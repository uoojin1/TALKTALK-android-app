package com.moonspace.talkmay5;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by eugenemoon on 2016. 5. 7..
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            if(Build.VERSION.SDK_INT < 23.0) {
                imageView.setLayoutParams(new GridView.LayoutParams(380, 460));
            }
            else {
                imageView.setLayoutParams(new GridView.LayoutParams(480, 580));
            }
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        Picasso
                .with(mContext)
                .load(mThumbIds[position])
                .fit()
                .into(imageView);

        //imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.bumseok3, R.drawable.hayoung3, R.drawable.chaeyeon3,
            R.drawable.yujin3, R.drawable.yoonmi3, R.drawable.donghun3,
            R.drawable.yeseul3, R.drawable.jaewon3, R.drawable.hyeryoung3,
            R.drawable.seungmin4, R.drawable.seol, R.drawable.tofu



    };
}
