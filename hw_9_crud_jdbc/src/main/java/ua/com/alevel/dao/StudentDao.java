package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;

import java.util.Collection;

public interface StudentDao extends BaseDao<Student> {

    Collection<Student> findStudentByGroup(Long groupId);
}