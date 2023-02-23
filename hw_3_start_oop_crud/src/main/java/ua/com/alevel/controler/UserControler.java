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
        System.out.println("Вітаю в першій CRUD програмі");
        System.out.println("Виберіть опцію:");
        boolean select;
        menu();
        while ((select = bf.readLine() != null)) {
            crud(bf, select);
        }
    }
    private void menu() {
        System.out.println();
        System.out.println("Якщо ти хочеш створити користувача введи 1");
        System.out.println("Якщо ти хочеш оновити користувача введи 2");
        System.out.println("Якщо ти хочеш видалити користувача введи 3");
        System.out.println("Якщо ти хочеш знайти користувача користувача введи 4");
        System.out.println("Якщо ти хочеш знайти всіх користувачів введи 5");
        System.out.println("Якщо ти хочеш вийти введи 6");
    }
    private  void  crud(BufferedReader reader, String select) {
          switch (select){
              case "1" -> create(reader);
              case "2" -> update(reader);
              case "3" -> delete(reader);
              case "4" -> findById(reader);
              case "5" -> findAll(reader);
              case "6" -> System.exit(0);
          }
    }
private void create(BufferedReader reader) throws IOException {
    System.out.println("Будь ласка введіть ім'я");
    String firstname = reader.readLine();
    System.out.println("Будь ласка введіть прізвище");
    String lastname = reader.readLine();
    System.out.println("Будь ласка введіть телефон");
    String phone = reader.readLine();
    System.out.println("Будь ласка введіть вік");
    String stringAge = reader.readLine();
    int age = Integer.parseInt(stringAge);
    User user = new User();
    user.setFirstName();
    user.setLastName();
    user.setPhone();
    user.setAge();
    userService.create(user);
}
private void update(){
}
private void delete(){

}
private void findById(BufferedReader reader) throws IOException {
    System.out.println("Будь ласка введіть айді");
    String id = reader.readLine();
    User user = userService.findById();
    System.out.println("user = " + user);
}
 private void findAll(){
   User[] users = userService.findAll();
     for (User user : users) {
         System.out.println("user = " + user);
     }
 }
}
