package com.example.productdemo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ProductDAO {
    private static final SessionFactory factory;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // Reads your hibernate.cfg.xml automatically
                .build();
        try {
            factory = new MetadataSources(registry)
                    .addAnnotatedClass(Product.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError("Hibernate SessionFactory creation failed: " + e);
        }
    }

    public void saveProduct(Product product) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = factory.openSession()) {
            return session.createSelectionQuery("FROM Product", Product.class).list();
        }
    }

    public Product getProduct(int id) {
        try (Session session = factory.openSession()) {
            return session.find(Product.class, id);
        }
    }

    public void updateProduct(Product product) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        }
    }

    public void deleteProduct(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Product product = session.find(Product.class, id);
            if (product != null) {
                session.remove(product);
            }
            tx.commit();
        }
    }
}
