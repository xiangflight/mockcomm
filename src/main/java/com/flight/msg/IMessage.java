package com.flight.msg;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/10/12 6:35 下午
 */

public interface IMessage {

    /**
     * 设置公私钥
     *
     * @param privateKey 私钥
     * @param publicKey 公钥
     */
    void setKeyPairs(PrivateKey privateKey, PublicKey publicKey);

    /**
     * 设置内容
     *
     * @param content 内容
     */
    void setContent(byte[] content);

    /**
     * 消息内容
     *
     * @return 消息内容
     */
    byte[] getContent();

    /**
     * 加密后的内容
     *
     * @return 加密后的内容
     */
    byte[] getDecrypted();

    /**
     * 解密后的内容
     *
     * @return 解密后的内容
     */
    byte[] getEncrypted();

}
