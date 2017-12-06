package com.moonspace.talkmay5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.view.animation.Animation;

/**
 * Created by eugenemoon on 2016. 7. 18..
 */
public class Player extends GameObject{

    private Bitmap spritesheet;
    private boolean Drunk = false;
    private boolean Pasan = false;
    private boolean graduated = true;
    private int money;
    private int time; //1학년 1학기부터 시작
    private double GPA;
    private double dy;
    private double dx;// x movement
    private boolean up;
    private boolean playing;
    private animation Animation = new animation();
    private long startTime;
    private long drunkTimer = 0;

    public Player(Bitmap res, int w, int h, int numFrames) {

        x = GamePanel.WIDTH;
        y = GamePanel.HEIGHT+1700;
        if(Build.VERSION.SDK_INT < 23.0) {
            y=y*3/4;
        }
        else {
        }
        GPA = 1.0;
        dy = 0;
        dx = 0;
        time = 333;//time
        if(Build.VERSION.SDK_INT < 23.0) {
            height = h*3;
            width = w*3;
        }
        else {
            height = h * 4;
            width = w * 4;
        }
        money = 100;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        Animation.setFrames(image);
        Animation.setDelay(20);
        startTime = System.nanoTime();

    }
    public void setGraduated(boolean b){graduated = b;}
    public void setUp(boolean b){up = b;}
    public void increaseGPA(double gpa){
        GPA += gpa;
    }
    public void decreaseGPA(double gpa){
        GPA -= gpa;
    }
    public void increaseMoney(double MONEY){
        money += MONEY;
    }
    public void decreaseMoney(double MONEY){
        money -= MONEY;
    }

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>10)
        {
            time++;
            drunkTimer++;
            startTime = System.nanoTime();
        }
        Animation.update();

        //-150, 1300 left right limit
        if(!Drunk) {
            if (up) {
                    dx -= 3;
            } else {
                    dx += 3;
            }
        }
        else{
            if (!up) {
                    dx -= 3;
            } else {
                    dx += 3;
            }
        }
        // limit the movement of character
        if(x<-120){
            x = -120;
            dx = 0;
        }
        if(x>1200){
            dx = 0;
            x = 1200;
        }

        if(dx>30)dx = 30;
        if(dx<-30)dy = -30;
        if(drunkTimer>10){
            Drunk = false;
            drunkTimer = 0; // reset drunk timer
        }

        x += dx*2;

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(Animation.getImage(),x,y,null);
        //canvas.drawBitmap(spritesheet,x,y,null);
    }
    public int getTime(){return time;}
    public boolean getGraduated(){return graduated;}
    public void isPasan(boolean Pasanham){Pasan = Pasanham;}
    public void isDrunk(boolean Drunken){Drunk = Drunken;}
    public int getMoney(){return money;}
    public boolean getDrunken(){return Drunk;}
    public double getGPA(){return GPA;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetDY(){dy = 0;}
    public void resetTime(){time = 333;}
    public void resetGPA(){GPA = 1.0;}
    public void resetMoney(){money = 100;}
}