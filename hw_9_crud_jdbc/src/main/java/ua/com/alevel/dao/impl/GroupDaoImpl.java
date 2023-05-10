package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.dto.GroupStudentDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.jdbc.ConnectorDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class GroupDaoImpl implements GroupDao {

    private final Connection connection = ConnectorDB.getInstance().getConnection();
    private final Statement statement = ConnectorDB.getInstance().getStatement();

    private static final String CREATE_GROUP = "insert into Group values (default, ?)";
    private static final String UPDATE_GROUP = "update Group set name = ? where id = ?";
    private static final String FIND_GROUP = "select * from Group";
    private static final String DELETE_GROUP = "delete from Group where id = ?";
    private static final String FIND_BY_ID_GROUP = "select * from Group where id = ?";
    private static final String ATTACH_STUDENT_TO_GROUP = "insert into Student_group values (?, ?)";
    private static final String DETACH_STUDENT_FROM_GROUP = "delete from Student_group where group_id = ? and student_id = ?";
    private static final String FIND_GROUP_STUDENT = "select * from Student_group";
    private static final String FIND_ALL_GROUP_BY_STUDENT = "select g.id, g.name from Group as g join Student_group as st on st.group_id = g.id where st.student_id = ?";

    @Override
    public void create(Group group) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_GROUP)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
    }

    @Override
    public void update(Group group) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GROUP)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setLong(2, group.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
    }

    @Override
    public Optional<Group> findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_GROUP)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return Optional.of(convertResultSetToGroup(resultSet));
        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
        return Optional.empty();
    }

    private Group convertResultSetToGroup(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        group.setId(id);
        group.setName(name);
        return group;
    }

    private GroupStudentDto convertGroupStudentDto(ResultSet resultSet) throws SQLException {
        Long student_id = resultSet.getLong("student_id");
        Long group_id = resultSet.getLong("group_id");
        return new GroupStudentDto(student_id, group_id);
    }

    @Override
    public ArrayList<Group> findAll() {
        ArrayList<Group> groups = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery(FIND_GROUP)) {
            while (resultSet.next()) {
                groups.add(convertResultSetToGroup(resultSet));
            }
            return groups;
        } catch (SQLException e) {
            System.out.println("Exception = " + e.getMessage());
        }
        return groups;
    }

    @Override
    public void attachStudentToGroup(Long groupId, Long studentId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ATTACH_STUDENT_TO_GROUP)) {
            preparedStatement.setLong(1, groupId);
            preparedStatement.setLong(2, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
    }

    @Override
    public Collection<Group> findGroupStudent(Long studentId) {
        List<Group> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_GROUP_BY_STUDENT)) {
            preparedStatement.setLong(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(convertResultSetToGroup(resultSet));
            }
        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
        return students;
    }

    @Override
    public void detachStudentFromGroup(Long groupId, Long studentId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DETACH_STUDENT_FROM_GROUP)) {
            preparedStatement.setLong(1, groupId);
            preparedStatement.setLong(2, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Exception = " + e.getMessage());
        }
    }

    @Override
    public Collection<GroupStudentDto> findAllRelationships() {
        List<GroupStudentDto> dto = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery(FIND_GROUP_STUDENT)) {
            while (resultSet.next()) {
                dto.add(convertGroupStudentDto(resultSet));
            }
            return dto;
        } catch (SQLException ex) {
            System.out.println("ex = " + ex.getMessage());
        }
        return dto;
    }
}