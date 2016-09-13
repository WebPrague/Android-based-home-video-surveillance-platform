package me.hupeng.android.monitor.UI;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import me.hupeng.android.monitor.R;
import me.hupeng.android.monitor.Util.SharedPreferencesUtil;
import me.hupeng.android.monitor.Util.ToastUtil;

/**
 * 用于配置的Activity
 * @author HUPENG
 */
public class ConfigActivity extends Activity{
    private EditText etServerIp;
    private Button btn_save;
    private EditText etClientId;
    private EditText etAngle;

    private void init(){
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        etServerIp = (EditText) findViewById(R.id.et_server_address);
        btn_save = (Button) findViewById(R.id.btn_save_config);
        etClientId = (EditText) findViewById(R.id.et_client_id);
        etAngle = (EditText) findViewById(R.id.et_angle);

        //绑定单击回调
        btn_save.setOnClickListener(new MyOnClickListener());

        etServerIp.setText(getServerIp());
        etClientId.setText(SharedPreferencesUtil.readInt(ConfigActivity.this,"client_id")+"");
        etAngle.setText(getAngle()+"");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        init();
    }

    /**
     * 实现单机事件的回调
     * */
    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String tempServerAddr = etServerIp.getText().toString();
            SharedPreferencesUtil.writeString(ConfigActivity.this, "server", tempServerAddr);
            String tempClientId = etClientId.getText().toString();
            try{
                int clientId = Integer.parseInt(tempClientId);
                SharedPreferencesUtil.writeInt(ConfigActivity.this, "client_id", clientId);
                int angle = Integer.parseInt(etAngle.getText().toString());
                SharedPreferencesUtil.writeInt(ConfigActivity.this, "angle" , angle);
            }catch (Exception e){

            }
            ToastUtil.toast(ConfigActivity.this, "配置写入完成");
        }
    }

    /**
     * 得到服务器端的IP地址
     * */
    private String getServerIp(){
        String tempServerIp = SharedPreferencesUtil.readString(ConfigActivity.this, "server");
        return (tempServerIp==null || tempServerIp.equals("")) ? "183.175.12.160" : tempServerIp;
    }

    /**
     * 监听action bar返回事件
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private int getAngle(){
        int angle = SharedPreferencesUtil.readInt(ConfigActivity.this, "angle",-1);
        return angle==-1? 90 : angle;
    }
}
