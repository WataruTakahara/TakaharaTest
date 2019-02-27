package com.example.z00s600124.takaharatest.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.example.z00s600124.takaharatest.R;

/**
 * メッセージ一覧画面に表示されるテキストのカスタムビュークラス.
 */
public class DisplayMessageTextView extends AppCompatTextView {
    /**
     * @param context 　通常はgetApplicationContext()を用いる
     * @param message 表示するメッセージ文
     */
    public DisplayMessageTextView(Context context, String message) {
        super(context);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textSize_list));
        setBackground(context.getDrawable(R.drawable.message_frame_style));

        //マージン、パディングを設定.今後values/stylesで再設定予定
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(params.leftMargin + 5, 10, params.rightMargin + 5, 10);
        setLayoutParams(params);
        setPadding(15, 10, 15, 10);
        //入力されたメッセージを追加
        setText(message);

    }

}