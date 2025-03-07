package com.example.dynamiclistview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    protected final String url = "https://nua.insufficient-light.com/data/holiday_songs_spotify.json";

    private List<HolidaySongs> arrayOfSongs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i < response.length(); i++){
                    String album_name;
                    String artist_name;
                    String danceability;
                    String duration;
                    String album_img;

                    try {
                        JSONObject songInfo = response.getJSONObject(i);
                        album_name = songInfo.getString("album_name");
                        artist_name = songInfo.getString("artist_name");
                        danceability = songInfo.getString("danceability");
                        duration = songInfo.getString("duration_ms");
                        album_img = songInfo.getString("album_img");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    HolidaySongs holidaySongs = new HolidaySongs(album_name, artist_name, danceability, duration, album_img);
                    arrayOfSongs.add(holidaySongs);
                }

                HolidaySongsAdapter adapter = new HolidaySongsAdapter(getApplicationContext(), arrayOfSongs);
                ListView listView = findViewById(R.id.my_list_view);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Failed to get data.");
            }
        });

        queue.add(jsonArrayRequest);

        // Create the adapter to convert the array to views


        // Attach the adapter to a ListView

//        final LayoutInflater factory = getLayoutInflater();
//        final View myView = factory.inflate(R.layout.my_list, null);
//        TextView textView = (TextView) myView.findViewById(R.id.textView);
    }
}

