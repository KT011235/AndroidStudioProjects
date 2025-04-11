package com.example.hustagram;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;

public class ImageDetailActivity extends AppCompatActivity {
    TextView comment;
    static ImageView imageView;
    static Images image;
    static RequestQueue queue;
    static ImagesAdapter imagesAdapter;
    static Context context;
    Button back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.image_detail);

        back_button = findViewById(R.id.back_to_grid_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageDetailActivity.this, DisplayImagesActivity.class);
                startActivity(intent);
            }
        });

        comment = findViewById(R.id.commentView);
        imageView = findViewById(R.id.imageDetail);

        byte[] bytes= Base64.decode(image.getImage(),Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        // set bitmap on imageView
        imageView.setImageBitmap(bitmap);

        comment.setText(image.getComment());
        };

    public static Intent newIntent(Context packageContext, ImagesAdapter adapterRef, RequestQueue queue, Images image, Context context) {
        Intent i = new Intent(packageContext, ImageDetailActivity.class);
        imagesAdapter = adapterRef;
        ImageDetailActivity.queue = queue;
        ImageDetailActivity.image = image;
        ImageDetailActivity.context= context;
        return i;
    }


}
