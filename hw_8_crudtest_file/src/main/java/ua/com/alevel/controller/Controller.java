package ua.com.alevel.controller;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dao.StudentDaoCSV;
import ua.com.alevel.entity.StudentCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static ua.com.alevel.dao.StudentDaoCSV.STUDENTS_DATA_BASE;
import static ua.com.alevel.dao.StudentDaoCSV.STUDENTS_DATA_BASE_STUDENTS;

public class Controller {

    private final StudentDao studentDao = new StudentDaoCSV();

    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("    |Привіт! Ласкаво просимо |");
        System.out.println("    | до бази даних STUDENTS |");
        String select;
        menu();
        createDir();
        while((select = bf.readLine()) != null) {
            variant(bf, select);
        }
    }

    private void menu() {
        System.out.println("    |------------------------|");
        System.out.println("    Створити студента        1");
        System.out.println("    Оновити студента         2");
        System.out.println("    Видалити студента        3");
        System.out.println("    Знайти студента за айді  4");
        System.out.println("    Побачити всіх студентів  5");
        System.out.println("    Видалити папку студентів 6");
        System.out.println("    Вийти                    0");
        System.out.println("    |........................|");
    }

    private void variant(BufferedReader reader, String select) throws IOException {
        switch(select) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll();
            case "0" -> System.exit(0);
            case "6" -> deleteDir();
        }
        menu();
    }

    private void createDir() throws IOException {
        File dirs = new File(STUDENTS_DATA_BASE.toURI());
        File file = new File(STUDENTS_DATA_BASE_STUDENTS.toURI());
        file.getAbsolutePath();
        String path = file.getAbsolutePath();
        System.out.println(">>  " + path);
        dirs.mkdirs();
        file.createNewFile();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Ім'я:");
        String firstName = reader.readLine();
        System.out.println("Прізвище:");
        String lastName = reader.readLine();
        System.out.println("Вік:");
        String stringAge = reader.readLine();
        int age = Integer.parseInt(stringAge);
        StudentCSV st = new StudentCSV();
        st.setFirstName(firstName);
        st.setLastName(lastName);
        st.setAge(age);
        studentDao.create(st);
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Айді студента для оновлення:");
        String id = reader.readLine();
        System.out.println("Ім'я:");
        String firstName = reader.readLine();
        System.out.println("Прізвище:");
        String lastName = reader.readLine();
        System.out.println("Вік:");
        String stringAge = reader.readLine();
        int age = Integer.parseInt(stringAge);
        StudentCSV st = studentDao.findById(id);
        st.setFirstName(firstName);
        st.setLastName(lastName);
        st.setAge(age);
        studentDao.update(st);
        System.out.println("Оновлено!");
    }

    public void delete(BufferedReader reader) throws IOException {
        System.out.println("Айді студента для видалення: ");
        String id = reader.readLine();
        studentDao.delete(id);
        System.out.println("Видалено!");
    }

    private void findById(BufferedReader reader) throws IOException {
        System.out.println("Айді студента щоб знайти: ");
        String id = reader.readLine();
        StudentCSV st = studentDao.findById(id);
        System.out.println("- " + st);
    }

    private void findAll() {
        System.out.println("Список студентів:");
        List<StudentCSV> students = studentDao.findAll();
        for (StudentCSV st : students) {
            System.out.println("- " + st);
        }
    }

    private void deleteDir() {
        File file = new File(STUDENTS_DATA_BASE_STUDENTS.toURI());
        file.delete();
        file = new File(STUDENTS_DATA_BASE.toURI());
        file.delete();
        System.out.println("Папку з студентами видалено:");
        file.getAbsolutePath();
        String path = file.getAbsolutePath();
        System.out.println(path);
    }
}