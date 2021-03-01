package org.jwt.repository.dao;

import org.jwt.repository.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    List<User> getAllUsers();

    Optional<User> getUser(String userName);
}
