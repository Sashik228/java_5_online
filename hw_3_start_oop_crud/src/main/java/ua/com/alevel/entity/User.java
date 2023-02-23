package ua.com.alevel.entity;

public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phone;

    private int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if (age > 1 && age < 120) {
        }
      this.age = age;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
