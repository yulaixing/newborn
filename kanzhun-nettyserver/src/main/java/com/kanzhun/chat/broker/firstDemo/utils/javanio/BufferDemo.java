package com.kanzhun.chat.broker.firstDemo.utils.javanio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author:xing.yl
 * @date: 18/12/28
 *
 * 通道channel
 * 缓冲区 buffer
 * 选择器 selector
 *
 */
public class BufferDemo {

    private static void testReadBuffer() {

        try {

            RandomAccessFile aFile = new RandomAccessFile("/Users/chensi/data/newborn/kanzhun-nettyserver/src/resources/test.txt", "rw");

            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int read = inChannel.read(buf);

            while(read!=-1){

                buf.flip();

                while(buf.hasRemaining()){
                    char c = (char) buf.get();
                    System.out.print(c+"");
                }

                buf.rewind();

                System.out.println("");

                buf.mark();

                while(buf.hasRemaining()){
                    char c = (char) buf.get();
                    System.out.print(c);
                }

                buf.reset();

                System.out.println("");

                while(buf.hasRemaining()){
                    char c = (char) buf.get();
                    System.out.print(c);
                }

                buf.clear();
                read= inChannel.read(buf);
            }


//            buf.put("\nabc".getBytes());
//            buf.flip();
//            inChannel.write(buf);
            aFile.close();



        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        testReadBuffer();

    }

}
