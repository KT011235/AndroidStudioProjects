package com.example.logintwo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    TextView usernameField;
    TextView passwordField;
    Button loginButton;
    //127.0.0.1
    //10.0.0.107
    String url = "http://10.0.0.107:5000/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.login_button);
        usernameField = findViewById(R.id.user_input);
        passwordField = findViewById(R.id.password_input);

        queue = Volley.newRequestQueue(this);
        queue.start();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                String data = String.format("{ \"username\":\"%s\", \"password\":\"%s\" }", username, password);

                JsonRequest jsonRequest =
                        new JsonRequest(Request.Method.POST, url, data,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Log.d("Login", "responded" + response);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Login Error", "Error:" + error);
                            }
                        }) {
                            @Override
                            protected Response parseNetworkResponse(NetworkResponse response) {
                                String data = new String(response.data);
                                Response<String> res = Response.success(data, null);
                                Log.d("Login", "parseNetworkResponse called");
                                return res;
                            }
                        };
                queue.add(jsonRequest);
            }
        });
    }
}
