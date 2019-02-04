package com.example.z00s600124.takaharatest.defines;

/*
画面遷移のリクエストコードを定義.
 */
public enum RequestCode {
    MessageListToInput(1000),
    ;

    private final int id;

    RequestCode(final int id) {
        this.id = id;
    }

    public int getInt() {
        return this.id;
    }
}