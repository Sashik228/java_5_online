package ua.com.alevel.controller;

import ua.com.alevel.dao.impl.GroupDaoImpl;
import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.dto.GroupStudentDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Controller {

    private final GroupDaoImpl groupDao = new GroupDaoImpl();

    private final StudentDaoImpl studentDao = new StudentDaoImpl();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String select;
        menu();
        while ((select = bufferedReader.readLine()) != null) {
            crud(bufferedReader, select);
        }
    }

    public void menu() {
        System.out.println("            STUDENT             " );
        System.out.println("Create student, please enter - 1");
        System.out.println("Update student, please enter - 2");
        System.out.println("Delete student, please enter - 3");
        System.out.println("Find student by id, please enter - 4");
        System.out.println("Find all students, please enter - 5");
        System.out.println("Find all students in group, please enter - 6");

        System.out.println("              GROUP           ");
        System.out.println("Create group, please enter - 7");
        System.out.println("Update group, please enter - 8");
        System.out.println("Delete group, please enter - 9");
        System.out.println("Find group by id, please enter - 10");
        System.out.println("Find all groups, please enter - 11");
        System.out.println("Find all groups in student, please enter - 12");

        System.out.println("STUDENT - GROUP RELATIONSHIPS");
        System.out.println("Add student to group, please enter - 13");
        System.out.println("Delete student from group , please enter - 14");
        System.out.println("Find all student-group relationships, enter - 15");
        System.out.println("-------------------------------------------------------------");
        System.out.println("                         EXIT - 0                            ");
        System.out.println("-------------------------------------------------------------");
    }

    private void crud(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> createStudent(bufferedReader);
            case "2" -> updateStudent(bufferedReader);
            case "3" -> deleteStudent(bufferedReader);
            case "4" -> findStudent(bufferedReader);
            case "5" -> findAllStudent(bufferedReader);
            case "6" -> findAllStudentByGroup(bufferedReader);

            case "7" -> createGroup(bufferedReader);
            case "8" -> updateGroup(bufferedReader);
            case "9" -> deleteGroup(bufferedReader);
            case "10" -> findGroup(bufferedReader);
            case "11" -> findAllGroup(bufferedReader);
            case "12" -> findAllGroupByStudent(bufferedReader);

            case "13" -> attachStudentToGroup(bufferedReader);
            case "14" -> detachStudentFromGroup(bufferedReader);
            case "15" -> allRelationships(bufferedReader);

            case "0" -> System.exit(0);
        }
        menu();
    }

    private void createStudent(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter the first name of the student:");
        String firstName = bufferedReader.readLine();
        System.out.println("Please enter the last name of the student:");
        String lastName = bufferedReader.readLine();
        System.out.println("Please enter the age of the student:");
        int age = Integer.parseInt(bufferedReader.readLine());
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        studentDao.create(student);
    }

    private void updateStudent(BufferedReader bufferedReader) throws IOException {
        printListOfStudents();
        System.out.println("To update a student, please enter the id: ");
        long id = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Please enter the first name of the student: ");
        String firstName = bufferedReader.readLine();
        System.out.println("Please enter the last name of the student: ");
        String lastName = bufferedReader.readLine();
        System.out.println("Please enter the age of the student: ");
        int age = Integer.parseInt(bufferedReader.readLine());
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setId(id);
        studentDao.update(student);
    }

    private void deleteStudent(BufferedReader bufferedReader) throws IOException {
        printListOfStudents();
        System.out.println("To delete a student, please enter the id: ");
        long id = Integer.parseInt(bufferedReader.readLine());
        studentDao.delete(id);
    }

    private void findStudent(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter the id of the student: ");
        long id = Integer.parseInt(bufferedReader.readLine());
        Optional<Student> student = studentDao.findById(id);
        System.out.println("Student = " + student);
    }

    private void findAllStudent(BufferedReader bufferedReader) {
        Collection<Student> students = studentDao.findAll();
        for (Student student : students) {
            System.out.println("Student = " + student);
        }
    }

    private void findAllStudentByGroup(BufferedReader bufferedReader) throws IOException {
        printListOfGroup();
        System.out.println("Please enter the id of the group: ");
        long id = Integer.parseInt(bufferedReader.readLine());
        Collection<Student> all_student = studentDao.findStudentByGroup(id);
        for (Student st : all_student) {
            System.out.println("Student = " + st);
        }
    }

    private void createGroup(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter the name of the group:");
        String name = bufferedReader.readLine();
        Group group = new Group();
        group.setName(name);
        groupDao.create(group);
    }

    private void updateGroup(BufferedReader bufferedReader) throws IOException {
        printListOfGroup();
        System.out.println("To update a group, please enter the id:");
        long id = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Please enter the name of the group:");
        String name = bufferedReader.readLine();
        Group group = new Group();
        group.setName(name);
        group.setId(id);
        groupDao.update(group);
    }

    private void deleteGroup(BufferedReader bufferedReader) throws IOException {
        printListOfGroup();
        System.out.println("To delete a group, please enter the id:");
        int choice = Integer.parseInt(bufferedReader.readLine());
        long id = Integer.parseInt(bufferedReader.readLine());
        groupDao.delete(id);
    }

    private void findGroup(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter the id of the group: ");
        long id = Integer.parseInt(bufferedReader.readLine());
        Optional<Group> group = groupDao.findById(id);
        System.out.println("Group = " + group);
    }

    private void findAllGroup(BufferedReader bufferedReader) {
        Collection<Group> groups = groupDao.findAll();
        for (Group group : groups) {
            System.out.println("Group = " + group);
        }
    }

    private void findAllGroupByStudent(BufferedReader bufferedReader) throws IOException {
        printListOfStudents();
        System.out.println("Please enter the id of the student: ");
        long id = Integer.parseInt(bufferedReader.readLine());
        Collection<Group> all_students = groupDao.findGroupStudent(id);
        if (!all_students.isEmpty()) {
            for (Group gr : all_students) {
                System.out.println("Group = " + gr);
            }
        } else {
            System.out.println("The list is empty.");
        }
    }

    private void attachStudentToGroup(BufferedReader bufferedReader) throws IOException {
        printListOfStudents();
        printListOfGroup();
        System.out.println("Please enter the student id: ");
        long studentId = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Please enter the group id: ");
        long groupId = Integer.parseInt(bufferedReader.readLine());
        studentDao.attachGroupToStudent(studentId, groupId);
    }

    private void detachStudentFromGroup(BufferedReader bufferedReader) throws IOException {
        relations();
        System.out.println("Please enter the student id: ");
        long studentId = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Please enter the group id: ");
        long groupId = Integer.parseInt(bufferedReader.readLine());
        studentDao.detachGroupFromStudent(studentId, groupId);
    }

    private void allRelationships(BufferedReader bufferedReader) {
        List<GroupStudentDto> relations = groupDao.findAllRelationships();
        System.out.println("Student id - Group id");
        for (GroupStudentDto rl : relations) {
            System.out.println("\t " + rl.studentId() + "\t   - \t" + rl.groupId());
            System.out.println("-----------------------");
        }
    }

    private void relations() {
        Collection<GroupStudentDto> groups = groupDao.findAllRelationships();
        for (GroupStudentDto gr : groups) {
            System.out.println("Relation = " + gr);
        }
    }

    private void printListOfGroup() {
        ArrayList<Group> groups = (ArrayList<Group>) groupDao.findAll();
        for (int i = 0; i < groups.size(); i++) {
            System.out.println((i + 1) + " - " + groups.get(i));
        }
    }

    private void printListOfStudents() {
        ArrayList<Student> students = (ArrayList<Student>) studentDao.findAll();
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + " - " + students.get(i));
        }
    }
}