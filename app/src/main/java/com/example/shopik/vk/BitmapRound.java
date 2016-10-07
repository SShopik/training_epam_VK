package com.example.shopik.vk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.FloatRange;

public class BitmapRound {
    public static Bitmap getCircleMaskedBitmapUsingClip(Bitmap source, int radius) {
        if (source == null) {
            return null;
        }

        int diam = radius << 1;

        Bitmap scaledBitmap = scaleTo(source, diam);

        final Path path = new Path();
        path.addCircle(radius, radius, radius, Path.Direction.CCW);

        Bitmap targetBitmap = Bitmap.createBitmap(diam, diam, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(targetBitmap);

        canvas.clipPath(path);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        canvas.drawBitmap(scaledBitmap, 0, 0, paint);

        return targetBitmap;
    }

    public static Bitmap scaleTo(Bitmap source, int size) {
        int destWidth = source.getWidth();

        int destHeight = source.getHeight();

        destHeight = destHeight * size / destWidth;
        destWidth = size;

        if (destHeight < size) {
            destWidth = destWidth * size / destHeight;
            destHeight = size;
        }

        Bitmap destBitmap = Bitmap.createBitmap(destWidth, destHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(destBitmap);
        canvas.drawBitmap(source, new Rect(0, 0, source.getWidth(), source.getHeight()), new Rect(0, 0, destWidth, destHeight), new Paint(Paint.ANTI_ALIAS_FLAG));
        return destBitmap;
    }

    public static Bitmap renderScriptBlur(Context context, Bitmap srcBitmap, @FloatRange(from = 0.0f, to = 25.0f) float radius) {
        if (srcBitmap == null)
            return null;
        Bitmap outputBitmap = null;
        RenderScript rs = null;
        try {
            rs = RenderScript.create(context);
            outputBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
            final Canvas canvas = new Canvas(outputBitmap);
            canvas.drawBitmap(srcBitmap, 0, 0, null);
            Allocation overlayAlloc = Allocation.createFromBitmap(rs, outputBitmap);
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            blur.setInput(overlayAlloc);
            blur.setRadius(radius);
            blur.forEach(overlayAlloc);
            overlayAlloc.copyTo(outputBitmap);
            return outputBitmap;
        } catch (Exception ex) {
            if (outputBitmap != null)
                outputBitmap.recycle();
            return srcBitmap;
        } finally {
            if (rs != null)
                rs.destroy();
        }
    }
}
