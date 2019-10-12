package com.flight.comm;

import com.flight.msg.IMessage;

/**
 * @author xiangdotzhaoAtwoqutechcommacom
 * @date 2019/10/12 4:06 下午
 */

public interface ICommunicator {

    /**
     * 通信者的姓名
     *
     * @return 姓名
     */
    String getName();

    /**
     * 通信者的角色
     *
     * @return 发送方还是接收方
     */
    Role getRole();

    /**
     * 通信过程
     *
     * @param message 通信内容
     */
    void communicate(IMessage message);

}
