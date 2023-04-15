package ua.com.alevel.entity;

import java.io.Serializable;

public class StudentCSV implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private int age;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        if (age > 18 && age < 50) {
            this.age = age;
        }
    }

    public String toString() {
        return "Student > ID: " + id + ";  Full Name: " + firstName + ", " + lastName + "; " + " Age: " + age + " ";
    }
}