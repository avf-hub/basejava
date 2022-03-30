package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String pathName = ".\\.gitignore";
        File file = new File(pathName);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("ERROR", e);
        }
        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(pathName)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // HW08
        File directory = new File(".");
        printDirAndFiles(directory);
    }

    public static void printDirAndFiles(File directory) {
        File[] files = directory.listFiles();
        for (File element : files) {
            if (element.isFile()) {
                System.out.println("Файл: " + element.getName());
            } else {
                System.out.println("Папка: " + element.getName());
                printDirAndFiles(element);
            }
        }
    }
}
