package org.jwt.security.impl;

import org.jwt.security.Salt;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.security.SecureRandom;

@Dependent
public class SaltImpl implements Salt {

    private final SecureRandom secureRandom;

    @Inject
    public SaltImpl() {
        this.secureRandom = new SecureRandom();
    }

    @Override
    public byte[] salt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
