package com.example.listviewwithimages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class UserActivity extends AppCompatActivity {
    static UserAdapter adapter;
    public static Intent newIntent(Context packageContext, UserAdapter adapterRef) {
        Intent i = new Intent(packageContext, UserActivity.class);
        adapter = adapterRef;
        return i;
    }
    @Override
    protected void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        int index = getIntent().getIntExtra(UserAdapter.EXTRA_SELECTED_ITEM, -1);
        if (index >= 0) {
            adapter.populateView(findViewById(R.id.userLayout), index);
        }
    }
}
