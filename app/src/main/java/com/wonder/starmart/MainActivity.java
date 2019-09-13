package com.wonder.starmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    Button next;
    int next_counter;
    Intent in_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFragmentChangerListener();



    }
    public void firstFragmentChangerListener(){
        next=(Button)findViewById(R.id.button1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (next_counter==0){
                    Fragment fragment=new ftFragmentTwo();
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.fragment_place,fragment);
                    ft.commit();
                    next_counter++;

                    ConstraintLayout currentLayout =
                            (ConstraintLayout) findViewById(R.id.main);
                    currentLayout.setBackgroundColor(getcolor());
                }
                else if(next_counter==1){
                    Fragment fragment2=new ftFragmentThree();
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.fragment_place,fragment2);
                    ft.commit();
                    next_counter++;
                    ConstraintLayout currentLayout =
                            (ConstraintLayout) findViewById(R.id.main);
                    currentLayout.setBackgroundColor(getcolor2());
                }
                else if(next_counter==2){
                    in_next=new Intent(MainActivity.this,Home.class);
                    startActivity(in_next);
                }
            }
        });
    }
    private int getcolor(){
        return ResourcesCompat.getColor(getResources(), R.color.mycolor1, null);
    }
    private int getcolor2(){
        return ResourcesCompat.getColor(getResources(), R.color.mycolor2, null);
    }

}
