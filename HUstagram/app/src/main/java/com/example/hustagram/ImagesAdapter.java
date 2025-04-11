package com.example.hustagram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;


public class ImagesAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    List<Images> imagesList;
    private Context context;
    ImageView image;

    public ImagesAdapter(Context context, List<Images> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }


    @Override
    public int getCount() {
        return null == imagesList ? 0 : imagesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null == imagesList ? null: imagesList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView = LayoutInflater.from(context).inflate(R.layout.grid_items,
                viewGroup, false);
        image = mView.findViewById(R.id.imagePost);
        // decode base64 string
        byte[] bytes= Base64.decode(imagesList.get(i).getImage(),Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        // set bitmap on imageView
        image.setImageBitmap(bitmap);
        return mView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

