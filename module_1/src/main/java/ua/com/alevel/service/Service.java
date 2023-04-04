package ua.com.alevel.service;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.GroupStudentRelation;
import ua.com.alevel.entity.Student;

import java.util.ArrayList;
import java.util.UUID;

public class Service {

    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Group> groups = new ArrayList<Group>();
    private ArrayList<GroupStudentRelation> relations = new ArrayList<GroupStudentRelation>();

    private String generateStudentId() {
        String id = UUID.randomUUID().toString();
        for (Student student : students) {
            if (student != null && student.getId().equals(id)) {
                return generateStudentId();
            }
        }
        return id;
    }

    public void createStudent(Student student) {
        student.setId(generateStudentId());
        students.add(student);
    }

    public void updateStudent(int index, Student student) {
        student.setId(students.get(index).getId());
        students.set(index, student);
    }

    public void deleteStudent(int index) {
        deleteRelationByStudent(students.get(index).getId());
        students.remove(index);
    }

    public Student findByIdStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }

        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    private String generateGroupId() {
        String id = UUID.randomUUID().toString();
        for (Group group : groups) {
            if (group != null && group.getId().equals(id)) {
                return generateGroupId();
            }
        }
        return id;
    }

    public void createGroup(Group group) {
        group.setId(generateGroupId());
        groups.add(group);
    }

    public void updateGroup(int index, Group group) {
        group.setId(groups.get(index).getId()); //вытягиваем айди старой группы и записываем в новую
        groups.set(index, group);

    }

    public void deleteGroup(int index) {
        deleteRelationByGroup(groups.get(index).getId());
        groups.remove(index);
    }

    public Group findByIdGroup(String id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addStudentToGroup(String studentId, String groupId) {
        Student st = findByIdStudent(studentId);
        Group gr = findByIdGroup(groupId);
        relations.add(new GroupStudentRelation(st, gr));
    }

    public void deleteRelationByStudent(String id) {
        int size = relations.size();
        for (int i = 0; i < size; i++) {
            if (relations.get(i).getStudent().getId().equals(id)) {
                relations.remove(i);
                i--;
                size = relations.size();
            }
        }
    }

    public void deleteRelationByGroup(String id) {
        int size = relations.size();
        for (int i = 0; i < size; i++) {
            if (relations.get(i).getGroup().getId().equals(id)) {
                relations.remove(i);
                i--;
                size = relations.size();
            }
        }
    }


    public ArrayList<Group> findAllGroupByStudent(String id) {
        ArrayList<Group> studentGroup = new ArrayList<Group>();
        for (GroupStudentRelation rel : relations) {
            if (rel.getStudent().getId().equals(id)) {
                studentGroup.add(rel.getGroup());
            }
        }
        return studentGroup;
    }

    public ArrayList<Student> findAllStudentByGroup(String id) {
        ArrayList<Student> groupStudent = new ArrayList<Student>();
        for (GroupStudentRelation rel : relations) {
            if (rel.getGroup().getId().equals(id)) {
                groupStudent.add(rel.getStudent());
            }
        }
        return groupStudent;
    }
}
