package com.gmail.mileshko.lesya.schedule.security;

import java.util.UUID;

public class TokenGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
