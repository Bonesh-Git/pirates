package com.example.wallet.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    private static final Map<String, String> tokenStore = new HashMap<>();

    public static String generateToken(String username) {
        String token = username + "MFT";
        tokenStore.put(token, username);
        return token;
    }

    public static boolean isValid(String token) {
        return tokenStore.containsKey(token);
    }

    public static String getUsername(String token) {
        return tokenStore.get(token);
    }
}
