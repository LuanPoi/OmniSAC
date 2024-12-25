package dev.luanpoi.omnisacbackend.repositories;

import dev.luanpoi.omnisacbackend.daos.UserDao;
import dev.luanpoi.omnisacbackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    UserDao userDao;

    public User save(User user) {
        return this.userDao.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }
}
