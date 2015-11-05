package com.tlhhub.tutorial;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tlhhub.tutorial.entity.AppUser;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    public void testApp() {
        SessionFactory sessionFactory = new Configuration().configure()
            .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AppUser user = new AppUser("firstuser");
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}
