package ua.com.alevel;

import ua.com.alevel.controler.UserControler;

import java.io.IOException;

public class StartOOP {
    public static void main(String[] args) throws IOException {
        System.out.println("Створюєм читаєм оновлюєм видаляєм ");

        UserControler userControler = new UserControler();
        userControler.start();
    }
}
