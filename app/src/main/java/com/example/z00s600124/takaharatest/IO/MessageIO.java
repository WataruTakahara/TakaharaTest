package com.example.z00s600124.takaharatest.IO;

import java.util.ArrayList;

/**
メッセージの保存・読み込み・削除を管理する抽象クラス
 * @param <T> メッセージのクラス
 */
abstract public class MessageIO<T> {

    /**
   引数で渡された文字列を追記保存する
    */
    abstract public void addMessage(T message);

    /**
    全メッセージを取得.
    配列には古いものから順に挿入されていること.
     */
    abstract public ArrayList<T> getAllMessages();

    /**
    全メッセージを消去.
     */
    abstract public void deleteAllMessages();
}
