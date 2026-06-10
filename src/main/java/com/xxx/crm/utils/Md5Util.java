package com.xxx.crm.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * MD5 加密工具类
 */
public class Md5Util {

    /**
     * MD5 加密，返回 32 位小写十六进制字符串（推荐，最常用）
     */
    public static String hex(String msg) {
        if (msg == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(msg.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(32);
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * MD5 加密，返回 Base64 编码字符串
     */
    public static String encode(String msg) {
        if (msg == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(msg.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("123456 -> hex: " + Md5Util.hex("123456"));
        System.out.println("123456 -> base64: " + Md5Util.encode("123456"));
    }
}