package ua.com.alevel.controller;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {
    Service service = new Service();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Виберіть опцію: \n");
        String select;
        menu();
        while ((select = bufferedReader.readLine()) != null) {
            crud(bufferedReader, select);
        }
    }

    public void menu() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Якщо ви хочете створити студента натисніть - 1");
        System.out.println("Якщо ви хочете оновити студента натисніть - 2");
        System.out.println("Якщо ви хочете видалити студента натисніть - 3");
        System.out.println("Якщо ви хочете знайти студента натисніть - 4");
        System.out.println("Якщо ви хочете знайти всіх студентів натисніть - 5");
        System.out.println("Якщо ви хочете знайти всіх студентів в групі натисніть - 6");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Якщо ви хочете створити групу натисніть - 7");
        System.out.println("Якщо ви хочете оновити групу натисніть - 8");
        System.out.println("Якщо ви хочете видалити групу натисніть - 9");
        System.out.println("Якщо ви хочете знайти групу натисніть - 10");
        System.out.println("Якщо ви хочете знайти всі групи натисніть - 11");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Якщо ви хочете додати студента до групи натисніть - 12");
        System.out.println("Якщо ви хочете знайти групи в яких є студенти натисніть - 13");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Якщо хочете вийти натисніть - 0");
        System.out.println("--------------------------------------------------------------------------------");
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

            case "12" -> addStudentToGroup(bufferedReader);
            case "13" -> findAllGroupByStudent(bufferedReader);

            case "0" -> System.exit(0);

        }
        menu();
    }

    private void printListOfGroup() {
        ArrayList<Group> groups = service.getGroups();
        for (int i = 0; i < groups.size(); i++) {
            System.out.println((i + 1) + " - " + groups.get(i));
        }
    }

    private void printListOfStudents() {
        ArrayList<Student> students = service.getStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + " - " + students.get(i));
        }
    }

    private void addStudentToGroup(BufferedReader bufferedReader) throws IOException {
        System.out.println("Будь ласка введіть айді студента: ");
        String studentId = bufferedReader.readLine();
        System.out.println("Будь ласка введіть айді групи: ");
        String groupId = bufferedReader.readLine();
        service.addStudentToGroup(studentId, groupId);
    }

    private void findAllStudentByGroup(BufferedReader bufferedReader) throws IOException {
        System.out.println("Будь ласка введіть айді: ");
        String id = bufferedReader.readLine();
        ArrayList<Student> all_students = service.findAllStudentByGroup(id);

        System.out.println("Група = " + service.findByIdGroup(id));
        for (Student st : all_students) {
            System.out.println(st);
        }
    }

    private void findAllGroupByStudent(BufferedReader bufferedReader) throws IOException {
        System.out.println("Будь ласка введіть айді: ");
        String id = bufferedReader.readLine();
        ArrayList<Group> all_groups = service.findAllGroupByStudent(id);

        System.out.println("Студент = " + service.findByIdStudent(id));
        for (Group gr : all_groups) {
            System.out.println(gr);
        }
    }

    private void createStudent(BufferedReader bufferedReader) throws IOException {
        System.out.println("Будь ласка введіть ім'я студента:");
        String firstName = bufferedReader.readLine();
        System.out.println("Будь ласка введіть прізвище студента:");
        String lastName = bufferedReader.readLine();
        System.out.println("Будь ласка введіть вік студента:");
        int age = Integer.parseInt(bufferedReader.readLine());
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        service.createStudent(student);
    }

    private void updateStudent(BufferedReader bufferedReader) throws IOException {
        printListOfStudents();
        System.out.println("Будь ласка виберіть студента для оновлення: ");
        int choice = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Будь ласка введіть ім'я студента: ");
        String firstName = bufferedReader.readLine();
        System.out.println("Будь ласка введіть прізвище студента: ");
        String lastName = bufferedReader.readLine();
        System.out.println("Будь ласка введіть вік студента: ");
        int age = Integer.parseInt(bufferedReader.readLine());
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        service.updateStudent(choice - 1, student);
    }

    private void deleteStudent(BufferedReader bufferedReader) throws IOException {
        printListOfStudents();
        System.out.println("Будь ласка введіть номер студента для видалення: ");
        int choice = Integer.parseInt(bufferedReader.readLine());
        service.deleteStudent(choice - 1);
    }

    private void findStudent(BufferedReader bufferedReader) throws IOException {
        System.out.println("Будь ласка введіть айді: ");
        String id = bufferedReader.readLine();
        Student student = service.findByIdStudent(id);
        System.out.println("Студент = " + student);
    }

    private void findAllStudent(BufferedReader bufferedReader) {
        ArrayList<Student> students = service.getStudents();
        for (Student student : students) {
            System.out.println("Студент = " + student);
        }
    }

    private void createGroup(BufferedReader bufferedReader) throws IOException {
        System.out.println("Будь ласка введіть ім'я групи: ");
        String name = bufferedReader.readLine();
        Group group = new Group();
        group.setName(name);
        service.createGroup(group);
    }

    private void updateGroup(BufferedReader bufferedReader) throws IOException {
        printListOfGroup();
        System.out.println("Виберіть групу щоб обновити: ");
        int choice = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Будь ласка введіть ім'я групи: ");
        String name = bufferedReader.readLine();
        Group group = new Group();
        group.setName(name);
        service.updateGroup(choice - 1, group);
    }

    private void deleteGroup(BufferedReader bufferedReader) throws IOException {
        printListOfGroup();
        System.out.println("Введіть номер групи яку видалити: ");
        int choice = Integer.parseInt(bufferedReader.readLine());
        service.deleteGroup(choice - 1);
    }

    private void findGroup(BufferedReader bufferedReader) throws IOException {
        System.out.println("Будь ласка введіть айді: ");
        String id = bufferedReader.readLine();
        Group group = service.findByIdGroup(id);
        System.out.println("Група = " + group);
    }

    private void findAllGroup(BufferedReader bufferedReader) {
        ArrayList<Group> groups = service.getGroups();
        for (Group group : groups) {
            System.out.println("Група = " + group);
        }
    }

}