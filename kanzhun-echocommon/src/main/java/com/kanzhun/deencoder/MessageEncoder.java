package com.kanzhun.deencoder;

import com.kanzhun.model.Header;
import com.kanzhun.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author:xing.yl
 * @date: 19/1/2
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        Header header = msg.getHeader();
        out.writeByte(MessageDecoder.PACKAGE_TAG);
        out.writeByte(header.getEncode());
        out.writeByte(header.getEncrypt());
        out.writeByte(header.getExtend1());
        out.writeByte(header.getExtend2());
        out.writeBytes(header.getSessionid().getBytes());
        out.writeInt(header.getLength());
        out.writeInt(header.getCommand());
        out.writeBytes(msg.getData().getBytes("UTF-8"));

    }
}
