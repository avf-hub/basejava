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
        File dir = new File("./src");
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
        printDirAndFiles(dir, "");
    }

    public static void printDirAndFiles(File directory, String indent) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File element : files) {
                if (element.isDirectory()) {
                    System.out.println(indent + "Directory: " + element.getName());
                    printDirAndFiles(element, indent + "\t");
                } else if (element.isFile()) {
                    System.out.println(indent + "File: " + element.getName());
                }
            }
        }
    }
}
