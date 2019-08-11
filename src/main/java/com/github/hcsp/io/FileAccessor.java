package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> list = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            list.add(line);
        }

        bufferedReader.close();
        return list;
    }


    public static List<String> readFile2(File file) throws IOException {
        Path path = file.toPath();
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    public static List<String> readFile3(File file) throws IOException {
        return FileUtils.readLines(file, "UTF-8");
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String lineStr = String.join("\n", lines);

        bufferedWriter.write(lineStr);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        String lineStr = String.join("\n", lines);
        FileUtils.write(file, lineStr, "UTF-8");
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        Path path = file.toPath();
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        File testFile = new File(projectDir, "target/test.txt");
        List<String> lines = Arrays.asList("AAA", "BBB", "CCC");
        writeLinesToFile1(lines, testFile);
        writeLinesToFile2(lines, testFile);
        writeLinesToFile3(lines, testFile);

        System.out.println(readFile1(testFile));
        System.out.println(readFile2(testFile));
        System.out.println(readFile3(testFile));
    }
}
