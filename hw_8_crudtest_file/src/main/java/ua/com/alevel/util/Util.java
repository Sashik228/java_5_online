package ua.com.alevel.util;

import ua.com.alevel.entity.StudentCSV;

import java.util.List;
import java.util.UUID;

public final class Util {

    private static Util instance;

    private Util() {}

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public String generateId(List<StudentCSV> students) {
        String id = UUID.randomUUID().toString();
        if (students.stream().anyMatch(student -> student.getId().equals(id))) {
            return generateId(students);
        }
        return id;
    }
}