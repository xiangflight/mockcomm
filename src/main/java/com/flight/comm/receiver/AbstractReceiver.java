package com.flight.comm.receiver;

import com.flight.comm.ICommunicator;
import com.flight.comm.Role;
import com.flight.msg.IMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/10/12 4:10 下午
 */

public abstract class AbstractReceiver implements ICommunicator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Role getRole() {
        return Role.RECEIVER;
    }

    @Override
    public void communicate(IMessage message) {
        logger.debug("I am " + getName() + ", I will receive [" + new String(message.getContent()) + "] encrypted by PublicKey. " +
                "After encrypt, the message is [" + new String(message.getDecrypted()) + "]");
    }
}
