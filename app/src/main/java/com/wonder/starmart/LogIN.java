package com.wonder.starmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class LogIN extends AppCompatActivity {
    TextView descriptxt,phnum,emil,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        descriptxt=(TextView)findViewById(R.id.decrip);
        phnum=(TextView)findViewById(R.id.phonenum) ;
        emil=(TextView)findViewById(R.id.emailadd) ;
        name=(TextView)findViewById(R.id.name) ;
        readFile();
    }

    public void readFile(){
        try {
            FileInputStream fileInputStream = openFileInput("appdata.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();


            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "\n");
            }
            String str =stringBuffer.toString();
            String[] array = str.split(",");

            phnum.setText(array[0]);
            name.setText(array[1]);
            emil.setText(array[2]);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
