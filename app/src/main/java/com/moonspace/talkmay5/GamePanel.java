package com.moonspace.talkmay5;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by eugenemoon on 2016. 7. 18..
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    MediaPlayer bgm;

    private SoundPool gulp;
    private int gulpID;

    private SoundPool coin;
    private int coinID;

    private SoundPool success;
    private int successID;
    private SoundPool failed;
    private int failedID;

    private SoundPool tutorsound;
    private int tutorsoundID;

    public static final int HEIGHT = 800;
    public static final int WIDTH = 480;
    private long redbullStartTime;
    private long sojuStartTime;
    private long coinStartTime;
    private long tutorStartTime;
    private MainThread thread;
    private Player player;
    private Background bg;
    private ArrayList<Redbull> redbulls;
    private ArrayList<Soju> sojus;
    private ArrayList<Coin> coins;
    private ArrayList<Tutor> tutors;
    private ArrayList<GPAeffect> gpAeffects;
    private Random rand = new Random();
    private long numberOfSoju = 0;
    private Typeface myTypeface;
    AssetManager mngr;
    private boolean newGameCreated;
    private long startReset;
    private boolean reset;
    private int best = 999999;

    public GamePanel(Context context){
        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);


        //make gamePanel focusable so it can haldle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        bgm.stop();
        while(retry && counter<1000){
            counter++;
            try{
                thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;

            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        //font
        //myTypeface = Typeface.createFromAsset()
        //bgm
        bgm = MediaPlayer.create(getContext(), R.raw.mitchirineko);
        bgm.start();

        gulp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        gulpID = gulp.load(getContext(),R.raw.gulp,1);

        coin = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        coinID = coin.load(getContext(),R.raw.coin,1);

        tutorsound = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        tutorsoundID = tutorsound.load(getContext(),R.raw.tutor,1);

        success = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        successID = success.load(getContext(),R.raw.success,1);

        failed = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        failedID = failed.load(getContext(),R.raw.failed,1);

        //TextView scoreView = findViewById(R.id.scoreview);
        bg = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.background));
        player = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.charframe2),143,70,2);
        //player = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.charframe2),100,40,2);

        redbulls = new ArrayList<Redbull>();
        sojus = new ArrayList<Soju>();
        coins = new ArrayList<Coin>();
        tutors = new ArrayList<Tutor>();
        gpAeffects = new ArrayList<GPAeffect>();

        redbullStartTime = System.nanoTime();
        sojuStartTime = System.nanoTime(); // delay soju starttime
        coinStartTime = System.nanoTime();
        tutorStartTime = System.nanoTime();

        thread = new MainThread(getHolder(), this);

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!player.getPlaying())
            {
                player.setPlaying(true);
            }
            else
            {
                player.setUp(true);
            }
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            player.setUp(false);
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void update(){
        if(player.getPlaying()) {
            bg.update();
            player.update();

            //add redbulls on timer
            long redbullElapsed = (System.nanoTime()-redbullStartTime)/1000000;
            long sojuElapsed = (System.nanoTime()-sojuStartTime)/1000000;
            long coinElapsed = (System.nanoTime()-coinStartTime)/1000000;
            long tutorElapsed = (System.nanoTime()-tutorStartTime)/1000000;

            if(redbullElapsed>(1500-player.getTime()/4)){
                System.out.println("making redbull");
                //first redbull always goes down the middle
                if(redbulls.size()==0){
                    redbulls.add(new Redbull(BitmapFactory.decodeResource(getResources(),R.drawable.redbull3frame2),WIDTH/2,0,19,37,player.getTime(),3));
                }
                else{
                    redbulls.add(new Redbull(BitmapFactory.decodeResource(getResources(),R.drawable.redbull3frame2),
                            (int)(rand.nextDouble()*(WIDTH)*3),0, 19,37, player.getTime(),3));
                }
                //reset timer
                redbullStartTime = System.nanoTime();
            }

            //add soju loop
            if(sojuElapsed>(1500-player.getTime()/4)){
                System.out.println("making soju");
                //first redbull always goes down the middle
                if(sojus.size()==0){
                    sojus.add(new Soju(BitmapFactory.decodeResource(getResources(),R.drawable.soju3frame2),WIDTH/3,0,17,37,player.getTime(),3));
                }
                else{
                    sojus.add(new Soju(BitmapFactory.decodeResource(getResources(),R.drawable.soju3frame2),
                            (int)(rand.nextDouble()*(WIDTH)*3),0, 17,37, player.getTime(),3));
                }
                //reset timer
                sojuStartTime = System.nanoTime();
            }

            //add coin loop
            if(coinElapsed>(1500-player.getTime()/4)){
                System.out.println("making coin");
                //first redbull always goes down the middle
                if(coins.size()==0){
                    coins.add(new Coin(BitmapFactory.decodeResource(getResources(),R.drawable.coinframe2),WIDTH/3,0,39,37,player.getTime(),3));
                }
                else{
                    coins.add(new Coin(BitmapFactory.decodeResource(getResources(),R.drawable.coinframe2),
                            (int)(rand.nextDouble()*(WIDTH)*3),0, 39,37, player.getTime(),3));
                }
                //reset timer
                coinStartTime = System.nanoTime();
            }

            //tutor loop
            if(tutorElapsed>(20000)){
                System.out.println("making tutor");
                //tutor always random position
                tutors.add(new Tutor(BitmapFactory.decodeResource(getResources(),R.drawable.tutor1),
                        (int)(rand.nextDouble()*(WIDTH)*3),0, 44,44, player.getTime(),1));
                //reset timer
                tutorStartTime = System.nanoTime();
            }

            //loop through every redbulls and check collision and remove
            for(int i=0; i<redbulls.size();i++){
                //update redbull
                redbulls.get(i).update();

                if(collision(redbulls.get(i),player)){
                    gulp.play(gulpID,1,1,1,0,1);
                    player.increaseGPA(0.10);
                    player.decreaseMoney(10);
                    gpAeffects.add(new GPAeffect(BitmapFactory.decodeResource(getResources(), R.drawable.gpaup),
                            player.getX(), player.getY() - 30, 146, 27,System.nanoTime(), 3));
                    redbulls.remove(i);
                    break;
                }
                if(redbulls.get(i).getY()>3000){//might have to adjust
                    redbulls.remove(i);
                    break;
                }
            }
            //coin loop
            for(int i=0; i<coins.size();i++){
                //update redbull
                coins.get(i).update();
                float point5 = (float)0.5;
                if(collision(coins.get(i),player)){
                    coin.play(coinID,point5,point5,1,0,1);
                    player.increaseMoney(50);
                    coins.remove(i);
                    break;
                }
                if(coins.get(i).getY()>3000){//might have to adjust
                    coins.remove(i);
                    break;
                }
            }

            //tutor loop
            for(int i=0; i<tutors.size();i++){
                //update redbull
                tutors.get(i).update();

                if(collision(tutors.get(i),player)){
                    player.decreaseMoney(500);
                    player.increaseGPA(0.5);
                    tutors.remove(i);
                    tutorsound.play(tutorsoundID,1,1,1,0,1);
                    if(player.getMoney()<0){ // 파산
                        for(Soju m: sojus){
                            sojus.remove(m);
                        }
                        for(Redbull m: redbulls){
                            redbulls.remove(m);
                        }
                        player.isPasan(true); // set player to pasan
                    }
                    break;
                }
                if(tutors.get(i).getY()>3000){//might have to adjust
                    tutors.remove(i);
                    break;
                }
            }

            for(int i=0; i<sojus.size();i++){
                //update soju
                sojus.get(i).update();

                if(collision(sojus.get(i),player)){
                    //Toast.makeText(getContext(), "soju",Toast.LENGTH_SHORT).show();
                    gulp.play(gulpID,1,1,1,0,1);
                    sojus.remove(i);
                    player.decreaseGPA(0.20);
                    player.decreaseMoney(18);
                    player.isDrunk(true);
                    gpAeffects.add(new GPAeffect(BitmapFactory.decodeResource(getResources(), R.drawable.gpadown),
                            player.getX(), player.getY() - 30, 146, 27, System.nanoTime(), 3));

                    //player.setPlaying(false);//should change to true
                    break;
                }
                if(sojus.get(i).getY()>3000){//might have to adjust
                    sojus.remove(i);
                    break;
                }
            }
            for(int i=0;i<gpAeffects.size();i++){ // remove the gpaeffect when the time is gone
                gpAeffects.get(i).update();
                if(gpAeffects.get(i).getEraseTimer()>100){
                    gpAeffects.remove(i);
                }
            }
        }
        if(player.getGPA()<0 || player.getMoney() < - 500){ // game end
            player.setPlaying(false);
            player.setGraduated(false);
            failed.play(failedID,1,1,1,0,1);
            MainActivity.prefsbool.edit().putBoolean("com.moonspace.talkmay5.graduated",false).commit();
            reset();

        }
        if(player.getGPA()>=4.0){
            player.setPlaying(false);
            player.setGraduated(true);
            success.play(successID,1,1,1,0,1);
            if(player.getTime()-333 < best) {
                best = player.getTime()-333;
                MainActivity.prefs.edit().putInt("com.moonspace.talkmay5.HIGHSCORE", player.getTime() - 333).commit();
                MainActivity.prefsbool.edit().putBoolean("com.moonspace.talkmay5.graduated", true).commit();
            }
            reset();
        }
    }
    public void reset(){
        player.resetTime();
        player.resetGPA();
        player.resetMoney();
        for(Soju m: sojus){
            sojus.remove(m);
        }
        for(Redbull m: redbulls){
            redbulls.remove(m);
        }
        for(Coin m : coins){
            coins.remove(m);
        }
    }
    @Override
    public void draw(Canvas canvas){
        final float scaleFactorX = getWidth()/(WIDTH*1.f)/3;
        final float scaleFactorY = getHeight()/(HEIGHT*1.f)/3;
        final float scaleFactorX2 = getWidth()/(WIDTH*1.f)/4;
        final float scaleFactorY2 = getHeight()/(HEIGHT*1.f)/4;

        if(canvas!=null) {
            final int savedState = canvas.save();
            if(Build.VERSION.SDK_INT < 23.0)
                canvas.scale(scaleFactorX, scaleFactorY);
            else
                canvas.scale(scaleFactorX2,scaleFactorY2);

            bg.draw(canvas);
            player.draw(canvas);

           for(Redbull m: redbulls){
                m.draw(canvas);
           }
            for(int i=0;i<sojus.size();i++){
                sojus.get(i).draw(canvas);
            }
            for(int i=0;i<coins.size();i++){
                coins.get(i).draw(canvas);
            }
            for(int i=0;i<tutors.size();i++){
                tutors.get(i).draw(canvas);
            }
            for(GPAeffect m: gpAeffects){
                m.draw(canvas);
            }
            drawText(canvas);
            canvas.restoreToCount(savedState);
        }
    }
    public boolean collision(GameObject a, GameObject b)
    {
        if(Rect.intersects(a.getRectangle(), b.getRectangle()))
        {
            return true;
        }
        return false;
    }
    public void drawText(Canvas canvas){
        final float scaleFactorX = getWidth()/(WIDTH*1.f)/3;
        final float scaleFactorY = getHeight()/(HEIGHT*1.f)/3;
        final float scaleFactorX2 = getWidth()/(WIDTH*1.f)/4;
        final float scaleFactorY2 = getHeight()/(HEIGHT*1.f)/4;


        if(Build.VERSION.SDK_INT < 23.0){
            canvas.scale(scaleFactorX, scaleFactorY);
        }
        else {
            canvas.scale(scaleFactorX2, scaleFactorY2);
        }
        mngr = getContext().getAssets();
        Typeface myTypeface = Typeface.createFromAsset(mngr,"PrincessSofia-Regular.ttf");
        Typeface myTypeface2 = Typeface.createFromAsset(mngr,"Playtime.otf");
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);
        //paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTypeface(myTypeface2);
        Paint paint1 = new Paint();
        paint1.setTextSize(200);
        paint1.setTypeface(myTypeface2);

        canvas.drawText((int)(player.getTime()/365)+"  Year", 10, HEIGHT, paint);
        int hakgi;
        if((int)(player.getTime()%365)>180){
            hakgi = 2;
        }
        else{
            hakgi = 1;
        }
        canvas.drawText(hakgi+"  Semester", 300, HEIGHT, paint);
        int gpaPoint = (int)(player.getGPA()*100);
        double gpaDouble = (double)(gpaPoint/100.0);
        canvas.drawText("GPA: " + gpaDouble, 10, HEIGHT + 80, paint);
        canvas.drawText("drunken?: " + player.getDrunken(), 10, HEIGHT + 160, paint);
        canvas.drawText("days: " + (player.getTime() - 333), 10, HEIGHT + 240, paint);
        canvas.drawText("money: " + player.getMoney(), 10, HEIGHT + 320, paint);



        if(!player.getPlaying()){ // reset needed for the scores

            //paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("PRESS TO START", WIDTH / 2 - 50, HEIGHT * 2 - 100, paint1);

            paint1.setTextSize(80);
            canvas.drawText("PRESS AND HOLD TO GO LEFT", WIDTH / 2 - 50, HEIGHT * 2 + 20, paint1);
            canvas.drawText("RELEASE TO GO RIGHT", WIDTH / 2 - 50, HEIGHT * 2 + 140, paint1);
            paint1.setColor(Color.RED);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

           // canvas.drawText("How many years will it take for you to graduate?",WIDTH/2-50,HEIGHT*2+260,paint1);
           // canvas.drawText("You will lose if your money goes below -1000 or GPA goes below 0.0",WIDTH/2-50,HEIGHT*2+380,paint1);

        }

    }
    public void newGame(){

    }
}
