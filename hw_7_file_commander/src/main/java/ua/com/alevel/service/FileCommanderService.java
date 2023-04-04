package ua.com.alevel.service;

import java.io.File;
import java.io.IOException;

public class FileCommanderService {

    public void createDirectory(String directoryName) throws IOException {
        File dirs = new File(directoryName);
        dirs.getAbsolutePath();
        String path = dirs.getAbsolutePath();
        System.out.println(path);
        dirs.mkdirs();
    }

    public void createFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.getAbsolutePath();
        String path = file.getAbsolutePath();
        System.out.println(path);
        file.createNewFile();
    }

    public void listFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.getAbsolutePath();
        String path = file.getAbsolutePath();
        System.out.println(path);
        File[] files = file.listFiles();
        for (File fl : files) {
            System.out.println(fl);
        }
    }

    public void deleteFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.delete();
        System.out.println("Файл був видалений з:");
        file.getAbsolutePath();
        String path = file.getAbsolutePath();
        System.out.println(path);
    }

    public void findFileOrFolder(File dir, String findFile) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().equalsIgnoreCase(findFile)) {
                        System.out.println(file.getAbsolutePath());
                    } else {
                        findFileOrFolder(file, findFile);
                    }
                }
            }
        }
    }

    public static void findTextInFiles(File file, String findText) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    findTextInFiles(file1, findText);
                }
            }
        }
    }
}