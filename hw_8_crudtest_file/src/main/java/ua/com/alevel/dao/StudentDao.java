package ua.com.alevel.dao;

import ua.com.alevel.entity.StudentCSV;

import java.util.List;

public interface StudentDao {
    void create(StudentCSV student);
    void update(StudentCSV student);
    void delete(String id);
    StudentCSV findById(String id);
    List<StudentCSV> findAll();
}