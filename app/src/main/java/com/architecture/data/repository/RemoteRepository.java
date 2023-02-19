package com.architecture.data.repository;

import android.content.Context;

public class RemoteRepository {

    Context context;

    public RemoteRepository(Context context) {
        this.context = context;
    }

    public String getData() {
        return "I am remote data";
    }
}
