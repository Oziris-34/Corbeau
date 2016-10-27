package com.example.a34011_73_01.corbeau_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by 34011-73-09 on 27/10/2016.
 */

public class BitmapBlending {
    public static Bitmap blend(Bitmap bmp1, Bitmap bmp2) {
        Bitmap result = null;

        Rect bmp1Rect = new Rect(0, 0, bmp1.getWidth(), bmp1.getHeight());

        Rect bmp2Rect = new Rect(0, 0, bmp2.getWidth(), bmp2.getHeight());

        result = Bitmap.createBitmap(bmp1Rect.width(), bmp1Rect.height(), Bitmap.Config.ARGB_8888);

        if(result != null) {
            Canvas canvas = new Canvas(result);
            canvas.drawBitmap(bmp1, bmp1Rect, bmp1Rect, new Paint(Paint.FILTER_BITMAP_FLAG));
            canvas.drawBitmap(bmp1, bmp2Rect, bmp1Rect, new Paint(Paint.FILTER_BITMAP_FLAG));
        }

        return result;
    }
}
