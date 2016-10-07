package com.example.shopik.vk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    Bitmap finalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_3);
        finalBitmap = BitmapRound.getCircleMaskedBitmapUsingClip(bitmap, 45);
        ImageView imageView = (ImageView) findViewById(R.id.ava);
        imageView.setImageBitmap(finalBitmap);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_2);
        finalBitmap = BitmapRound.getCircleMaskedBitmapUsingClip(bitmap, 45);
        imageView = (ImageView) findViewById(R.id.ava_2);
        imageView.setImageBitmap(finalBitmap);
    }

    static final String CLICK = "Click";

    public void onClick(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), CLICK, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void startMenu(View view) {
        startActivity(new Intent(this, Menu.class));
    }
}