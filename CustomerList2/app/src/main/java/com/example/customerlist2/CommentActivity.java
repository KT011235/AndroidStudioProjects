package com.example.customerlist2;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CommentActivity extends AppCompatActivity {
    private final String url = "http://10.2.105.199:5000/update";
    //private final String url = "http://10.0.0.107:5000/update";

    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            customer = extras.getSerializable("customer", Customer.class);
        }
        CommentAdapter adapter = new CommentAdapter(this, customer);
        ListView listView = findViewById(R.id.comment_list);
        listView.setAdapter(adapter);

        Button saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.comment);

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.start();

            String comment = editText.getText().toString();
            try {
                JSONObject object = new JSONObject()
                        .put("comments", comment);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (comment.isBlank()) {
                            Toast.makeText(getApplicationContext(), "No comments entered.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", error.toString());
                    }
                });

                queue.add(jsonObjectRequest);

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

//
//        final EditText editText = findViewById(R.id.comment);
//        Button saveButton = findViewById(R.id.save_button);
//        Button deleteButton = findViewById(R.id.delete_button);
//        ListView listView = findViewById(R.id.comment_list);
//
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
//        listView.setAdapter(adapter);
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String comment = editText.getText().toString();
//
//                if (comment.isBlank()) {
//                    Toast.makeText(getApplicationContext(), "No comments entered.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                adapter.add(comment);
//                editText.setText("");
//
//            }
//        });
//    }