package ua.com.alevel;

import ua.com.alevel.controller.FileCommanderController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileCommanderController fileCommanderController = new FileCommanderController();
        fileCommanderController.start();
    }
}