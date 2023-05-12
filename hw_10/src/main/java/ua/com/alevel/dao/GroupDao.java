package ua.com.alevel.dao;

import ua.com.alevel.dto.GroupStudentDto;
import ua.com.alevel.entity.Group;

import java.util.Collection;
import java.util.List;


public interface GroupDao extends BaseDao<Group> {

    Collection<Group> findGroupStudent(Long studentId);

    List<GroupStudentDto> findAllRelationships();
}