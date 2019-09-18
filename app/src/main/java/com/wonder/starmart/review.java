package com.wonder.starmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class review extends AppCompatActivity {

    private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ListView list=(ListView)findViewById(R.id.list);
        Log.d(TAG,"onCreate: Started.");

        User user=new User("Yasiru","ADAHDAADBHADADAD","2","R.drawable.common_google_signin_btn_icon_dark");
        User user1=new User("Yasru","AAHDAADBHADADAD","2","R.drawable.common_google_signin_btn_icon_dark");
        User user2=new User("Yasiu","HDAADBHADADAD","2","R.drawable.common_google_signin_btn_icon_dark");
        User user3=new User("Yasiru","ADAHDAADBHADADAD","3","R.drawable.common_google_signin_btn_icon_dark");
        User user4=new User("siru","AHDAADBHADADAD","2","R.drawable.common_google_signin_btn_icon_dark");

        ArrayList<User> reviewers=new ArrayList<User>();
        reviewers.add(user);
        reviewers.add(user1);
        reviewers.add(user2);
        reviewers.add(user3);
        reviewers.add(user4);

        reviewersListAdapter adapter=new reviewersListAdapter(this,R.layout.list_layout,reviewers);
        list.setAdapter(adapter);
    }
}
