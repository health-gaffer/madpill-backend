package cn.edu.nju.madpill.utils;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author Aneureka
 * @createdAt 2020-02-17 18:46
 * @description Simple crytor using base64-xor.
 **/

@Component
public class Base64XORCodec {

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

    private static byte[] base64Decode(String s) throws IOException {
        BASE64Decoder d = new BASE64Decoder();
        return d.decodeBuffer(s);
    }

    private static String base64Encode(byte[] bytes) {
        BASE64Encoder enc = new BASE64Encoder();
        return enc.encode(bytes).replaceAll("\\s", "");
    }

}
