package com.example.customerlist2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends BaseAdapter {

    private Customer customer;
    private Context context;

    public CommentAdapter(Context context, Customer customer) {
        this.customer = customer;
        this.context = context;
    }

    @Override
    public int getCount() {
        return customer.getComments().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cView = LayoutInflater.from(context).inflate(R.layout.layout_comment_list,
                parent, false);
        TextView comment = cView.findViewById(R.id.customer_comment);
        comment.setText(customer.getComments().get(position));
        return cView;
    }
}
