package ua.com.alevel.dto;

public record GroupStudentDto(Long studentId, Long groupId) {

    @Override
    public String toString() {
        return "GroupStudentDto{" +
                "studentId=" + studentId +
                ", groupId=" + groupId +
                '}';
    }
}