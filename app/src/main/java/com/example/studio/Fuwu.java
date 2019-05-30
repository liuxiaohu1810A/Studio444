package com.example.studio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fuwu extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
 //只能调用一次
    @Override
    public void onCreate() {
        super.onCreate();
      /*  new Thread(){
            @Override
            public void run() {
                try {
                    File file = new File("/sdcard/Pictures/0858c600-1b34-41c1-a2ff-f67cdc376558.png");
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    HttpURLConnection con = (HttpURLConnection) new URL("https://www.wanandroid.com/blogimgs/0858c600-1b34-41c1-a2ff-f67cdc376558.png").openConnection();
                    int length = con.getContentLength();
                    if (length<=0){
                        return;
                    }
                    InputStream in = con.getInputStream();
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] by=new byte[1024*4];
                    int len=0;
                    int str=0;
                    while ((len=in.read(by))!=-1){
                        out.write(by,0,len);
                        str+=len;
                        int i = str * 100 / length;
                        //EventBus传递数据
                        MyEventBus eventBus = new MyEventBus();
                        eventBus.a=i;
                        EventBus.getDefault().postSticky(eventBus);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
    }
    //能调用多次
    private static final String TAG = "Fuwu";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: start方法");
        new Thread(){
                        @Override
                        public void run() {
                            try {
                                File file = new File("/sdcard/Pictures/0858c600-1b34-41c1-a2ff-f67cdc376558.png");
                                if (!file.exists()){
                                    file.createNewFile();
                                }
                                HttpURLConnection con = (HttpURLConnection) new URL("https://www.wanandroid.com/blogimgs/0858c600-1b34-41c1-a2ff-f67cdc376558.png").openConnection();
                                int length = con.getContentLength();
                                if (length<=0){
                                    return;
                                }
                    InputStream in = con.getInputStream();
                    FileOutputStream out = new FileOutputStream(file);
                    byte[] by=new byte[1024*4];
                    int len=0;
                    int str=0;
                    while ((len=in.read(by))!=-1){
                        out.write(by,0,len);
                        str+=len;
                        int i = str * 100 / length;
                        //EventBus传递数据

                        EventBus.getDefault().post(String.valueOf(i));
                    }
                    con.disconnect();
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
