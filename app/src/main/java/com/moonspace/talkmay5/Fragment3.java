package com.moonspace.talkmay5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ToggleButton;

/**
 * Created by eugenemoon on 2016. 7. 12..
 */
public class Fragment3 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public Fragment3(){

    }

    public static Fragment3 newInstance(int sectionNumber) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3, container, false);
        Button highscoreBtn = (Button)rootView.findViewById(R.id.highscore);
        Button startButton = (Button)rootView.findViewById(R.id.startgamebtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), game.class);
                startActivity(intent);
            }
        });
        highscoreBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), score.class);
                startActivity(intent);
            }
        });
        return rootView;
    }


}
