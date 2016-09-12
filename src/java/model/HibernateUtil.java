package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Filipe
 */
public class HibernateUtil {

    private static final Session SESSION;
    private static final AnnotationConfiguration ANNOTATION_CONFIGURATION;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            ANNOTATION_CONFIGURATION = new AnnotationConfiguration();
            ANNOTATION_CONFIGURATION.addAnnotatedClass(UserModel.class);
            ANNOTATION_CONFIGURATION.addAnnotatedClass(EnderecoModel.class);
            ANNOTATION_CONFIGURATION.addAnnotatedClass(CaronaModel.class);
            ANNOTATION_CONFIGURATION.configure();
            
            SESSION = ANNOTATION_CONFIGURATION.buildSessionFactory().openSession();
            
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void createDB(){
        SchemaExport schemaExport = new SchemaExport(ANNOTATION_CONFIGURATION);
        schemaExport.create(true, true);
    }

    public static Session getSession() {
        return SESSION;
    }
}
