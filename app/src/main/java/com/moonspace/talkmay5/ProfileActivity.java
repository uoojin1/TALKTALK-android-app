package com.moonspace.talkmay5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by eugenemoon on 2016. 5. 16..
 */
public class ProfileActivity extends AppCompatActivity{
    public TextView name1;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilefragment);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    new ProfileFragment()).commit();
        }

    }
}
