package ua.com.alevel.entity;

import java.util.UUID;

public abstract class BaseEntity {
    private  String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
