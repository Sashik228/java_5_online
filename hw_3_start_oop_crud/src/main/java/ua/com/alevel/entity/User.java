package ua.com.alevel.entity;

public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phone;
    private int age;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName(String firstName, String lastName) {
        return this.firstName + " " + this.lastName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User{" +
                "First Name = '" + firstName + '\'' +
                ", Last Name = '" + lastName +
                ", ID = '" + getId() + '\'' +
                "} " + super.toString();

    }

    public int getAge() {
        return age;
    }
}
