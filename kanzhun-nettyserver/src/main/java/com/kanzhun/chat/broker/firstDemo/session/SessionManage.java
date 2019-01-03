package com.kanzhun.chat.broker.firstDemo.session;

import com.kanzhun.chat.broker.firstDemo.session.model.ChannelSession;
import com.kanzhun.chat.broker.firstDemo.utils.IpUtils;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:xing.yl
 * @date: 18/11/11
 */
public class SessionManage {
    private static Map<Long, ChannelSession> map = new ConcurrentHashMap<Long, ChannelSession>();

    private static AtomicInteger sender = new AtomicInteger(1);
    private static int MAX_SENDER_NUM = 1 << 28;


    public void addSession(Channel channel) {
        long cid = generateCid();
        ChannelSession channelSession = new ChannelSession(cid, channel);
        channel.attr(ChannelSession.ATTR_KEY_CONNECTION_ID).set(String.valueOf(cid));
        map.put(cid,channelSession);
    }


    public void sendOtherSession(Channel channel, Object msg) {

        String cid = channel.attr(ChannelSession.ATTR_KEY_CONNECTION_ID).get();

        Set<Map.Entry<Long, ChannelSession>> entries = map.entrySet();

        System.out.println("entries.size() is"+entries.size());
        for (Map.Entry<Long, ChannelSession> entry : entries) {

            System.out.println("entry.getKey()"+entry.getKey());
            if (!entry.getKey().equals(Long.valueOf(cid))) {
                entry.getValue().getChannel().writeAndFlush(msg);
            }

        }


    }


    public void clearSession(Channel channel) {


    }

    private long generateCid() {
        int id = sender.getAndIncrement();
        if (id < 0 || id >= MAX_SENDER_NUM) {
            sender.set(1);
            id = 1;
        }
        long cid = (IpUtils.getLocalInnerIp() + (1l << 32)) << 28 | id;
        return cid;
    }

}
