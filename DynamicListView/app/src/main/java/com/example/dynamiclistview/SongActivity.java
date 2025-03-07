package com.example.dynamiclistview;
// SongActivityは、アルバムをクリックしたときに表示されるレイアウトに基づいてる。
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SongActivity extends AppCompatActivity {
    static HolidaySongsAdapter adapter;
    public static Intent newIntent(Context packageContext, HolidaySongsAdapter adapterRef) {
        Intent i = new Intent(packageContext, SongActivity.class);
        adapter = adapterRef;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist_layout);

        int index = getIntent().getIntExtra(HolidaySongsAdapter.EXTRA_SELECTED_ITEM, -1);
        if (index >= 0) {
            adapter.populateView(findViewById(R.id.playlistLayout), index);
        }
    }
}
