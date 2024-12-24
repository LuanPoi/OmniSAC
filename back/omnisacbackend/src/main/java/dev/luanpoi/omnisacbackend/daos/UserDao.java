package dev.luanpoi.omnisacbackend.daos;

import dev.luanpoi.omnisacbackend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserDao extends CrudRepository<User, UUID> {
}
