package com.moonspace.talkmay5;

import android.graphics.Rect;

/**
 * Created by eugenemoon on 2016. 7. 18..
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }
    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);
    }
}
