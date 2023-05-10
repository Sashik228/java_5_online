package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;
import ua.com.alevel.jdbc.ConnectorDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    private final Connection connection = ConnectorDB.getInstance().getConnection();
    private final Statement statement = ConnectorDB.getInstance().getStatement();
    private static final String CREATE_STUDENT = "insert into Student values (default, ?, ?, ?)";
    private static final String UPDATE_STUDENT = "update Student set first_name = ?, last_name = ?, age = ? where id = ?";
    private static final String DELETE_STUDENT = "delete from Student where id = ?";
    private static final String FIND_BY_ID_STUDENT = "select * from Student where id = ?";
    private static final String FIND_STUDENT = "select * from Student";
    private static final String FIND_ALL_GROUP_BY_STUDENT = "select s.id, s.first_name, s.last_name, s.age from Student as s " +
            "join Student_group as st on st.student_id = s.id where st.group_id = ?";

    @Override
    public void create(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STUDENT)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setLong(3, student.getAge());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
    }

    @Override
    public void update(Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setLong(4, student.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_STUDENT)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Optional.of(convertResultSetToStudent(resultSet));
        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Student> findAll() {
        ArrayList<Student> student = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery(FIND_STUDENT)) {
            while (resultSet.next()) {
                student.add(convertResultSetToStudent(resultSet));
            }
            return student;
        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
        return student;
    }

    @Override
    public Collection<Student> findStudentByGroup(Long groupId) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_GROUP_BY_STUDENT)) {
            preparedStatement.setLong(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(convertResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Exception = " + e.getMessage());
        }
        return students;
    }

    private Student convertResultSetToStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        return student;
    }
}