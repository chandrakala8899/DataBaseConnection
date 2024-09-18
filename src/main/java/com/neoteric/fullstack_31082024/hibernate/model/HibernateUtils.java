package com.neoteric.fullstack_31082024.hibernate.model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {
    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {


                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/bank");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "Chandu@0210");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                properties.put(Environment.SHOW_SQL, true);
                properties.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(AccountEntity.class)
                        .addAnnotatedClass(AccountEntityAddress.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);


            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

            return sessionFactory;
        }
    }

