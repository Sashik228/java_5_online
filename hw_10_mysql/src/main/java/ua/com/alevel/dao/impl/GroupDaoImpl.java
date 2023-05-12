package ua.com.alevel.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.dto.GroupStudentDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

import java.util.*;

public class GroupDaoImpl implements GroupDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Group group) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            session.save(group);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
    }

    @Override
    public void update(Group group) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            session.update(group);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Group group = session.get(Group.class, id);
            session.delete(group);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
    }

    @Override
    public Optional<Group> findById(Long id) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Group group = session.find(Group.class, id);
            tr.commit();
            return Optional.of(group);
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
        return Optional.empty();
    }

    @Override
    public Collection<Group> findAll() {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Query query = session.createQuery("from Group");
            List<Group> groups = query.getResultList();
            tr.commit();
            return groups;
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<Group> findGroupStudent(Long studentId) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Student student = session.find(Student.class, studentId);
            Set<Group> groups = student.getGroupSet();
            tr.commit();
            return groups;
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
        return new HashSet<Group>() {
        };
    }

    public List<GroupStudentDto> findAllRelationships() {
        List<GroupStudentDto> dto = new ArrayList<>();
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Query query = session.createQuery("from Group");
            List<Group> groups = query.getResultList();
            for (Group gr : groups) {
                for (Student st : gr.getStudentSet()) {

                    dto.add(new GroupStudentDto(st.getId(), gr.getId()));
                }
            }
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
        return dto;
    }

    private void rollTransaction(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}