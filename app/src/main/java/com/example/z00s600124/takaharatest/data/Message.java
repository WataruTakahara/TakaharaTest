package com.example.z00s600124.takaharatest.data;

import android.graphics.Bitmap;

/**
 * メッセージを表現するクラス
 */
public class Message {

    private  String text;
    private  String imagefilePath;

    public Message(String text, String imagefilePath){
        this.text = text;
        this.imagefilePath = imagefilePath;
    }
    public String getText(){
        return text;
    }
    public String getImageFilePath(){
        return imagefilePath;
    }
}
