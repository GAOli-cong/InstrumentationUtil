# InstrumentationUtil
## 使用

#### 添加依赖：

**Step 1.** 在settings.gradle中添加

```css
maven { url 'https://jitpack.io' }
```

**Step 2.** Add the dependency

```css
dependencies {
	        implementation 'com.github.GAOli-cong:InstrumentationUtil:1.0.1'
	}
```

#### 相关使用方法

##### 一、点击事件

```
//点击事件
InstrumentationUtils.sendClickEvent(x,y);

 /**
     * 点击事件
     * @param x 点位 x 轴坐标
     * @param y 点位 y 轴坐标
     */
public static void sendClickEvent(final float x, final float y)
```

##### 二、长按事件

```
//长按事件
InstrumentationUtils.sendLongTouchEvent(x,y);

 /**
     * 长按事件
     * @param x1 点位 x 轴坐标
     * @param y1 点位 y 轴坐标
     */
    public static  void sendLongTouchEvent(final float x1, final float y1)
```

##### 三、发送滑动事件

```
//滑动事件
InstrumentationUtils.sendSlideEvent(x1,y1,x2,y2);

 /**
     * 发送滑动事件
     * @param x1 开始坐标x1
     * @param y1 开始坐标y1
     * @param x2 结束坐标x2
     * @param y2 结束坐标y2
     */
    public static void sendSlideEvent(final float x1, final float y1,final float x2, final float y2)
```

##### 四、发送KeyEvent事件

```
//keyEvent  4==返回按钮
InstrumentationUtils.sendKeyEvent(4);

 /**
     * 发送普通 keyEvent
     * @param num
     */
    public static  void sendKeyEvent(final int num)
```

##### 五、发送字符串输入事件

```
//字符串
InstrumentationUtils.sendTextEvent("hello world");

/**
     * 发送字符串
     * @param text
     */
    public static  void sendTextEvent(final String text)
```

