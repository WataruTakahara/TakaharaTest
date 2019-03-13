package com.example.z00s600124.takaharatest.manager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.z00s600124.takaharatest.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * gsonでメッセージを管理するクラスです。
 */
public class MessageGsonIO extends MessageIO {
    private String fileName;
    private String messageSaveKey;
    private SharedPreferences messagePrefer;

    public MessageGsonIO(Context activeContext){
        super();
        fileName = activeContext.getResources().getString(R.string.gson_message_list_file_name);
        messageSaveKey = activeContext.getResources().getString(R.string.gson_message_key_name);
        messagePrefer = activeContext.getSharedPreferences(fileName, Application.MODE_PRIVATE);
    }
    @Override
    public void addMessage(String message) {
        ArrayList<String> allMessage = getAllMessages();
        allMessage.add(message);
        messagePrefer.edit().putString(messageSaveKey, new Gson().toJson(allMessage)).apply();
    }

    @Override
    public ArrayList<String> getAllMessages() {

        ArrayList<String> allMessages = new Gson().fromJson(messagePrefer.getString(messageSaveKey, ""), new TypeToken<ArrayList<String>>(){}.getType());
        if(allMessages == null){
            return new ArrayList<>();
        }
        return allMessages;
    }

    @Override
    public void deleteAllMessages() {
       messagePrefer.edit().remove(messageSaveKey).apply();
    }
}
