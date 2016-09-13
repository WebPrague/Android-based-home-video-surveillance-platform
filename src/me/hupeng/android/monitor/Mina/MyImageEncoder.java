package me.hupeng.android.monitor.Mina;

import android.graphics.Bitmap;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.io.ByteArrayOutputStream;

/**
 * Created by HUPENG on 2016/9/4.
 */
public class MyImageEncoder implements ProtocolEncoder {

    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput out) throws Exception {
        MyData myData = null;
        if (message instanceof MyData){
            myData = (MyData) message;
        }
        if (myData != null){
            //读取图片到ByteArrayOutputStream

//            CharsetEncoder charsetEncoder = (CharsetEncoder)ioSession.getAttribute("encoder");
//            if(charsetEncoder == null){
//                charsetEncoder = Charset.defaultCharset().newEncoder();
//                ioSession.setAttribute("encoder", charsetEncoder);
//            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            myData.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] picByte = baos.toByteArray();

            int length = picByte.length;

            System.out.println("发送的文件长度：" + length);

            IoBuffer ioBuffer;
            ioBuffer = IoBuffer.allocate(1024).setAutoExpand(true);
            ioBuffer.setAutoShrink(true);
            ioBuffer.setAutoExpand(true);
            ioBuffer.putInt(length);
            ioBuffer.putInt(myData.clientId);
            ioBuffer.put(picByte);
            ioBuffer.capacity(length+8);
            ioBuffer.flip();
            out.write(ioBuffer);

            if (message instanceof MyData){

                myData.bitmap.recycle();
            }
        }
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
