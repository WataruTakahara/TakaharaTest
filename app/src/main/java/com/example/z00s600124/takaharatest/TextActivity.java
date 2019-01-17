package com.example.z00s600124.takaharatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        //送信ボタン
        Button sendButton = findViewById(R.id.sendTextButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //メイン画面へ
                Intent intent = new Intent();
                EditText editText = findViewById(R.id.edit_text);
                if (editText.getText() != null) {
                    String sendStr = editText.getText().toString();
                    intent.putExtra(MainActivity.MESSAGE, sendStr);
                }

                editText.setText("");

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
