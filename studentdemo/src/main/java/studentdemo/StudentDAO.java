package studentdemo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentDAO {
    private static final SessionFactory factory;

    static {
        factory=Utility.getSessionFactory();
    }

    public void saveStudent(Student student) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
        }
    }

    public List<Student> getAllStudents() {
        try (Session session = factory.openSession()) {
            return session.createSelectionQuery("FROM Student", Student.class).list();
        }
    }

    public Student getStudent(int id) {
        try (Session session = factory.openSession()) {
            return session.find(Student.class, id);
        }
    }

    public void updateStudent(Student student) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(student);
            tx.commit();
        }
    }

    public void deleteStudent(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student student = session.find(Student.class, id);
            if (student != null) {
                session.remove(student);
            }
            tx.commit();
        }
    }
}
