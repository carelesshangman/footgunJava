import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java DirectoryListing <path>");
            System.exit(1);
        }

        File directory = new File(args[0]);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("The specified path does not exist or is not a directory.");
            System.exit(1);
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                System.out.printf("%-25s Datoteka %9.3f%n", file.getName(), file.length() / 1024.0);
            } else if (file.isDirectory()) {
                System.out.printf("%-25s    Mapa %9.3f%n", file.getName(), getFolderSize(file) / 1024.0);
            }
        }
    }

    public static long getFolderSize(File folder) {
        long size = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                size += file.length();
            } else {
                size += getFolderSize(file);
            }
        }
        return size;
    }
}
