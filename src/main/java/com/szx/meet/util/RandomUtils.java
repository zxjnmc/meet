package com.szx.meet.util;

import java.util.Random;

/**
 * @Author szx
 * @Date 2021/7/10 12:15
 * @Description
 */
public class RandomUtils {

    public static String generateRandomNum(Integer capacity) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
