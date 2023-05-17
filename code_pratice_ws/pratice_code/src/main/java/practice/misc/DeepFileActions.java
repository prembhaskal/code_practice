package practice.misc;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DeepFileActions {

    public static void main(String[] args) throws IOException {
        new DeepFileActions().listFiles(new File("F:/android/docs/"));
    }

    private int count = 0;

    private void listFiles(File file) throws IOException {

        if (file == null) {
            throw new IllegalArgumentException("null file");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("not directory");
        }


        Path path = Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path.toAbsolutePath().toString());
                count++;
                Files.delete(path);
                return super.visitFile(path, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });

        System.out.println("total files " + count);

        System.out.println("path is " + path.toString());
    }
}
