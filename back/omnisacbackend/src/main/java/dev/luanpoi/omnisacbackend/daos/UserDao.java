package dev.luanpoi.omnisacbackend.daos;

import dev.luanpoi.omnisacbackend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserDao extends CrudRepository<User, UUID> {

    @Query("from User u where u.email=:email")
    Optional<User> findByEmail(@Param("email") String email);
}
