package by.potapenko.database.dao;

import by.potapenko.database.entity.ClientEntity;

public class ClientDao extends Dao<Long, ClientEntity> {
    private static final ClientDao INSTANCE = new ClientDao();

    private ClientDao() {
        super(ClientEntity.class);
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }
}
