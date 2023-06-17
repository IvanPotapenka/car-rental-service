package by.potapenko.database.dao;

import by.potapenko.database.entity.UserEntity;
import org.hibernate.cfg.Configuration;

public class AdminDao extends Dao<Long, UserEntity> {
    private static final AdminDao INSTANCE = new AdminDao();

    private AdminDao() {
        super(UserEntity.class);
    }

    public static AdminDao getInstance() {
        return INSTANCE;
    }
}
