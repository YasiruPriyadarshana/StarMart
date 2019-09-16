package com.wonder.starmart;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Starting extends AppCompatActivity {
    private EditText inputmnum;
    private Button btnFrag;
    boolean skip=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        if (readFile()){
            skip=true;
        }
        if(!skip) {
            FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
            fr.add(R.id.fragment_place, new fTFragmentone());
            fr.commit();
         }else {
            Intent intent=new Intent(Starting.this,Home.class);
            startActivity(intent);
        }



    }



    public boolean readFile(){
        try {
            FileInputStream fileInputStream = openFileInput("appdata.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();

            String lines;
            if ((lines = bufferedReader.readLine()) != null){
                return true;
            }


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

            File file = new File(getFilesDir(), "appdata.txt");
            if (file.exists())
                deleteFile("appdata.txt");
        }

}