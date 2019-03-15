package com.example.z00s600124.takaharatest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.z00s600124.takaharatest.data.Message;
import com.example.z00s600124.takaharatest.defines.IntentExtraKeys;
import com.example.z00s600124.takaharatest.defines.RequestCode;
import com.example.z00s600124.takaharatest.IO.MessageGsonIO;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Stream;

/**
 * ユーザがテキストボックスに文字を入力し、ボタンの押下でDisplayMessageListActivityに値を送信する.
 */
public class InputActivity extends AppCompatActivity {
    private EditText inputBox;
    private ImageView selectedImage;
    private Bitmap selectedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_message);

        //画像選択
        selectedImage = findViewById(R.id.selected_image);
        Button selectImageBtn = findViewById(R.id.select_image_button);
        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, RequestCode.FromGallery.getInt());
            }
        });

        //送信ボタン
        inputBox = findViewById(R.id.edit_text);
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
                            .addMessage(new Message(sendStr, null));

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.FromGallery.getInt() && resultCode == RESULT_OK) {
            try {

                BufferedInputStream inputStream = new BufferedInputStream(getContentResolver().openInputStream(data.getData()));
                selectedImageBitmap = BitmapFactory.decodeStream(inputStream);
                selectedImage.setImageBitmap(selectedImageBitmap);
                selectedImage.setVisibility(View.VISIBLE);
                saveImageInApp(selectedImageBitmap);
            } catch (Exception e) {
                selectedImageBitmap = null;
            }
        }
    }

    private void saveImageInApp(Bitmap bitmap) {
        FileOutputStream out = null;
        try {
            // openFileOutputはContextのメソッドなのでActivity内ならばthisでOK
            out = this.openFileOutput("image.png", this.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            // エラー処理
            e.printStackTrace();
        } finally {
            closeOutPutStream(out);
        }
    }

    private void closeOutPutStream(OutputStream stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
