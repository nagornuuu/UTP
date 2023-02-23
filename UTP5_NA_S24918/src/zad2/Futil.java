package zad2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class Futil {

    private static ArrayList<String> list = new ArrayList<>();                      // list of files

    public static void processDir(String dirName, String resultFileName) {
        try {
            // create new FileVisitor
            FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
                @Override
                // visiting file
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    //read file with cp1250 encoding
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.toFile()), "cp1250"));
                        String line;
                        while (!((line = br.readLine()) == null)) {
                        list.add(line);
                    }
                    return FileVisitResult.CONTINUE;                                // continue visiting
                }
            };
            Files.walkFileTree(Paths.get(dirName), fv);                             // walk through the directory
            Files.write(Paths.get(resultFileName), list, StandardCharsets.UTF_8);   // write list to file with UTF-8 encoding
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

//https://habr.com/ru/post/437694/ -- here is everything about FileVisitor