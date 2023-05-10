package ua.com.alevel.dto;

public class GroupStudentDto {

    private final Long studentId;

    private final Long groupId;

    @Override
    public String toString() {
        return "GroupStudentDto{" +
                "studentId=" + studentId +
                ", groupId=" + groupId +
                '}';
    }

    public GroupStudentDto(Long studentId, Long groupId) {
        this.studentId = studentId;
        this.groupId = groupId;
    }
}