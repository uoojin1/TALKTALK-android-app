package com.moonspace.talkmay5;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {


    //added firebase
    /*

    / Get ListView object from xml
   final ListView listView = (ListView) findViewById(R.id.listView);

   // Create a new Adapter
   final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
           android.R.layout.simple_list_item_1, android.R.id.text1);

   // Assign adapter to ListView
   listView.setAdapter(adapter);

   // Use Firebase to populate the list.
   Firebase.setAndroidContext(this);

   new Firebase("https://moonspace-talktalk.firebaseio.com/todoItems") // change to do items
           .addChildEventListener(new ChildEventListener() {
               public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                   adapter.add((String)dataSnapshot.child("text").getValue());
               }
               public void onChildRemoved(DataSnapshot dataSnapshot) {
                   adapter.remove((String)dataSnapshot.child("text").getValue());
               }
               public void onChildChanged(DataSnapshot dataSnapshot, String s) { }
               public void onChildMoved(DataSnapshot dataSnapshot, String s) { }
               public void onCancelled(FirebaseError firebaseError) { }
           });

     */


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    public static SharedPreferences prefs;
    public static SharedPreferences prefsbool;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /*
    * icons for the tabs
    */
    final int icons[] = new int[]{R.drawable.ic_face_white_24dp,
            R.drawable.ic_radio_white_18dp,
            R.drawable.ic_mail_outline_white_18dp,
            R.drawable.ic_videogame_asset_white_24dp
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Activitymain has the general view, toolbar and container for frag

        prefs = this.getSharedPreferences("com.moonspace.talkmay5.HIGHSCORE", Context.MODE_PRIVATE);
        prefsbool = this.getSharedPreferences("com.moonspace.talkmay5.graduated", Context.MODE_PRIVATE);
        //prefs.edit().putInt("com.moonspace.talkmay5.HIGHSCORE", 400).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name_korean));
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.tangerine_small);
        //just basically setting up the tool bar

        View logoView = toolbar.getChildAt(1);
        logoView.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast toast = Toast.makeText(getApplicationContext(),"톡!",Toast.LENGTH_SHORT);
                View view = toast.getView();
                //view.setBackgroundColor(getResources().getColor(R.color.orange));
                view.setBackground(getDrawable(R.drawable.tangerine_small));
                toast.setView(view);
                toast.show();
                return false;
            }
        });

        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container); // Viewpager holds the view of container
        mViewPager.setAdapter(mSectionsPagerAdapter);// this ViewPager will be controlled by the mSectionsPagerAdapter
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs); //  just getting Tab XML : TAB1, etc
        tabLayout.setupWithViewPager(mViewPager); // set this layout with viewpager which is the container of frag

        for(int i=0; i<icons.length; i++){
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        //String[] tabText = getResources().getStringArray(R.array.tabs);

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            if(position==0) {
                return Fragment1.newInstance(position + 1);
            }
            else if(position==1){
                return Fragment4.newInstance(position+1);
            }
            else if(position==2){
                return Fragment2.newInstance(position+1);
            }
            else if(position==3){
                return Fragment3.newInstance(position+1);
            }

            else return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
        // this getPageTitle scales the icons to the right size
        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable = getResources().getDrawable(icons[position]);
            drawable.setBounds(0,0,36,36);
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
    }
}
