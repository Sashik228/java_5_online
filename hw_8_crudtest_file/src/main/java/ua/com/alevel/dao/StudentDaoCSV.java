package ua.com.alevel.dao;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.entity.StudentCSV;
import ua.com.alevel.util.Util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoCSV implements StudentDao {

    public static final File STUDENTS_DATA_BASE = new File("STUDIENTS_Data_Base");
    public static final File STUDENTS_DATA_BASE_STUDENTS = new File("STUDIENTS_Data_Base/students.CSV");

    private List<StudentCSV> students = new ArrayList<>();

    @Override
    public void create(StudentCSV student) {
        initStudents();
        student.setId(Util.getInstance().generateId(students));
        students.add(student);
        wrightStudentsToCSV();
    }

    @Override
    public void update(StudentCSV student) {
        initStudents();
        for(int i = 0; i < students.size(); i++) {
            try {
                if (students.get(i).getId().equals(student.getId())) {
                    students.set(i, student);
                }
            }
            catch (Exception e) {
                i++;
            }
        }
        wrightStudentsToCSV();
    }

    @Override
    public void delete(String id) {
        initStudents();
        students.removeIf(student -> student.getId().equals(id));
        wrightStudentsToCSV();
    }

    @Override
    public StudentCSV findById(String id) {
        initStudents();
        for (StudentCSV student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<StudentCSV> findAll() {
        initStudents();
        return students;
    }

    private void initStudents() {
        try (CSVReader CSVReader = new CSVReader(new FileReader(STUDENTS_DATA_BASE_STUDENTS))) {
            List<String[]> list = CSVReader.readAll();
            students = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(list)) {
                for (String[] element : list) {
                    StudentCSV student = new StudentCSV();
                    student.setId(element[0]);
                    student.setFirstName(element[1]);
                    student.setLastName(element[2]);
                    student.setAge(Integer.parseInt(element[3]));
                    students.add(student);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void wrightStudentsToCSV() {
        try (CSVWriter CSVWriter = new CSVWriter(new FileWriter(STUDENTS_DATA_BASE_STUDENTS))) {
            List<String[]> list = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(students)) {
                for (StudentCSV student : students) {
                    String [] st = new String[] {
                            student.getId(),
                            student.getFirstName(),
                            student.getLastName(),
                            String.valueOf(student.getAge())
                    };
                    list.add(st);
                }
                CSVWriter.writeAll(list);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}