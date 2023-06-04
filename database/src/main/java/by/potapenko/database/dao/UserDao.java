package by.potapenko.database.dao;

import by.potapenko.database.entity.UserEntity;

public final class UserDao extends Dao<Long, UserEntity> {
    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
        super(UserEntity.class);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}


