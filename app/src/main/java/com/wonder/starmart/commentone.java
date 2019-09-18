package com.wonder.starmart;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class commentone extends Fragment {
    Button button;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for thisfragment
                view = inflater.inflate(R.layout.fragment_commentone, container, false);

        button=(Button)view.findViewById(R.id.editreview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddComment.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
