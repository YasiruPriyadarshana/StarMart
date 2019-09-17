package com.wonder.starmart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button getdata;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        descriptxt=(TextView)findViewById(R.id.decrip);
        phnum=(TextView)findViewById(R.id.phonenum) ;
        emil=(TextView)findViewById(R.id.emailadd) ;
        name=(TextView)findViewById(R.id.name) ;
        getdata=(Button)findViewById(R.id.getdata);
        myDb=new DatabaseHelper(this);
        readFile();

        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=myDb.getAllData();
                if (res.getCount() == 0){
                    showMessage("Error","Nothing");
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : "+res.getString(0)+"\n");
                    buffer.append("PhoneNumber : "+res.getString(1)+"\n");
                    buffer.append("Name : "+res.getString(2)+"\n");
                    buffer.append("Email : "+res.getString(3)+"\n");
                }

                showMessage("Data",buffer.toString());
            }
        });
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

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
