package me.hupeng.android.monitor.Mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by HUPENG on 2016/9/4.
 */
public class MyImageFactory implements ProtocolCodecFactory {
    private MyImageEncoder myImageEncoder;
    private MyImageDecoder myImageDecoder;

    public MyImageFactory(){
        myImageEncoder = new MyImageEncoder();
        myImageDecoder = new MyImageDecoder();

    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return myImageEncoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return myImageDecoder;
    }
}
