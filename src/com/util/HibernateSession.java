package com.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Represents a entity which handles the session with the database.
 * 
 * @author Eugenia Pérez Martínez
 */
public class HibernateSession {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static Session session;

	/**
	 * Based on hibernate.cfg.xml configuration creates a SessionFactory
	 * 
	 * @return the session factory
	 */
	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		/*
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();*/
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(/*serviceRegistry*/);
		return sessionFactory;
	}

	/**
	 * This gives the desired session factory
	 * 
	 * @return hibernate Session Factory instance
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Gives the current Session
	 * 
	 * @return Hibernate Session
	 */
	public static Session getSession() {
		if (null == session) {
			session = sessionFactory.openSession();
		}
		return session;
	}
}