package ua.com.alevel.dao;

import ua.com.alevel.dto.GroupStudentDto;
import ua.com.alevel.entity.Group;

import java.util.Collection;

public interface GroupDao extends BaseDao<Group> {

    void attachStudentToGroup(Long groupId, Long studentId);

    void detachStudentFromGroup(Long groupId, Long studentId);

    Collection<Group> findGroupStudent(Long studentId);

    Collection<GroupStudentDto> findAllRelationships();
}