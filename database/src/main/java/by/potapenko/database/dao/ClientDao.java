package by.potapenko.database.dao;

import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.entity.UserEntity;

public class ClientDao extends Dao<UserEntity, ClientEntity> {
    private static final ClientDao INSTANCE = new ClientDao();

    private ClientDao() {
        super(ClientEntity.class);
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }
}
