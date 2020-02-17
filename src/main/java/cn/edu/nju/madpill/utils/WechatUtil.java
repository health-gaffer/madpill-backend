package cn.edu.nju.madpill.utils;

import com.sun.javafx.binding.StringFormatter;

import java.io.IOException;

/**
 * @author Aneureka
 * @createdAt 2020-02-17 15:25
 * @description
 **/
public class WechatUtil {

    public static String token2openId(String token) {
        try {
            String[] data = Base64XORCodec.decrypt(token).split(" ");
            if (data.length == 2) {
                return data[0];
            } else {
                throw new IllegalArgumentException("Invalid token.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid token.");
        }
    }

    public static String generateToken(String openId, String sessionKey) {
        String data = String.format("%s %s", openId, sessionKey);
        return Base64XORCodec.encrypt(data);
    }
}
