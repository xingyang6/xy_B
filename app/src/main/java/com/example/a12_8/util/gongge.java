package com.example.a12_8.util;

public class gongge {
    public static long lastclick;
    public static boolean isFast(){
        long time=System.currentTimeMillis();
        long mtime=time-lastclick;
        if (mtime>0&&mtime<800){
            return true;
        }
        lastclick=time;
        return false;

    }
}
