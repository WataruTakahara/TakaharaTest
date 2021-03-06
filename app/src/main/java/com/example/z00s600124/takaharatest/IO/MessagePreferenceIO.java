package com.example.z00s600124.takaharatest.IO;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.z00s600124.takaharatest.R;

import java.util.ArrayList;

/**
 *
 * メッセージが画像対応となったため、このクラスは使わなくなった。
 * 本来は消すべきだが、これは勉強用なので残しておく
 */

/**
メッセージをプリファレンスに保存・読み込み・削除することを管理するクラス

    messageBaseKeyとmessageNextIdKeyを組み合わせ、複数のメッセージをプリファレンスで管理する。
    以下はプリファレンスに保存される内容の例
    <integer name="next_id">2</integer>
    <string name="message0">あああ</string>
    <string name="message1">いいい</string>

 */
public class MessagePreferenceIO extends MessageIO<String>{
    private String fileName;
    private String messageBaseKey;
    private String messageNextIdKey;
    private SharedPreferences messagePrefer;

    /**
        初期化にアクティブなApplicationのContextからプリファレンスの操作に必要な情報を取得する.
         @param activeContext 実行元となるContextクラス.通常はgetApplicationContext()を用いる
     */
    public MessagePreferenceIO(Context activeContext) {
        super();
        fileName = activeContext.getResources().getString(R.string.prefer_message_list_file_name);
        messageBaseKey = activeContext.getResources().getString(R.string.prefer_message_key_name);
        messageNextIdKey = activeContext.getResources().getString(R.string.prefer_message_key_next_id);
        messagePrefer = activeContext.getSharedPreferences(fileName, Application.MODE_PRIVATE);
    }

    /**
   引数で渡された文字列をプリファレンスに追記する
    */
    @Override
    public void addMessage(String message) {
        //プリファレンスに保存されたメッセージの要素数を取得してユニークなキーを生成（message0 , message1 ...)
        int messageCount = getNextInputId();
        String addMessageKey = makeMessageKey(messageCount);

        SharedPreferences.Editor messageEditor = messagePrefer.edit();
        //メッセージを追加し次に挿入するべきIDも1つ増やす
        messageEditor.putString(addMessageKey, message);
        messageEditor.putInt(messageNextIdKey, messageCount + 1);

        messageEditor.apply();
    }

    /**
    全メッセージを取得.
    配列には古いものから順に挿入されている.
     */
    @Override
    public ArrayList<String> getAllMessages() {
        ArrayList<String> allMessages = new ArrayList<>();

        //メッセージの要素数を取得
        int messageCount = getNextInputId();

        for (int i = 0; i < messageCount; i++) {
            String oneMessageValue = getOneMessageValue(i);
            if(oneMessageValue != null) {
                allMessages.add(oneMessageValue);
            }
        }
        return allMessages;
    }

    /**
    全メッセージを消去.
    各メッセージおよび要素数の項目を削除する

     */
    @Override
    public void deleteAllMessages() {
        //メッセージの要素数を取得
        int nextInputNum = getNextInputId();

        SharedPreferences.Editor messageEditor = messagePrefer.edit();
        messageEditor.remove(messageNextIdKey);
        for (int i = 0; i < nextInputNum; i++) {
            String oneMessageKey = makeMessageKey(i);
            messageEditor.remove(oneMessageKey);
        }
        messageEditor.apply();
    }

    /**
    メッセージの要素数を指定して、特定のメッセージのキーを取得する
     */
    private String makeMessageKey(int inputNum) {
        return messageBaseKey + inputNum;
    }

    /**
    メッセージの要素数を指定して、特定のメッセージの内容を取得する.
    存在しない場合はnullが返却される
    */
    private String getOneMessageValue(int inputNum) {
        String oneMessageKey = makeMessageKey(inputNum);
        return messagePrefer.getString(oneMessageKey, null);
    }

    /**
    次に挿入するメッセージのIDを取得.
     */
    private int getNextInputId() {
        return messagePrefer.getInt(messageNextIdKey, 0);
    }
}
