package com.example.shopik.vk;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Menu extends Activity {

    Bitmap bitmap;
    Bitmap finalBitmap;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image_3);
        finalBitmap = BitmapRound.getCircleMaskedBitmapUsingClip(bitmap,50);


        ImageView imageView = (ImageView) findViewById(R.id.image_round);
        imageView.setImageBitmap(finalBitmap);



    }


}
