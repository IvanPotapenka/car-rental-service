package by.potapenko.service;

import by.potapenko.database.entity.ClientEntity;
import by.potapenko.database.repositpry.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Optional<ClientEntity> create(ClientEntity client) {
        clientRepository.save(client);
        return Optional.of(client);
    }

    public List<ClientEntity> findAll(int limit, int page) {
        return (List<ClientEntity>) clientRepository.findAll(Pageable.ofSize(limit).withPage(page));
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public Optional<ClientEntity> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Integer getCount(Double limit) {
        return (Integer) (int) Math.ceil(clientRepository.count() / limit);
    }
}

