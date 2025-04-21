package com.example.finalwork02.utils;

import java.security.SecureRandom;

public class EntryNumberGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    public static String generateUniqueCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }

    public static void main(String[] args) {
        // 生成并打印一个唯一的六位加课码
        String courseCode = generateUniqueCode();
        System.out.println("生成的加课码: " + courseCode);
    }
}
