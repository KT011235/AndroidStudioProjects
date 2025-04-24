package com.example.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class ToDoAdapter extends BaseAdapter {
    List<ToDoItems> toDoItemsList;
    private Context context;
    TextView textView;
    public ToDoAdapter(Context context, List<ToDoItems> toDoItemsList){
        this.context = context;
        this.toDoItemsList = toDoItemsList;
    }

    @Override
    public int getCount() {
        return null == toDoItemsList ? 0 : toDoItemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null == toDoItemsList ? null: toDoItemsList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("ToDoAdapter", "getView called for position: " + i);
        View itemView = LayoutInflater.from(context).inflate(R.layout.todolist_item,
                viewGroup, false);
        textView = itemView.findViewById(R.id.todo_item);
        textView.setText(toDoItemsList.get(i).getTodo_item());

        CheckBox checkBox = itemView.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBox.setEnabled(false);
                }
            }
        });

        return itemView;
    }
}
