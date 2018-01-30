package com.jiajun.config.netty;

/**
 * Created by SUMMERS on 2018/1/27.
 */
public enum MessageEventEnum {
    CONNECT("连接",MessageBody.class),
    HEARTBEAT("心跳",MessageBody.class),
    BIZ("业务",BizMessageBody.class);

    MessageEventEnum(String description,Class messageBodyClass){
        this.description = description;
        this.messageBodyClass = messageBodyClass;
    }

    private String description;

    private Class messageBodyClass;

    public String getDescription() {
        return description;
    }
}
