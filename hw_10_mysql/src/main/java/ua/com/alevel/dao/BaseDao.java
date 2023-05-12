package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseDao<Entity extends BaseEntity> {

    void create(Entity entity);

    void update(Entity entity);

    void delete(Long id);

    Optional<Entity> findById(Long id);

    Collection<Entity> findAll();
}