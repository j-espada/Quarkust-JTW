package org.jwt.service.impl;

import org.jwt.api.UserDto;
import org.jwt.repository.dao.UserDao;
import org.jwt.repository.entity.User;
import org.jwt.security.impl.PasswordHash;
import org.jwt.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import java.util.Base64;
import java.util.Optional;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserDao userDao;

    @Inject
    PasswordHash hash;

    @Override
    @Transactional
    public void registerUser(final UserDto userDto) {

        Optional<User> userOptional = this.userDao.getUser(userDto.getUsername());

        if(userOptional.isEmpty()) {

            try {
                String username = userDto.getUsername();
                String password = userDto.getPassword();

                byte[] salt = this.hash.salt();
                byte[] hash = this.hash.hash(password, salt);

                User user = new User();
                user.setUsername(username);
                user.setPassword(Base64.getEncoder().encodeToString(hash));
                user.setSalt(Base64.getEncoder().encodeToString(salt));

                userDao.create(user);
            } catch (final Exception e) {
                throw new InternalServerErrorException(e);
            }
        }
        else {
            throw new BadRequestException("Invalid username");
        }
    }

    @Override
    public boolean login(final String basicAuth) {

        String authInfo = new String(Base64.getDecoder().decode(basicAuth.substring(6)));
        String [] splitInfo = authInfo.split(":");

        String basicUsername = splitInfo[0];
        String basicPassword = splitInfo[1];

        Optional<User> userOp = userDao.getUser(basicUsername);

        if (userOp.isPresent()) {

            User user = userOp.get();
            String dbUsername = user.getUsername();
            String dbPassword = user.getPassword();
            String dbSalt = user.getSalt();

            try {
                byte[] hash = this.hash.hash(basicPassword, Base64.getDecoder().decode(dbSalt));
                String basicPwEncoded = Base64.getEncoder().encodeToString(hash);
                return dbUsername.equals(basicUsername) && dbPassword.equals(basicPwEncoded);
            } catch (final Exception e) {
                throw new InternalServerErrorException(e);
            }
        }

        return false;
    }
}
