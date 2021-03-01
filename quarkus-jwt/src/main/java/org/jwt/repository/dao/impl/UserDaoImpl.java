package org.jwt.repository.dao.impl;

import org.jwt.repository.dao.UserDao;
import org.jwt.repository.entity.User;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;

@ApplicationScoped
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> getAllUsers() {
        return findAll();
    }

    @Override
    public Optional<User> getUser(final String userName) {

        try {

            User user = em.createNamedQuery("findUser", User.class)
                    .setParameter("username", userName)
                    .getSingleResult();

            return Optional.of(user);

        } catch (final NoResultException e) {
            return Optional.empty();
        }
    }
}
