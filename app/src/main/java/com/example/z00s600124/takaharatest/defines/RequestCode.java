package com.example.z00s600124.takaharatest.defines;

/**
画面遷移のリクエストコードを定義.
 */
public enum RequestCode {
    FromInput(1000),
    FromGallery(2000)
    ;

    private final int id;

    RequestCode(final int id) {
        this.id = id;
    }

    public int getInt() {
        return this.id;
    }
}