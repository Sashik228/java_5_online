package ua.com.alevel.entity;

public class GroupStudentRelation {

    private Student student;
    private Group group;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupStudentRelation(Student student, Group group) {
        this.student = student;
        this.group = group;
    }

}