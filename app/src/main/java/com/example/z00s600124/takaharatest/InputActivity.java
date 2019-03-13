package com.example.z00s600124.takaharatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.z00s600124.takaharatest.defines.IntentExtraKeys;
import com.example.z00s600124.takaharatest.manager.MessageGsonIO;

/**
ユーザがテキストボックスに文字を入力し、ボタンの押下でDisplayMessageListActivityに値を送信する.
 */
public class InputActivity extends AppCompatActivity {
    private EditText inputBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        inputBox = findViewById(R.id.edit_text);
        //送信ボタン
        Button sendButton = findViewById(R.id.sendTextButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //メイン画面へ
                Intent intent = new Intent();
                if (inputBox.getText() != null) {
                    String sendStr = inputBox.getText().toString();

                    //入力文字をプリファレンスに保存
                    new MessageGsonIO(getApplicationContext())
                            .addMessage(sendStr);

                    intent.putExtra(IntentExtraKeys.INPUT_VALUE.getKeyName(), sendStr);
                    setResult(RESULT_OK, intent);
                } else {
                    setResult(RESULT_CANCELED, intent);
                }

                finish();
            }
        });

        Log.d("InputActivity", "onCreate");
    }

    /*
    勉強用にすべてのアクティビティライフライクルの各要素でログを出力する
     */

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("InputActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("InputActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("InputActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("InputActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("InputActivity", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("InputActivity", "onRestart");
    }
}
