package org.jwt.service;

import org.jwt.api.UserDto;

public interface UserService {

    void registerUser(UserDto userDto);

    boolean login(String basicAuth);
}
