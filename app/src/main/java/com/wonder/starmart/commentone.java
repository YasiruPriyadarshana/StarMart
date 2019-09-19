package com.wonder.starmart;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class commentone extends Fragment {
    Button button;
    View view;
    TextView name2,review2;
    RatingBar rate2;
    String[] id,ssname,ssdesc,ssrate;
    DatabaseHelper mydb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for thisfragment
                view = inflater.inflate(R.layout.fragment_commentone, container, false);

        name2=(TextView)view.findViewById(R.id.myname);
        review2=(TextView)view.findViewById(R.id.myreview);
        rate2=(RatingBar)view.findViewById(R.id.myrate3);
        button=(Button)view.findViewById(R.id.editreview);
        mydb = new DatabaseHelper(getContext());
        readFileforID();
        Toast.makeText(requireActivity(),id[1],Toast.LENGTH_SHORT).show();
        Cursor res=mydb.getReviewFromId((id[1]));


        StringBuffer name=new StringBuffer();
        StringBuffer desc=new StringBuffer();
        StringBuffer rate=new StringBuffer();;

        while (res.moveToNext()){

            name.append(res.getString(1)+",");
            desc.append(res.getString(2)+",");
            rate.append(res.getString(3)+",");
        }
        String sname = name.toString();
        String sdesc = desc.toString();
        String srate = rate.toString();

        ssname = sname.split(",");
        ssdesc = sdesc.split(",");
        ssrate = srate.split(",");

        name2.setText(ssname[0]);
        review2.setText(ssdesc[0]);
        rate2.setRating(Float.valueOf(ssrate[0]));



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddComment.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
    public boolean readFileforID(){

        try {
            FileInputStream fileInputStream = requireActivity().openFileInput("appreview.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();


            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "\n");

            }

            String str =stringBuffer.toString();
            id = str.split(",");
            return true;

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
