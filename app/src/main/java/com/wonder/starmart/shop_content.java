package com.wonder.starmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class shop_content extends AppCompatActivity {
    private Button review;
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_content);

        FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
        fr.add(R.id.fragment, new commentone());
        fr.commit();

        review=(Button)findViewById(R.id.reviewbt);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(shop_content.this,review.class);
                startActivity(intent);

            }
        });

        mydb=new DatabaseHelper(this);
        mydb.insertDataReview("class","If you are a fan of the chunky, thick dough pizz",5);






    }
}
