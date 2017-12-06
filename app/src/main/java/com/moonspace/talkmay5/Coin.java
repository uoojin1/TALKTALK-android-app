package com.moonspace.talkmay5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;

import java.util.Random;

/**
 * Created by eugenemoon on 2016. 8. 2..
 */
public class Coin extends GameObject{
    private int score;
    private int speed;
    private Random rand = new Random();
    private animation Animation = new animation();
    private Bitmap spritesheet;

    public Coin(Bitmap res, int x, int y, int w, int h, int s, int numFrames)
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
        score = s;

        speed = (7 + (int) (rand.nextDouble()*score/30))*3;

        //cap missile speed
        if(speed>70)speed = 70;

        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = res;

        for(int i = 0; i<image.length;i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        Animation.setFrames(image);
        Animation.setDelay(100-speed);

    }
    public void update()
    {
        y+=speed;
        Animation.update();
    }
    public void draw(Canvas canvas)
    {
        try{
            canvas.drawBitmap(Animation.getImage(),x,y,null);
        }catch(Exception e){}
    }

    @Override
    public int getHeight()
    {
        //offset slightly for more realistic collision detection
        return height+10;
    }
}
