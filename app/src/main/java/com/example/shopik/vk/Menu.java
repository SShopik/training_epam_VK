package com.example.shopik.vk;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Menu extends Activity {

    Bitmap bitmap;
    Bitmap finalBitmap;
    Bitmap blur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_3);
        finalBitmap = BitmapRound.getCircleMaskedBitmapUsingClip(bitmap, 50);
        ImageView imageView = (ImageView) findViewById(R.id.image_round);
        imageView.setImageBitmap(finalBitmap);
        blur = BitmapRound.renderScriptBlur(this, bitmap, 20);
        Drawable drow = new BitmapDrawable(getResources(), blur);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.menu_header);
        layout.setBackground(drow);


    }


}
