package com.moonspace.talkmay5;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.prefs.PreferenceChangeListener;

/**
 * Created by eugenemoon on 2016. 5. 5..
 */
public class Fragment1 extends android.support.v4.app.Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
   // public static GridView gridView_profileImg;
    static GridView gridView_profileImg;

    static ArrayList<String> posters;
    static PreferenceChangeListener listener;
    static SharedPreferences prefs;

    public ArrayList<String> nameArray ;
    public ArrayList<String> positionArray;
    public ArrayList<String> role;
    public ArrayList<Integer> profilePics;

    //public Array profilePics;

    public Fragment1() {
        initialize();
    }

    private void initialize() {
        positionArray = new ArrayList<String>();
        positionArray.add("0");
        positionArray.add("1");
        positionArray.add("2");
        positionArray.add("3");
        positionArray.add("4");
        positionArray.add("5");
        positionArray.add("6");
        positionArray.add("7");
        positionArray.add("8");
        positionArray.add("9");
        positionArray.add("10");
        positionArray.add("11");
        positionArray.add("12");

        nameArray = new ArrayList<String>();
        nameArray.add("Bumsuck Kim");
        nameArray.add("Hayoung Kim");
        nameArray.add("Chaeyun Bae");
        nameArray.add("Eugene Moon");
        nameArray.add("Yoonmi Park");
        nameArray.add("Donghoon Kim");
        nameArray.add("Yeseul Yoon");
        nameArray.add("Jaewon Jung");
        nameArray.add("Hyeryung Kim");
        nameArray.add("Seungmin Jung");
        nameArray.add("Snow Moon");
        nameArray.add("Tofu");

        role = new ArrayList<String>();
        role.add("Male DJ");
        role.add("Female DJ");
        role.add("Audio Director");
        role.add("Software Developer");
        role.add("Program Director");
        role.add("Junior Scriptor");
        role.add("UI/UX Designer, Scriptor");
        role.add("Scriptor");
        role.add("Marketing");
        role.add("Script Director");
        role.add("Mascot");
        role.add("Mascot");

        profilePics = new ArrayList<Integer>();
        profilePics.add(R.drawable.bumseok3);
        profilePics.add(R.drawable.hayoung3);
        profilePics.add(R.drawable.chaeyeon3);
        profilePics.add(R.drawable.yujin3);
        profilePics.add(R.drawable.yoonmi3);
        profilePics.add(R.drawable.donghun3);
        profilePics.add(R.drawable.yeseul3);
        profilePics.add(R.drawable.jaewon3);
        profilePics.add(R.drawable.hyeryoung3);
        profilePics.add(R.drawable.seungmin4);
        profilePics.add(R.drawable.seol);
        profilePics.add(R.drawable.tofu);

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment1 newInstance(int sectionNumber) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        gridView_profileImg = (GridView) rootView.findViewById(R.id.ProfileImg_gridview);
        gridView_profileImg.setAdapter(new ImageAdapter(getContext()));

        //happens on item click
        gridView_profileImg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(), "position" + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(),ProfileActivity.class).
                        putExtra("pos",position).
                        putExtra("PositionNum",positionArray).
                        putExtra("Name",nameArray).
                        putExtra("Role",role).
                        putExtra("profilePics", profilePics);
                startActivity(intent);

                /*ProfileFragment.newInstance(position);
                System.out.println("printing works from fragment1");
                /*
                inflater.inflate(R.layout.profilefragment,container,false);
                View childView = getLayoutInflater(savedInstanceState).inflate(R.layout.profilefragment,null);
                container.addView(childView);*/
            }
        });

        return rootView;
    }

}
