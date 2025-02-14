package com.example.listviewwithimages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Network;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter{

    public static String EXTRA_SELECTED_ITEM = "my.selected.item";
    private Context context;
    private ArrayList<User> arrayList;
    private TextView name, phone;
    private NetworkImageView image;
    private ImageLoader imageLoader;

    public UserAdapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_user,
                parent, false );
        name = convertView.findViewById(R.id.name);
        phone = convertView.findViewById(R.id.phone);
        image = (NetworkImageView) convertView.findViewById(R.id.networkImageView);
        name.setText(arrayList.get(position).getName());
        phone.setText(arrayList.get(position).getPhone());
        image.setImageUrl(arrayList.get(position).getImageUrl(), imageLoader);
        return convertView;
    }
    
    public void populateView(View view, int index)
    {
        User picked = arrayList.get(index);
        
        TextView tv = view.findViewById(R.id.user_name);
        tv.setText(picked.getName());
        tv = view.findViewById(R.id.user_phone);
        tv.setText(picked.getPhone());

        NetworkImageView image = view.findViewById(R.id.userImageView);
        image.setImageUrl(picked.getImageUrl(), imageLoader);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = UserActivity.newIntent(adapterView.getContext(),
                this);
        intent.putExtra(EXTRA_SELECTED_ITEM, i);
        adapterView.getContext().startActivity(intent);
    }
}
