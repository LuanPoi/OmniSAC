package dev.luanpoi.omnisacbackend.repositories.impl;

import dev.luanpoi.omnisacbackend.daos.UserDao;
import dev.luanpoi.omnisacbackend.models.User;
import dev.luanpoi.omnisacbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    UserDao userDao;

    @Override
    public User save(User user) {
        return this.userDao.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }
}
