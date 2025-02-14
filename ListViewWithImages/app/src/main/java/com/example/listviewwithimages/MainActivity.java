package com.example.listviewwithimages;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LayoutInflater factory = getLayoutInflater();
        final View myView = factory.inflate(R.layout.my_list, null);
        TextView textView = myView.findViewById(R.id.textView);

        listView = findViewById(R.id.my_list_view);
        listItem = getResources().getStringArray(R.array.array_technology);


        ArrayList<User> arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new User("Eve", "777-777-7777"));
        arrayOfUsers.add(new User("John", "777-777-7777"));
        arrayOfUsers.add(new User("Mark", "777-777-7777"));
        arrayOfUsers.add(new User("Michael", "777-777-7777"));
        arrayOfUsers.add(new User("Adam", "777-777-7777"));
        arrayOfUsers.add(new User("Mary", "777-777-7777"));
        arrayOfUsers.add(new User("Olivia", "777-777-7777"));


        // Create the adapter to convert the array to views
        UserAdapter adapter = new UserAdapter(this, arrayOfUsers);

        // Attach the adapter to a ListView
        listView = findViewById(R.id.my_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(adapter);
    }
}