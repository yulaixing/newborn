package com.kanzhun.chat.broker.firstDemo.session.model;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * @author:xing.yl
 * @date: 18/11/11
 */
public class ChannelSession {

    public static AttributeKey<String> ATTR_KEY_CONNECTION_ID = AttributeKey.valueOf("cid");


    public ChannelSession(long cid,Channel channel){
        this.cid=cid;
        this.channel=channel;


    }

    public ChannelSession(){


    }



    private long cid;
    private Channel channel;


    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
