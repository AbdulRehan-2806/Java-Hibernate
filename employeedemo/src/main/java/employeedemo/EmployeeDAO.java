package employeedemo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmployeeDAO {
    private static final SessionFactory factory;

    static {
        factory=Utility.getSessionFactory();
    }

    public void saveEmployee(Employee employee) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(employee);
            tx.commit();
        }
    }

    public List<Employee> getAllEmployees() {
        try (Session session = factory.openSession()) {
            return session.createSelectionQuery("FROM Employee", Employee.class).list();
        }
    }

    public Employee getEmployee(int id) {
        try (Session session = factory.openSession()) {
            return session.find(Employee.class, id);
        }
    }

    public void updateEmployee(Employee employee) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(employee);
            tx.commit();
        }
    }

    public void deleteEmployee(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Employee employee = session.find(Employee.class, id);
            if (employee != null) {
                session.remove(employee);
            }
            tx.commit();
        }
    }
}
