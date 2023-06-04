package by.potapenko.database.hibernate;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SessionBuilding {
    private final SessionFactory sessionFactory;
    private static final SessionBuilding INSTANCE = new SessionBuilding();

    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public static SessionBuilding getInstance() {
        return INSTANCE;
    }
}
