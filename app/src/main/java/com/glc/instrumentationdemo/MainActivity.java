package com.glc.instrumentationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glc.instrumentationutils.InstrumentationUtils;

public class MainActivity extends AppCompatActivity {
    private Button btnHello;
    private EditText editMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHello=findViewById(R.id.btn_hello);
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "我被模拟点击触发", Toast.LENGTH_SHORT).show();
            }
        });

        editMsg=findViewById(R.id.edt_msg);
        showSoftInputFromWindow(MainActivity.this,editMsg);
    }


    /**
     * 页面完全渲染完毕后  进行模拟点击等操作
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
            //模拟点击
            InstrumentationUtils.sendClickEvent(573f,1047f);

            //模拟输入
            InstrumentationUtils.sendTextEvent("instrumentation input");

            //模拟keyEvent   4=返回按钮
            InstrumentationUtils.sendKeyEvent(4);

    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

}