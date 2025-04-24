package com.example.customerlist2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    protected ListView list;
    //protected String url = "http://10.0.0.107:5000/all";
    protected String url = "http://10.2.105.199:5000/all";
    private Gson gson;
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        list = findViewById(R.id.listView);
        button = findViewById(R.id.add_button);

        button.setOnClickListener(v -> {
            Intent i = new Intent(this, CustomerAddActivity.class);
            this.startActivity(i);
        });

        queue = Volley.newRequestQueue(this);
        queue.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Customer> customers = new ArrayList<Customer>();

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);

                                List<String> newArrayComments = new ArrayList<String>();
                                if (object.has("comments")) {
                                    JSONArray arrayComments = object.getJSONArray("comments");
                                    if (arrayComments != null) {

                                        for (int n = 0; i < arrayComments.length(); n++) {
                                            newArrayComments.add(arrayComments.get(i).toString());
                                        }
                                    }
                                }

                                customers.add(new Customer(
                                        object.getString("name"),
                                        object.getString("address"),
                                        object.getString("phone"),
                                        newArrayComments));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        CustomerAdapter adapter = new CustomerAdapter(list.getContext(), customers);
                        list.setAdapter(adapter);
                        list.setOnItemClickListener((parent, view, position, id) -> {
                            Intent intent = new Intent(MainActivity.this, CommentActivity.class);
                            intent.putExtra("customer", customers.get(position));
                            startActivity(intent);
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
}
