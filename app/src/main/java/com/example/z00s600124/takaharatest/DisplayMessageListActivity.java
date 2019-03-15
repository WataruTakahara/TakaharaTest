package com.example.z00s600124.takaharatest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.z00s600124.takaharatest.data.Message;
import com.example.z00s600124.takaharatest.defines.IntentExtraKeys;
import com.example.z00s600124.takaharatest.defines.RequestCode;
import com.example.z00s600124.takaharatest.IO.MessageGsonIO;
import com.example.z00s600124.takaharatest.view.DisplayMessageTextView;

import java.util.ArrayList;

/**
 * ユーザが入力した文字列を一覧で表示するためのアクティビティ.
 * 起動時にこの画面が最初に呼び出される.
 */
public class DisplayMessageListActivity extends AppCompatActivity {
    private LinearLayout listLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message_list);

        listLayout = findViewById(R.id.text_list_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //右下の＋ボタン
        FloatingActionButton displayInputActivityButton = findViewById(R.id.display_input_activity);
        displayInputActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInputActivity();
            }
        });
        //メッセージの初期化
        initMessageForPreference();
        Log.d("DisplayMessageListActivity", "onCreate");

    }

    /**
     * プリファレンスに保存されたメッセージを元に画面に反映させる
     */
    private void initMessageForPreference() {

        MessageGsonIO io = new MessageGsonIO(getApplicationContext());
        ArrayList<Message> allMessages = io.getAllMessages();

        //画面からメッセージを全て消去し、改めてメッセージを最初から追加していく
        deleteAllMessageListFromLayout();

        for (Message oneMessage : allMessages) {
            addMessageToLayout(oneMessage.getText());
        }
    }

    /**
     * アクションバーを表示する
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * オプションメニューのアイテムの押下時に実行
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedId = item.getItemId();

        switch (selectedId) {
            case R.id.action_delete_all://全て消去
                confirmDeleteAllMessageList();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 消去してよいかのダイアログを表示.
     */
    private void confirmDeleteAllMessageList() {
        //Builderパターンに変更
        new AlertDialog.Builder(DisplayMessageListActivity.this)
                .setTitle(R.string.dialog_delete_all_title)
                .setMessage(R.string.dialog_delete_all_text)
                .setPositiveButton(R.string.dialog_delete_all_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAllMessageListDialogSelectedOk();
                    }
                })
                .setNegativeButton(R.string.dialog_delete_all_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //キャンセルの場合、何もしない
                    }
                })
                .create().show();
    }

    /**
     * 全て消去ダイアログで「はい」が選ばれた.
     * 画面およびプレファレンスからメッセージを削除し、トーストを表示
     */
    private void deleteAllMessageListDialogSelectedOk() {
        deleteAllMessageListFromLayout();
        MessageGsonIO io = new MessageGsonIO(getApplicationContext());
        io.deleteAllMessages();

        Toast toast = Toast.makeText(DisplayMessageListActivity.this, R.string.toast_delete_all, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * ユーザが入力した文字列の一覧を全て消去する
     * 注意：プレファレンスの内容は変更されない
     */
    private void deleteAllMessageListFromLayout() {
        listLayout.removeAllViews();
    }

    /**
     * 画面遷移の通知を受け取る
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //入力画面で「入力」ボタンが押されてメッセージ一覧画面に戻ってきたとき
        if (requestCode == RequestCode.FromInput.getInt() && resultCode == RESULT_OK && intent != null) {
            String inputValue = intent.getStringExtra(IntentExtraKeys.INPUT_VALUE.getKeyName());
            addMessageToLayout(inputValue);
        }
        Log.d("DisplayMessageListActivity", "onActivityResult");

    }


    /**
     * 入力画面へ遷移する
     */
    private void startInputActivity() {
        Intent intent = new Intent(DisplayMessageListActivity.this, InputActivity.class);
        startActivityForResult(intent, RequestCode.FromInput.getInt());
    }


    /**
     * メッセージをレイアウトに追加して表示する
     *
     * @param message
     */
    private void addMessageToLayout(String message) {
        if(!allowAddMessage(message)) {
            return;
        }
        listLayout.addView(new DisplayMessageTextView(getApplicationContext(),message));
    }

    /**
     * メッセージ一覧画面に追加できる場合、trueを返す
     * @param message
     * @return
     */
    private boolean allowAddMessage(String message){
        //入力されたメッセージが空の場合、許可しない
        if (message.length() == 0) {
            return false;
        }
        return true;
    }
    /*
    勉強用にすべてのアクティビティライフライクルの各要素でログを出力する
    */

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DisplayMessageListActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DisplayMessageListActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DisplayMessageListActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DisplayMessageListActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DisplayMessageListActivity", "onDestroy");
    }

    @Override
    protected void onRestart() {
        Log.d("DisplayMessageListActivity", "onRestart1");
        super.onRestart();
        Log.d("DisplayMessageListActivity", "onRestart2");
    }
}