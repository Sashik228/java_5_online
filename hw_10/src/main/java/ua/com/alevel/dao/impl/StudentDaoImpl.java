package ua.com.alevel.dao.impl;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.com.alevel.config.HibernateConfig;
import ua.com.alevel.dao.StudentDao;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

import java.util.*;

public class StudentDaoImpl implements StudentDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Student student) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            session.save(student);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
    }

    @Override
    public void update(Student student) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            session.update(student);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
    }

    public void delete(Long id) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            session.flush();
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Student student = session.find(Student.class, id);
            tr.commit();
            return Optional.of(student);
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
        return Optional.empty();
    }

    @Override
    public Collection<Student> findAll() {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Query query = session.createQuery("from Student");
            List<Student> student = query.getResultList();
            tr.commit();
            return student;
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
        return Collections.emptyList();
    }

    @Override
    public void attachGroupToStudent(Long studentId, Long groupId) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Student student = session.find(Student.class, studentId);
            Group group = session.find(Group.class, groupId);
            student.getGroupSet().add(group);
            group.getStudentSet().add(student);
            session.update(student);
            session.update(group);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
    }

    @Override
    public void detachGroupFromStudent(Long studentId, Long groupId) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Student student = session.find(Student.class, studentId);
            Group group = session.find(Group.class, groupId);
            student.getGroupSet().remove(group);
            group.getStudentSet().remove(student);
            session.update(student);
            session.update(group);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
    }

    @Override
    public Collection<Student> findStudentByGroup(Long groupId) {
        Transaction tr = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tr = session.beginTransaction();
            Group group = session.find(Group.class, groupId);
            Set<Student> students = group.getStudentSet();
            tr.commit();
            return students;
        } catch (Exception e) {
            System.out.println("Exception = " + e.getMessage());
            rollTransaction(tr);
        }
        return new HashSet<Student>() {

        };
    }

    private void rollTransaction(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}