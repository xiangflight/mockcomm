package com.flight;

import com.flight.comm.receiver.Susan;
import com.flight.comm.sender.Bob;
import com.flight.msg.IMessage;
import com.flight.msg.SimpleMessage;
import com.flight.utlis.RsaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/10/12 3:55 下午
 */

public class MockCommApplication {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        MockCommApplication mockCommApplication = new MockCommApplication();
        try {
            mockCommApplication.mock();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void mock() throws NoSuchAlgorithmException {
        logger.debug("start mock process:");
        String content = "This is a message";
        logger.debug("construct a plain text [" + content + "]");
        logger.debug("construct public key and private key");
        KeyPair keyPair = RsaUtils.buildKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        IMessage message = new SimpleMessage();
        message.setContent(content.getBytes());
        message.setKeyPairs(privateKey, publicKey);
        Bob bob = new Bob();
        bob.communicate(message);
        Susan susan = new Susan();
        susan.communicate(message);
    }
}
