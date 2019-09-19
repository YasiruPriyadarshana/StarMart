package com.wonder.starmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class reviewersListAdapter  extends ArrayAdapter<User> {
    private static final String TAG="PersonListAdapter";
    private Context mContext;
    int mResource;

    public reviewersListAdapter(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name=getItem(position).getName();
        String description=getItem(position).getDecription();
        String rating=getItem(position).getRatingBar();
        String image=getItem(position).getImage();

        User user = new User(name,description,rating,image);

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tname=(TextView)convertView.findViewById(R.id.myname);
        TextView tdesc=(TextView)convertView.findViewById(R.id.desc);
        RatingBar bar=(RatingBar)convertView.findViewById(R.id.ratingBar);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.userIcon);

        tname.setText(name);
        tdesc.setText(description);
        bar.setRating(Float.parseFloat(rating));
        imageView.setImageResource(R.drawable.common_google_signin_btn_icon_dark);
        return convertView;
    }
}
