package com.suxs.core.shiro.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroUtil {

    public static final String DEFUALT_ENCRYPT_TYPE = "MD5";
    public static final int DEFAULT_HASH_ITERATIONS = 1024;


    public static String md5EncryptPassword(String username, String password) {
        Object salt = ByteSource.Util.bytes(username);
        SimpleHash hash = new SimpleHash("MD5", password, salt, DEFAULT_HASH_ITERATIONS);
        return hash.toString();
    }

}
