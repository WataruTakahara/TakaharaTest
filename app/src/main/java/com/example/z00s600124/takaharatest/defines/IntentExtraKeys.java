package com.example.z00s600124.takaharatest.defines;

import com.example.z00s600124.takaharatest.R;

/**
intentのgetStringExtra()やputExtra()で設定するキーを定義.
 */
public enum IntentExtraKeys {
    INPUT_VALUE("INPUT_VALUE"),
    ;
    private  String keyName;

    IntentExtraKeys(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return this.keyName;
    }
}

