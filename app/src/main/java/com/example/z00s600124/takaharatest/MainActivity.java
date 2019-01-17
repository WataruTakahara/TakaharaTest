package com.example.z00s600124.takaharatest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.TypedValue;
import android.view.ViewGroup.MarginLayoutParams;

public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE = "MyFirstAndroidApp.MESSAGE";
    public static int MESSAGE_INPUT_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_main);
        //右下の＋ボタン
        FloatingActionButton fab =  findViewById(R.id.myfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //テキスト入力画面へ遷移
                Intent intent = new Intent(MainActivity.this, TextActivity.class);
                int requestCode = MESSAGE_INPUT_REQUEST_CODE;
                startActivityForResult( intent, requestCode );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //  TextActivityからの返しの結果を受け取る
    protected void onActivityResult( int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //結果がnullであれば何もしない
        if(null == intent){
            return;
        }

        //入力画面で「入力」ボタンが押された
        if(resultCode == RESULT_OK && requestCode == MESSAGE_INPUT_REQUEST_CODE) {
            String res = intent.getStringExtra(MainActivity.MESSAGE);
            //入力されたメッセージが空の場合、何もしない
            if(res.length() == 0){
                return;
            }
            //表示用リストのレイアウト
            LinearLayout listLayout = findViewById(R.id.text_list_layout);
            TextView textView = new TextView(this);
            //textView.setTextSize(getResources().getDimension( R.dimen.textSize_list ));//これだと文字が大きくなる（ユーザ設定が反映されるため）
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textSize_list));
            textView.setBackground(getDrawable(R.drawable.message_frame_style));
            //マージン、パディングを設定
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(params.leftMargin +5,10,params.rightMargin +5,10);
            textView.setLayoutParams(params);
            textView.setPadding(15,10,15,10);
            //入力されたメッセージを追加
            textView.setText(res);
            listLayout.addView(textView);
        }
    }
}
