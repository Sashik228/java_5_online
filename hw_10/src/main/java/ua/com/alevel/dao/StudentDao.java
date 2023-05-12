package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;

import java.util.Collection;

public interface StudentDao extends BaseDao<Student> {

    void attachGroupToStudent(Long studentId, Long groupId);

    void detachGroupFromStudent(Long studentI, Long groupId);

    Collection<Student> findStudentByGroup(Long groupId);
}