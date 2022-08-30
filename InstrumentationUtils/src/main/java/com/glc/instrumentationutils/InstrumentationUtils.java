package com.glc.instrumentationutils;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author glc
 * @date 2022/8/30:12:35
 * @describe 模拟点击工具类
 */
public class InstrumentationUtils {
    private static final String TAG = "InstrumentationUtils";
    /**
     * 点击事件
     * @param x 点位 x 轴坐标
     * @param y 点位 y 轴坐标
     */
    public static void sendClickEvent(final float x, final float y) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
                    inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.e(TAG,"点击事件Error:" + ex.toString());
                }
            }
        }).start();
    }


    /**
     * 长按事件
     * @param x1 点位 x 轴坐标
     * @param y1 点位 y 轴坐标
     */
    public static  void sendLongTouchEvent(final float x1, final float y1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation iso = new Instrumentation();
                float x = x1;
                float y = y1;
                try {
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
                    Thread.sleep(800);
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, x, y, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e(TAG,"长按事件Error:" + e.toString());
                }

            }
        }).start();
    }


    /**
     * 发送普通 keyEvent
     * @param num
     */
    public static  void sendKeyEvent(final int num) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(num);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG,"发送普通 keyEvent:" + e.toString());
                }
            }
        }.start();
    }


    /**
     * 发送字符串
     * @param text
     */
    public static  void sendTextEvent(final String text) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendStringSync(text);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG,"发送字符串error :" + e.toString());
                }
            }
        }.start();
    }


    /**
     * 发送滑动事件
     * @param x1 开始坐标x1
     * @param y1 开始坐标y1
     * @param x2 结束坐标x2
     * @param y2 结束坐标y2
     */
    public static void sendSlideEvent(final float x1, final float y1,final float x2, final float y2){
        //判断上下滑动 or 左右滑动
        float xCha = x2 - x1;
        float yCha = y2 - y1;
        //将得到的差值 转换为正数
        if (xCha < 0) {
            xCha = -xCha;
        }
        if (yCha < 0) {
            yCha = -yCha;
        }
        //判断是否是上下滑动   y2-y1后的正数 与 x2-x1后的正数作比较  如果大于则说明是上下移动 否则是左右移动
        if (yCha > xCha) {
            float moveY = yCha / 90;
            //如果结束点的 y坐标 减去 开始点的 y坐标 是一个正数 则是向下滑动，否则是向上
            if ((y2 - y1) > 0) {
                sendMoveUpOrDown(x1, y1, -moveY);
            } else {
                sendMoveUpOrDown(x1, y1, moveY);
            }
        } else {
            float moveX = xCha / 90;
            //如果结束点的 x坐标 减去 开始点的 x坐标 是一个正数 则是向右滑动，否则是向左
            if ((x2 - x1) > 0) {
                sendMoveRightOrLeft(x1, y1, -moveX);
            } else {
                sendMoveRightOrLeft(x1, y1, moveX);
            }
        }
    }


    /**
     * 上下滑动
     *
     * @param x    开始的坐标 x
     * @param y    开始的坐标 y
     * @param isup 移动的距离
     */
    private static void sendMoveUpOrDown(final float x, final float y, final float isup) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation iso = new Instrumentation();
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, x, y, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 20, MotionEvent.ACTION_MOVE, x, y - 30 * isup, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 40, MotionEvent.ACTION_MOVE, x, y - 60 * isup, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 60, MotionEvent.ACTION_MOVE, x, y - 90 * isup, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 60, MotionEvent.ACTION_UP, x, y - 90 * isup, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG,"上下滑动error :" + e.toString());
                }
            }
        }).start();
    }

    /**
     * 左右滑动
     *
     * @param x    开始的坐标 x
     * @param y    开始的坐标 y
     * @param isup 移动的距离
     */
    private static void sendMoveRightOrLeft(final float x, final float y, final float isup) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation iso = new Instrumentation();
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, x, y, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 20, MotionEvent.ACTION_MOVE, x - 30 * isup, y, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 40, MotionEvent.ACTION_MOVE, x - 60 * isup, y, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 60, MotionEvent.ACTION_MOVE, x - 90 * isup, y, 0));
                    iso.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 60, MotionEvent.ACTION_UP, x - 90 * isup, y, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG,"左右滑动error :" + e.toString());
                }

            }
        }).start();
    }
}
