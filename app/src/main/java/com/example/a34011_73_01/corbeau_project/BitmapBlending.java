package com.example.a34011_73_01.corbeau_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.support.v7.widget.DrawableUtils;

/**
 * Created by 34011-73-09 on 02/11/2016.
 */

public class BitmapBlending {
    public static Drawable blend(Context context, BitmapDrawable drawableBase, BitmapDrawable drawableToBlend, float right, float bottom) {
        if((drawableBase == null) || (drawableToBlend == null)) {
            return null;
        }

        Bitmap bitmapBase = drawableBase.getBitmap();
        Bitmap bitmapToBlend = drawableToBlend.getBitmap();

        if((bitmapBase == null) || (bitmapToBlend == null)) {
            return null;
        }

        int width = bitmapBase.getWidth();
        int height = bitmapBase.getHeight();

        Bitmap bitmapResult = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapResult.eraseColor(Color.argb(0, 0, 0, 0));

        Paint basePaint = new Paint();
        basePaint.setShader(new BitmapShader(bitmapBase, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        basePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));

        Paint toBlendPaint = new Paint();
        toBlendPaint.setShader(new BitmapShader(bitmapToBlend, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        toBlendPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        Canvas canvas = new Canvas(bitmapResult);
        canvas.drawRect(0, 0, width, height, basePaint);
        canvas.drawRect(right * width, bottom * height, width, height, toBlendPaint);

        Drawable result = new BitmapDrawable(context.getResources(), bitmapResult);
        /*
        bitmapBase.recycle();
        bitmapToBlend.recycle();
         */
        return result;
    }
}
