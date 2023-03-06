package ua.com.alevel.service;

import ua.com.alevel.entity.User;
import java.util.UUID;

public class UserService {

    private User[] users = new User[10];
    private int userSum;

    public void create(User user) {
        user.setId(generateId());
        increaseSize();
        int age = user.getAge();
        for (int i = 0; i < users.length; i++) {
            if (age < 0){
                users[i] = null;
            } else if (users[i] == null) {
                users[i] = user;
                break;
            }
        }
        userSum++;

    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (User user : users) {
            if (user != null && user.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }

    public void increaseSize() {
        if (userSum == users.length) {
            User[] users1 = new User[ (users.length + 10)];
            for (int i = 0; i < users.length; i++) {
                users1[i] = users[i];
            }
            this.users = users1;
        }
    }

    public void update(User user) {
            for (int i = 0; i < users.length; i++) {
                try {
                    if (users[i].getId().equals(user.getId())){
                        users[i] = user;
                    }
                } catch (Exception e) {
                    i++;
                }
            }
        }

    public User delete(String id) {
            for (int i = 0; i < users.length; i++) {
                try {
                    if(users[i].getId().equals(id)){
                        users[i] = null;
                    }
                }
                catch (Exception exception){
                    i++;
                }
            }
            return null;
        }

    public User findById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User[] findAll() {
        return users;
    }
}