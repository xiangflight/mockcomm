package com.flight.comm.sender;

import com.flight.comm.ICommunicator;
import com.flight.comm.Role;
import com.flight.msg.IMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/10/12 4:10 下午
 */

public abstract class AbstractSender implements ICommunicator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Role getRole() {
        return Role.SENDER;
    }

    @Override
    public void communicate(IMessage message) {
        logger.debug("I am " + getName() + ", I will send [" + new String(message.getContent()) + "] encrypted by PrivateKey. " +
                "After encrypt, the message is [" + new String(message.getEncrypted()) + "]");
    }
}
