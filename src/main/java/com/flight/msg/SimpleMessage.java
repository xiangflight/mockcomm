package com.flight.msg;

import com.flight.utlis.RsaUtils;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/10/12 6:37 下午
 */

public class SimpleMessage implements IMessage {

    private byte[] content;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Override
    public void setKeyPairs(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @Override
    public byte[] getContent() {
        return content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public byte[] getDecrypted() {
        try {
            byte[] decrypt = RsaUtils.decrypt(publicKey, getEncrypted());
            setContent(decrypt);
            return decrypt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] getEncrypted() {
        try {
            byte[] encrypt = RsaUtils.encrypt(privateKey, new String(content));
            setContent(encrypt);
            return encrypt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
