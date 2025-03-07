package com.example.dynamiclistview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class HolidaySongsAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    public static String EXTRA_SELECTED_ITEM = "my.selected.item";
    private Context context;
    private List<HolidaySongs> arrayList;
    private TextView albumName, artistName, danceability, duration_ms;
    private NetworkImageView albumImg;
    private ImageLoader imageLoader;
    private RequestQueue queue;

    public HolidaySongsAdapter(Context context, List<HolidaySongs> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        queue = Volley.newRequestQueue(context);
        queue.start();
        imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String,
                    Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
        });
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_album,
                null);
        albumName = convertView.findViewById(R.id.album_name);
        artistName = convertView.findViewById(R.id.artist_name);
        danceability = convertView.findViewById(R.id.danceability);
        duration_ms = convertView.findViewById(R.id.duration_ms);
        albumImg = (NetworkImageView) convertView.findViewById(R.id.album_img);
        albumName.setText(arrayList.get(position).getAlbum_name());
        artistName.setText(arrayList.get(position).getArtist_name());
        danceability.setText(arrayList.get(position).getDanceability());
        duration_ms.setText(arrayList.get(position).getDuration_ms());
        albumImg.setImageUrl(arrayList.get(position).getAlbum_img(), imageLoader);
        return convertView;
    }

    public void populateView(View view, int index)
    {
        HolidaySongs picked = arrayList.get(index);

        TextView tv = view.findViewById(R.id.album_name);
        tv.setText(picked.getAlbum_name());

        NetworkImageView image = view.findViewById(R.id.album_img);
        image.setImageUrl(picked.getAlbum_img(), imageLoader);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = SongActivity.newIntent(adapterView.getContext(),
                this);
        intent.putExtra(EXTRA_SELECTED_ITEM, i);
        adapterView.getContext().startActivity(intent);
    }

}
