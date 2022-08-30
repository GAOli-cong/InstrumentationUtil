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
import android.widget.EditText;
import android.widget.Toast;

import com.glc.instrumentationutils.InstrumentationUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        findViewById(R.id.btn_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "我被模拟点击触发", Toast.LENGTH_SHORT).show();
            }
        });

        EditText editText = findViewById(R.id.edt_msg);
        showSoftInputFromWindow(MainActivity.this,editText);

    }


    /**
     * 页面完全渲染完毕后  进行模拟点击等操作
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){


            InstrumentationUtils.sendClickEvent(573f,1047f);
//
//            //模拟输入
//            sendKeyEventText("instrumentation input");
//            Log.d(TAG, "有焦点: ");
//
//
//
//            //keyEvent
//            sendKeyEvent(4);
        }else {
            Log.d(TAG, "没有焦点: ");
        }


    }

    /**
     * 点击事件
     *
     * @param x 点位 x 轴坐标
     * @param y 点位 y 轴坐标
     */
    private void sendEvent(final float x, final float y) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 发送字符串
     *
     * @param text
     */
    private void sendKeyEventText(final String text) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendStringSync(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 发送普通 keyEvent
     *
     * @param num
     */
    private void sendKeyEvent(final int num) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
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