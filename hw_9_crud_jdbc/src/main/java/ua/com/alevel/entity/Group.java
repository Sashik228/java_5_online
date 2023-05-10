package ua.com.alevel.entity;

public class Group extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "label='" + name + '\'' +
                "} " + super.toString();
    }
}