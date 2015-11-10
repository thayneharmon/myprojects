package com.tlhhub.tutorial;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.tlhhub.tutorial.entity.AppUser;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
  public void testApp() {
    Configuration configuration = new Configuration();
    configuration.configure();

    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
        configuration.getProperties()).build();
    SessionFactory sessionFactory = new Configuration().configure("my_hibernate.cfg.xml")
          .buildSessionFactory(serviceRegistry);
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    AppUser user = new AppUser("firstuser");
    session.save(user);

    session.getTransaction().commit();
    session.close();
  }
}
