package org.jwt.security.impl;

import org.jwt.security.Hash;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.Dependent;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Dependent
public class HashImpl implements Hash {

    @Override
    public byte[] hash(String data, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }
}
