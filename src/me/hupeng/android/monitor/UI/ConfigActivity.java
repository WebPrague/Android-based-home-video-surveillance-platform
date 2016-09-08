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

    private void init(){
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        etServerIp = (EditText) findViewById(R.id.et_server_address);
        btn_save = (Button) findViewById(R.id.btn_save_config);
        //绑定单击回调
        btn_save.setOnClickListener(new MyOnClickListener());

        etServerIp.setText(getServerIp());
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
            ToastUtil.toast(ConfigActivity.this, "配置写入完成");
        }
    }

    /**
     * 得到服务器端的IP地址
     * */
    private String getServerIp(){
        String tempServerIp = SharedPreferencesUtil.readString(ConfigActivity.this, "server");
        return (tempServerIp==null || tempServerIp.equals("")) ? "0.0.0.0" : tempServerIp;
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
}
