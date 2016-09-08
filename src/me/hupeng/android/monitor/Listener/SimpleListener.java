package me.hupeng.android.monitor.Listener;

import org.apache.mina.core.session.IoSession;

/**
 * Created by HUPENG on 2016/9/6.
 */
public interface SimpleListener {
    public void onReceive(Object obj, IoSession ioSession);
}
