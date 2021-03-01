package org.jwt.security.impl;

import org.jwt.security.Hash;
import org.jwt.security.Salt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@ApplicationScoped
public class PasswordHash {

    private final Hash hash;

    private final Salt salt;

    @Inject
    public PasswordHash(final Hash hash, final Salt salt) {
        this.hash = hash;
        this.salt = salt;
    }

    public byte[] hash(String data, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return hash.hash(data, salt);
    }

    public byte[] salt() {
        return salt.salt();
    }
}
