package me.hupeng.android.monitor.UI;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.hardware.Camera;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import me.hupeng.android.monitor.Mina.MinaUtil;
import me.hupeng.android.monitor.R;
import me.hupeng.android.monitor.Util.ToastUtil;
import java.io.ByteArrayOutputStream;

/**
 * 主功能界面
 * @author HUPENG
 */
public class MainActivity extends Activity implements SurfaceHolder.Callback,Camera.PreviewCallback{
    /**
     * 对Mina库进行的一个封装
     * */
    private MinaUtil minaUtil = null;

    /**
     * 摄像头相关代码
     * */
    private Camera camera = null;
    private SurfaceHolder surfaceHolder = null;
    private SurfaceView surfaceView = null;


    /**
     * 一般控件关联变量
     * */
    private TextView tvSelfIp,tvServerIp;
    private Switch monitorSwitch;

    /**
     * 初始化变量
     * */
    private void init(){
        tvSelfIp = (TextView) findViewById(R.id.tv_self_ip);
        tvServerIp = (TextView) findViewById(R.id.tv_server_ip);
        monitorSwitch = (Switch) findViewById(R.id.swich_monitor);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        try{
            String ipAddress = getIpAddress();
            if (ipAddress.equals("0.0.0.0")){

            }
        }catch (Exception e){
            Log.i("MainActivity", e.getMessage());
        }

        monitorSwitch.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        surfaceView.getHolder().addCallback(MainActivity.this);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    /**
     * 得到本机IP地址
     * */
    private String getIpAddress() {
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String maxText = info.getMacAddress();
        String ipText = intToIp(info.getIpAddress());

        String status = "";
        if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED)
        {
            status = "WIFI_STATE_ENABLED";
        }
        //获取到的各种WIFI信息
        String ssid = info.getSSID();
        int networkID = info.getNetworkId();
        int speed = info.getLinkSpeed();
        return ipText;
    }

    /**
     * 将获取到的Integer类型的IP地址转换成String类型的IP地址
     * */
    private String intToIp(int ip) {
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        try{
            camera = Camera.open();
            camera.setPreviewDisplay(holder);

            Camera.Parameters params = camera.getParameters();
            params.setPreviewSize(352, 288);
            params.setRotation(90);
            camera.setDisplayOrientation(90);

            camera.setParameters(params);
//            camera.startPreview() ;

            camera.setPreviewCallback(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if(camera != null) camera.release() ;
        camera = null ;
    }

    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        Camera.Size size = camera.getParameters().getPreviewSize();
        try {
            YuvImage image = new YuvImage(bytes, ImageFormat.NV21, size.width,
                    size.height, null);

            if (image != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compressToJpeg(new Rect(0, 0, size.width, size.height),
                        70, stream);
                Bitmap bmp = BitmapFactory.decodeByteArray(
                        stream.toByteArray(), 0, stream.size());
//                this.bitmap = rotateBitmapByDegree(bmp,90);
//                if (videoFlag){
//                    sendPic();
//                }else {
//                    bitmap.recycle();
//                }

                stream.close();

            }
        } catch (Exception ex) {
            Log.e("Sys", "Error:" + ex.getMessage());
        }
    }


    /**
     * Switch监听器类
     * */
    class MyOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked){
                //open
                camera.startPreview();
            }else {
                //close
                camera.stopPreview();
            }
        }
    }
}
