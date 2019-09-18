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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class shop_content extends AppCompatActivity {
    private Button review;
    DatabaseHelper mydb;

    String tr="true";
    String loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_content);
        readFile();
        if (loading.intern()==tr.intern()) {
            FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
            fr.add(R.id.fragment, new commentone());
            fr.commit();
        }else {
            FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
            fr.add(R.id.fragment, new commenttwo());
            fr.commit();
        }

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

    public void readFile(){
        try {
            FileInputStream fileInputStream = openFileInput("appreview.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines);
            }
            loading=stringBuffer.toString();
            Toast.makeText(this,loading,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
