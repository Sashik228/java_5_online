package ua.com.alevel.entity;

public class Group extends BaseEntityGroup {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Група{" +
                "ім'я='" + name + '\'' +
                "} " + getId();

    }
}
