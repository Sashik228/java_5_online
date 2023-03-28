package ua.com.alevel.entity;

public abstract class BaseEntityGroup {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntityGroup{" +
                "айді='" + id + '\'' +
                '}';
    }
}
