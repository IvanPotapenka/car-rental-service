package by.potapenko.database.dao;

import by.potapenko.database.entity.AdminEntity;

public class AdminDao extends Dao<Long, AdminEntity> {
    private static final AdminDao INSTANCE = new AdminDao();

    private AdminDao() {
        super(AdminEntity.class);
    }

    public static AdminDao getInstance() {
        return INSTANCE;
    }
}
