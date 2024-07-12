package com.ntt.clients.services;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class PasswordEncryptor {
    /**
     * Dummy method to 'encrypt' the password, a safe algorithm should be applied here.
     *
     * @return Encrypted password.
     */
    public String encrypt(String plainPassword) {
        byte[] passwordBytes = plainPassword.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(passwordBytes);
    }
}
