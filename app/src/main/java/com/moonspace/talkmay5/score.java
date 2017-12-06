package com.moonspace.talkmay5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by eugenemoon on 2016. 9. 5..
 */
public class score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.highscore); // Activitymain has the general view, toolbar and container for frag
        TextView firstplace = (TextView)findViewById(R.id.first);
        TextView secondplace = (TextView)findViewById(R.id.second);
        TextView thirdplace = (TextView)findViewById(R.id.third);
        TextView fourthplace = (TextView)findViewById(R.id.fourth);
        TextView fifthplace = (TextView)findViewById(R.id.fifth);

        firstplace.setTextColor(Color.WHITE);
        secondplace.setTextColor(Color.WHITE);
        thirdplace.setTextColor(Color.WHITE);
        fourthplace.setTextColor(Color.WHITE);
        fifthplace.setTextColor(Color.WHITE);


        int first = MainActivity.prefs.getInt("com.moonspace.talkmay5.HIGHSCORE", 0);
        int haknyun = first/365;
        int hakgi;
        if((int)(first%365)>180){
            hakgi = 2;
        }
        else{
            hakgi = 1;
        }
        boolean jolup = MainActivity.prefsbool.getBoolean("com.moonspace.talkmay5.graduated", true);
        //MainActivity.prefs.getInt("com.moonspace.talkmay5.HIGHSCORE",first);

        String graduated = "graduated";
        String droppedout = "dropped";
        firstplace.setTextSize(10);
        if(jolup) {
            firstplace.setText(haknyun + " 학년" + hakgi + " 학기 \n졸업 축하드립니다\n" + first +"일 소모");
        }
        else{
            firstplace.setText("졸업에 실패\n하셨습니다");
        }
        secondplace.setTextSize(10);
        secondplace.setText("그냥");
        thirdplace.setTextSize(10);
        thirdplace.setText("최근 하나만");
        fourthplace.setTextSize(10);
        fourthplace.setText("저장할게요");
        fifthplace.setTextSize(10);
        fifthplace.setText("화이팅");
    }

}
