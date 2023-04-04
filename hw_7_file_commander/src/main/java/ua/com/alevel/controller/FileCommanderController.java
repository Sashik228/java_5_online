package ua.com.alevel.controller;

import ua.com.alevel.service.FileCommanderService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileCommanderController {

    FileCommanderService fileCommanderService = new FileCommanderService();

    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            cases(bf, select);
        }
    }

    private void menu() {
        System.out.println("                (⌐■_■)–︻╦╤─   (✿◠‿◠)              ");
        System.out.println("|   _______________________________________________|");
        System.out.println("|  Операції:                                       |");
        System.out.println("|  1  Добавити папку в папку...                    |");
        System.out.println("|  2  Добавити файл в папку...                     |");
        System.out.println("|  3  Перелік файлів в папці...                    |");
        System.out.println("|  4  Видалити файл чи папку в папці...            |");
        System.out.println("|  5  Перемістити файли в папці...                 |");
        System.out.println("|  6  Знайти файли в папці...                      |");
        System.out.println("|  7  Знайти текст в папці...                      |");
        System.out.println("|  0  Вийти..._____________________________________|");
    }

    public void cases(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> addD(reader);
            case "2" -> addF(reader);
            case "3" -> list(reader);
            case "4" -> delete(reader);
            case "5" -> move(reader);
            case "6" -> findFile(reader);
            case "7" -> findText(reader);
            case "0" -> System.exit(0);
        }
        menu();
    }

    public void addD(BufferedReader reader) throws IOException {
        System.out.println("Введіть шлях директорії та її ім'я '/':");
        String addD = reader.readLine();
        fileCommanderService.createDirectory(addD);
    }

    public void addF(BufferedReader reader) throws IOException {
        System.out.println("Веддіть шлях директорії та ім'я файлу '/':");
        String addF = reader.readLine();
        fileCommanderService.createFile(addF);
    }

    public void list(BufferedReader reader) throws IOException {
        System.out.println("Введіть ім'я директорії:");
        File file = new File(reader.readLine());
        fileCommanderService.listFile(String.valueOf(file));
    }

    public void delete(BufferedReader reader) throws IOException {
        System.out.println("Введіть ім'я файлу чи директорії'/':");
        String delete = reader.readLine();
        fileCommanderService.deleteFile(delete);
    }

    public void move(BufferedReader reader) throws IOException {
        System.out.println("Введіть шлях директорії звідки пересунути файл:");
        File directory = new File(reader.readLine());
        System.out.println("Введіть ім'я файлу:");
        File file = new File(directory, reader.readLine());
        System.out.println("Введіть шлях директорії куди пересунути файл:");
        File newDirectory = new File(reader.readLine());
        file.renameTo(new File(newDirectory, file.getName()));
        System.out.println("Файл був переміщений сюди: " + newDirectory);
    }

    public void findFile(BufferedReader reader) throws IOException {
        System.out.println("Введіть шлях директорії:");
        String dir = reader.readLine();
        System.out.println("Введіть ім'я файлу:");
        String file = reader.readLine();
        fileCommanderService.findFileOrFolder(new File(dir), file);
    }

    private void findText(BufferedReader reader) throws IOException {
        System.out.println("Введіть шлях директорії:");
        File dir = new File(reader.readLine());
        System.out.println("Введіть текст який хочете знайти:");
        String text = reader.readLine().toLowerCase();
        fileCommanderService.findTextInFiles(dir, text);
    }
}