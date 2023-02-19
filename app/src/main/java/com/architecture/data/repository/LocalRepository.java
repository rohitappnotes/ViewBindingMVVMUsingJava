package com.architecture.data.repository;

import android.content.Context;

public class LocalRepository {

    Context context;

    public LocalRepository(Context context) {
        this.context = context;
    }

    public String getData() {
        return "I am local data";
    }
}
