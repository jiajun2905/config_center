package com.jiajun.config.netty;

/**
 * Created by SUMMERS on 2018/1/27.
 */
public enum MessageEventEnum {
    CONNECT(1,"连接",NettyMessage.class),
    HEARTBEAT(2,"心跳",NettyMessage.class),
    BIZ(3,"业务",BizMessage.class);

    MessageEventEnum(int code ,String description,Class messageClass){
        this.code = code;
        this.description = description;
        this.messageClass = messageClass;
    }

    private String description;

    private Class messageClass;

    private Integer code;

    public String getDescription() {
        return description;
    }

    public static MessageEventEnum valueOfCode(Integer code){
        for(MessageEventEnum event : MessageEventEnum.values()){
            if (event.code == code){
                return event;
            }
        }
        return null;
    }

    public Class getMessageClass() {
        return messageClass;
    }

    public Integer getCode() {
        return code;
    }
}
