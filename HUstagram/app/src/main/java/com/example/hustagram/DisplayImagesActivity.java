package com.example.hustagram;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DisplayImagesActivity extends AppCompatActivity {
    private RequestQueue queue;
    List<Images> imagesList;
    GridView grid_view;
    static Context context;
    Button back_button;
    //protected String url = "http://10.2.105.199:5000/images";
    protected String url = "http://10.0.0.107:5000/images";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        back_button = findViewById(R.id.back_to_main_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayImagesActivity.this, MainActivity.class);
                startActivity(intent);
            }
            });

        grid_view = findViewById(R.id.gridView);

        queue = Volley.newRequestQueue(this);
        queue.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        imagesList = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);

                                imagesList.add(new Images(
                                        object.getString("_id"),
                                        object.getString("time"),
                                        object.getString("comment"),
                                        object.getString("image")));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ImagesAdapter adapter = new ImagesAdapter(grid_view.getContext(), imagesList);
                        grid_view.setAdapter(adapter);
                        grid_view.setOnItemClickListener((parent, view, position, id) -> {
                            Intent intent = ImageDetailActivity.newIntent(DisplayImagesActivity.this, adapter,queue,imagesList.get(position),context);
                            intent.putExtra("image", imagesList.get(position).getImage());
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

    public static Intent newIntent(Context context, RequestQueue queue) {
        Intent i = new Intent(context, DisplayImagesActivity.class);
        DisplayImagesActivity.context =context;
        return i;
    }
    }
