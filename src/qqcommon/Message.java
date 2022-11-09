package qqcommon;
/*
 * @author Curry
 * @version 1.0
 *
 * 表示客户端和服务端通讯时的消息对象
 */

import java.io.Serializable;

//信息对象需要通过网络传输，实现串行化
public class Message implements Serializable {
    private final static long serialVersionUID = 1L;
    private String sender;//发送者
    private String receiver;//接受者
    private String content;//信息内容
    private String sendTime;//发送时间
    private String messageType;//信息类型[可以在接口中定义信息类型]

    public Message(){}

    public Message(String sender, String receiver, String content, String sendTime, String msgType) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sendTime = sendTime;
        this.messageType = msgType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
