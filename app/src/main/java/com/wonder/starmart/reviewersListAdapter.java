package com.wonder.starmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        User user = new User(name,description);

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tname=(TextView)convertView.findViewById(R.id.name);
        TextView tdesc=(TextView)convertView.findViewById(R.id.desc);

        tname.setText(name);
        tdesc.setText(description);

        return convertView;
    }
}
