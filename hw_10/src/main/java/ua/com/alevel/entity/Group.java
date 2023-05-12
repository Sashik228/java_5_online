package ua.com.alevel.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students_groups")
public class Group extends BaseEntity {

    @Column(name = "label")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "students_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> studentSet = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }
    public Set<Student> getStudentSet() {
        return studentSet;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}