package ua.com.alevel.controler;

import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserControler {

    private UserService userService = new UserService();

    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вітаю в круд програмі");
        System.out.println("Виберіть опцію:");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            crud(bf, select);
        }
    }
    private void exit() {
        System.out.println("До побачення!");
        System.exit(0);
    }

    private void menu() {
        System.out.println();
        System.out.println("Якщо ви хочете створити користувача введіть 1");
        System.out.println("Якщо ви хочете оновити користувача введіть 2");
        System.out.println("Якщо ви хочете видалити користувача введіть 3");
        System.out.println("Якщо ви хочете знайти користувача введіть 4");
        System.out.println("Якщо ви хочете знайти всіх користувачів введіть 5");
        System.out.println("Якщо ви хочете закрити програму введіть 6");
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll();
            case "6" -> System.exit(0);
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter the first name:");
        String firstName = reader.readLine();
        System.out.println("Please enter the last name:");
        String lastName = reader.readLine();
        System.out.println("Please enter the phone:");
        String phone = reader.readLine();
        System.out.println("Please enter the age:");
        String stringAge = reader.readLine();
        int age = Integer.parseInt(stringAge);
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setAge(age);
        userService.create(user);
    }

    private void update(BufferedReader bf) throws IOException{
        System.out.println("Enter id");
        String id = bf.readLine();
        System.out.println("Please enter the new first name:");
        String firstName = bf.readLine();
        System.out.println("Please enter the new last name:");
        String lastName = bf.readLine();
        System.out.println("Please enter the  new phone number:");
        String phone = bf.readLine();
        System.out.println("Please enter the new age:");
        String stringAge = bf.readLine();
        int age = Integer.parseInt(stringAge);
        User user = userService.findById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setAge(age);
        userService.update(user);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Введіть айді користувача: ");
        String id = reader.readLine();
        User user = userService.delete(id);
        System.out.println("Користувач був видалений");
    }

    private void findById(BufferedReader reader) throws IOException {
        System.out.println("Будь ласка введіть айді користувача: ");
        String id = reader.readLine();
        User user = userService.findById(id);
        System.out.println("Користувач = " + user);
    }

    private void findAll() {
        User[] users = userService.findAll();
        for (User user : users) {
            System.out.println("користувач = " + users);
        }
    }
}