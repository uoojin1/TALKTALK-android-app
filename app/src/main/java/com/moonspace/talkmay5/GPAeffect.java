package com.moonspace.talkmay5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.view.animation.Animation;

import java.util.Random;

/**
 * Created by eugenemoon on 2016. 7. 26..
 */
public class GPAeffect extends GameObject{

    private Random rand = new Random();
    private animation Animation = new animation();
    private Bitmap spritesheet;
    private int eraseTimer;
    private long startTime;

    public GPAeffect(Bitmap res, int x, int y, int w, int h,long startT, int numFrames)
    {
        super.x = x;
        super.y = y;
        if(Build.VERSION.SDK_INT < 23.0) {
            height = h*3;
            width = w*3;
        }
        else {
            height = h * 4;
            width = w * 4;
        }
        eraseTimer = 0;
        startTime = startT;

        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = res;

        for(int i = 0; i<image.length;i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        Animation.setFrames(image);
        Animation.setDelay(80);

    }
    public void update()
    {
        if(!Animation.playedOnce()) {
            Animation.update();
        }
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>30)
        {
            eraseTimer++;
            startTime = System.nanoTime();
        }
    }
    public void draw(Canvas canvas)
    {
        try {
            if (!Animation.playedOnce()) {
                canvas.drawBitmap(Animation.getImage(), x, y, null);
            }
        }catch(Exception e){}
    }

    @Override
    public int getHeight()
    {
        //offset slightly for more realistic collision detection
        return height;
    }
    public int getEraseTimer()
    {
        //offset slightly for more realistic collision detection
        return eraseTimer;
    }
}
