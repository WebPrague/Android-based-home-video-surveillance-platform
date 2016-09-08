package me.hupeng.android.monitor.UI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import me.hupeng.android.monitor.R;

/**
 * 用于配置的Activity
 * @author HUPENG
 */
public class ConfigActivity extends Activity{
    private EditText etServerIp;
    private Button btn_save;

    private void init(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        init();
    }
}
