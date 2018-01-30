package com.jiajun.config.netty;

/**
 * Created by SUMMERS on 2018/1/27.
 */
public class NettyMessage {

    private MessageEventEnum type;

    private String src;

    private Long timestamp;

    public MessageEventEnum getType() {
        return type;
    }

    public void setType(MessageEventEnum type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
