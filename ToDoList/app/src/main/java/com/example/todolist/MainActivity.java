package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    protected ListView listView;
    List<ToDoItems> todoItemsList;
    static Context context;
    private FloatingActionButton add_item;
    //School
    //protected String url = "http://10.2.105.199:5000/todolist";
    //Dorm
    protected String url = "http://10.0.0.107:5000/todolist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_item = findViewById(R.id.add_button);
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ToDoItemAddActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.listView);

        queue = Volley.newRequestQueue(this);
        queue.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        todoItemsList = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);

                                todoItemsList.add(new ToDoItems(
                                        object.getString("_id"),
                                        object.getString("item")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ToDoAdapter adapter = new ToDoAdapter(MainActivity.this, todoItemsList);
                        listView.setAdapter(adapter);

                        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                                todoItemsList.remove(position);
//                                adapter.notifyDataSetChanged();
//                                return true;
                                  ToDoItems itemToDelete = todoItemsList.get(position);
                                  String deleteUrl = "http://10.0.0.107:5000/todo/" + itemToDelete.getId();

                                StringRequest deleteRequest = new StringRequest(
                                        Request.Method.DELETE,
                                        deleteUrl,
                                        response -> {
                                            todoItemsList.remove(position);
                                            adapter.notifyDataSetChanged();
                                        },
                                        error -> Log.d("DeleteError", "Failed to delete: " + error)
                                );

                                queue.add(deleteRequest);
                                return true;
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSONArray Error", "Error:" + error);
                    }
                });

        queue.add(jsonArrayRequest);
    }


    public static Intent newIntent(Context context, RequestQueue queue) {
        Intent i = new Intent(context, MainActivity.class);
        MainActivity.context =context;
        return i;
    }
}
