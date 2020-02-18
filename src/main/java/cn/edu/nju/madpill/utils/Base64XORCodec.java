package cn.edu.nju.madpill.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

/**
 * @author Aneureka
 * @createdAt 2020-02-17 18:46
 * @description Simple crytor using base64-xor.
 **/

@Component
public class Base64XORCodec {

    private Base64XORCodec() {
    }

    public static String encrypt(String src, String key) {
        return base64Encode(xorWithKey(src.getBytes(), key.getBytes()));
    }

    public static String decrypt(String src, String key) throws IOException {
        return new String(xorWithKey(base64Decode(src), key.getBytes()));
    }

    private static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i % key.length]);
        }
        return out;
    }

    private static byte[] base64Decode(String s) {
        Base64.Decoder d = Base64.getDecoder();
        return d.decode(s);
    }

    private static String base64Encode(byte[] bytes) {
        Base64.Encoder enc = Base64.getEncoder();
        return new String(enc.encode(bytes)).replaceAll("\\s", "");
    }

}
