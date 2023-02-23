package ua.com.alevel.service;

import ua.com.alevel.entity.User;

import java.util.UUID;

public class UserService {

    private User[] users = new User[10];

    public void create(User user) {
        user.setId(generateId());
        for (int i = 0; i < users.length; i++){
            if (users[i] == null){
                users[i] = user;
            }
        }
    }

    public void update(User user) {
    }

    public void delete(String id) {
    }

    public User findById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User[] findAll() {
        return users;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return generateId();
            }
        }
            return null;
    }
}
