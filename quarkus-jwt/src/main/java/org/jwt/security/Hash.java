package org.jwt.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface Hash {

    byte[] hash(String data, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
