package com.flight.utlis;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/10/12 4:10 下午
 *
 * 弃用
 */

public final class RsaUtils {

    private static final String KEY_STRATEGY = "RSA";
    private static final int KEY_SIZE = 1024;
    private static final int BLOCK = KEY_SIZE / 8;

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_STRATEGY);
        keyPairGenerator.initialize(KEY_SIZE, random);
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_STRATEGY);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] data = message.getBytes();
        int inputLength = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        while (inputLength - offset > 0) {
            if (inputLength - offset > BLOCK) {
                cache = cipher.doFinal(message.getBytes(), offset, BLOCK);
            } else {
                cache = cipher.doFinal(data, offset, inputLength - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * BLOCK;
        }
        return out.toByteArray();
    }

    public static PublicKey getDecodedPublicKey(byte[] publicKey) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decrypt(PublicKey publicKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_STRATEGY);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        int inputLength = encrypted.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        while (inputLength - offset > 0) {
            if (inputLength - offset > BLOCK) {
                cache = cipher.doFinal(encrypted, offset, BLOCK);
            } else {
                cache = cipher.doFinal(encrypted, offset, inputLength - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * BLOCK;
        }
        return out.toByteArray();
    }

    public static void main(String[] args) {
        try {
            String content = "This is a message";
            KeyPair keyPair = RsaUtils.buildKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            final byte[] encrypt = RsaUtils.encrypt(privateKey, content);
            System.out.println(new String(encrypt));
            final byte[] decrypt = RsaUtils.decrypt(publicKey, encrypt);
            System.out.println(new String(decrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
