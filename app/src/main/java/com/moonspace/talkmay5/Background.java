package com.moonspace.talkmay5;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by eugenemoon on 2016. 7. 18..
 */
public class Background {

    private Bitmap image;
    private int x, y;

    public Background(Bitmap res){
        image = res;
    }
    public void update(){

    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);

    }
}
