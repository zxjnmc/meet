package com.szx.meet.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @Author szx
 * @Date 2021/7/9 23:35
 * @Description
 */
@Slf4j
public class EncryptionUtils {

    /**
     * 功能描述:SHA加密
     *
     * @param password password
     * @return java.lang.String
     * @author szx
     * @date 2021/7/9 23:38
     */
    public static String encryForSha(String password) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            log.error("加密出现异常:{}", e.getMessage());
        }
        sha.update(password.getBytes());
        return new BigInteger(sha.digest()).toString();
    }
}
