package com.wonder.starmart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class review extends AppCompatActivity {
    DatabaseHelper mydb;
    Button button;
    String[] ssname,ssdesc,ssrate;
    private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

    //DB part
        mydb=new DatabaseHelper(this);
        mydb.insertDataReview("Yasiru","I've been to pizza hut restaurants in different countries. Although the pizzas are great the pasta and the lasagna are the worst I've had in Sri lanka. I once returned a pasta saying that this isn't the fresh pasta you offer in another country but they denied and well offered us with something for that value. I like there lava cake",4);
        mydb.insertDataReview("Xiler","Pizza hut is the dominating piza place in Colombo and its immediate suburbs in the country presently with a diversified network of restaurants in many cities with a delivery service. They are ok for pizzas for Sri Lankan standards",3);
        mydb.insertDataReview("badser","They have traditional pizzas so familiar worldwide. The service is excellent, and the pizzas are great!",2);
        mydb.insertDataReview("sorbal","Dette er vel stedet du besøker første gang du er i Sri Lanka.. Fordi det virker kjent og trygt. Vel, det er det jo. Men - Sri Lanka er ikke kjent for god pizza",1);
        mydb.insertDataReview("noone","If you are a fan of the chunky, thick dough pizz",5);
        button=(Button)findViewById(R.id.getrev);



        Cursor res=mydb.getAllDataReview();
        if (res.getCount() == 0){
            showMessage("Error","Nothing");
            return;
        }

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




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getAllDataReview();
                if (res.getCount() == 0){
                    showMessage("Error","Nothing");
                    return;
                }
                StringBuffer buffer=new StringBuffer();

                while (res.moveToNext()){
                    buffer.append("Id : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Description : "+res.getString(2)+"\n");
                    buffer.append("Rating : "+res.getString(3)+"\n");

                }
                showMessage("Data",buffer.toString());
            }
        });


        //other

        ListView list=(ListView)findViewById(R.id.list);
        Log.d(TAG,"onCreate: Started.");

        User user=new User(ssname[0],ssdesc[0],ssrate[0],"R.drawable.common_google_signin_btn_icon_dark");
        User user1=new User(ssname[1],ssdesc[1],ssrate[1],"R.drawable.common_google_signin_btn_icon_dark");
        User user2=new User(ssname[2],ssdesc[2],ssrate[2],"R.drawable.common_google_signin_btn_icon_dark");
        User user3=new User(ssname[3],ssdesc[3],ssrate[3],"R.drawable.common_google_signin_btn_icon_dark");
        User user4=new User(ssname[4],ssdesc[4],ssrate[4],"R.drawable.common_google_signin_btn_icon_dark");

        ArrayList<User> reviewers=new ArrayList<User>();
        reviewers.add(user);
        reviewers.add(user1);
        reviewers.add(user2);
        reviewers.add(user3);
        reviewers.add(user4);

        reviewersListAdapter adapter=new reviewersListAdapter(this,R.layout.list_layout,reviewers);
        list.setAdapter(adapter);
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
