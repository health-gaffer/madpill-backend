package cn.edu.nju.madpill.utils;

import cn.edu.nju.madpill.exception.BaseException;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author Aneureka
 * @createdAt 2020-02-17 18:46
 * @description Simple crytor using base64-xor.
 **/
public class Base64XORCodec {

    private static final String KEY = "madpill";

    public static String encrypt(String src) {
        return base64Encode(xorWithKey(src.getBytes(), KEY.getBytes()));
    }

    public static String decrypt(String src) throws IOException {
        return new String(xorWithKey(base64Decode(src), KEY.getBytes()));
    }

    private static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i%key.length]);
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

    public static void main(String[] args) throws IOException {
        String data = "04378R4H1Ht1830Abu4H1ANr4H178R4E";
        System.out.println(encrypt(data));
        System.out.println(decrypt(encrypt(data)));
    }
}
