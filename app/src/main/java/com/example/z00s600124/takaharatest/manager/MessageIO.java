package com.example.z00s600124.takaharatest.manager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.z00s600124.takaharatest.R;

import java.util.ArrayList;

/**
メッセージの保存・読み込み・削除を管理する抽象クラス

 */
abstract public class MessageIO {

    /**
   引数で渡された文字列を追記保存する
    */
    abstract public void addMessage(String message);

    /**
    全メッセージを取得.
    配列には古いものから順に挿入されていること.
     */
    abstract public ArrayList<String> getAllMessages();

    /**
    全メッセージを消去.
     */
    abstract public void deleteAllMessages();
}
