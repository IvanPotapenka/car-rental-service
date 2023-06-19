package by.potapenko.service;

import by.potapenko.database.entity.UserEntity;
import by.potapenko.database.repositpry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> create(UserEntity user) {
        userRepository.save(user);
        return Optional.of(user);
    }

    public List<UserEntity> findAll(int limit, int page) {
        return (List<UserEntity>) userRepository.findAll(Pageable.ofSize(limit).withPage(page));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Integer getCount(Double limit) {
        return (Integer) (int) Math.ceil(userRepository.count() / limit);
    }
}

