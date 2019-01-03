package com.kanzhun.deencoder;

import com.kanzhun.model.Header;
import com.kanzhun.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

/**
 * @author:xing.yl
 * @date: 19/1/2
 */
public class MessageDecoder extends ByteToMessageDecoder {

    /**数据包长度**/
    public static final int HEAD_LENGHT = 45;
    /**标志头**/
    public static final byte PACKAGE_TAG = 0x01;


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {

        buffer.markReaderIndex();

        if (buffer.readableBytes() < HEAD_LENGHT) {
            throw new CorruptedFrameException("包长度问题");
        }

        byte tag = buffer.readByte();
        if (tag != PACKAGE_TAG) {
            throw new CorruptedFrameException("标志错误");
        }

        byte encode = buffer.readByte();
        byte encrypt = buffer.readByte();
        byte extend1 = buffer.readByte();
        byte extend2 = buffer.readByte();

        byte sessionByte[] = new byte[32];
        buffer.readBytes(sessionByte);
        String sessionid = new String(sessionByte,"UTF-8");
        int length = buffer.readInt();
        int command=buffer.readInt();
        Header header = new Header(tag,encode, encrypt, extend1, extend2, sessionid, length, command);
        byte[] data=new byte[length];
        buffer.readBytes(data);
        Message message = new Message(header,new String(data,"UTF-8"));
        out.add(message);
    }


    public static void main(String[] args) {

        ByteBuf buffer = Unpooled.buffer(10);

        buffer.writeInt(1);

//        buffer.markReaderIndex();


        int i = buffer.readInt();
        System.out.println(i);

//        buffer.resetReaderIndex();

         i = buffer.readInt();
        System.out.println(i);



    }

}
