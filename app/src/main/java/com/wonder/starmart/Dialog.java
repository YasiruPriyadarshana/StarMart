package com.wonder.starmart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dialog extends AppCompatDialogFragment {
    private EditText duser,dnum,demil;
    private int i;
    private String[] array,array2;
    private DatabaseHelper myDb;
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        myDb=new DatabaseHelper(getContext());
        if (i==1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            Dialogname(builder);
            return builder.create();
        }else if(i==2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            DialogEmail(builder);
            return builder.create();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            DialogNumber(builder);
            return builder.create();
        }
    }

    private void Dialogname(AlertDialog.Builder builder){
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view)
                .setTitle("Change Name")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        wirteFile(duser);
                    }
                })
        ;
        duser =view.findViewById(R.id.duser);
    }
    private void DialogEmail(AlertDialog.Builder builder){
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.layout_dialog2,null);

        builder.setView(view)
                .setTitle("Change Email")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        wirteFile(demil);
                        update();
                    }
                })
        ;
        demil =view.findViewById(R.id.demail);
    }

    private void DialogNumber(AlertDialog.Builder builder){
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view= inflater.inflate(R.layout.layout_dialog3,null);

        builder.setView(view)
                .setTitle("Change Number")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        wirteFile(dnum);
                    }
                })
        ;
        dnum =view.findViewById(R.id.dnum);
    }






    public void seti(){
        i=1;
    }
    public void seti2(){
        i=2;
    }
    public void seti3(){
        i=3;
    }


    private void wirteFile(EditText text){
        //read
        try {
            FileInputStream fileInputStream = requireActivity().openFileInput("appdata.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();


            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "\n");
            }
            String str =stringBuffer.toString();
            array = str.split(",");

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        //delete
        File file = new File(requireActivity().getFilesDir(), "appdata.txt");
        if (file.exists())
            requireActivity().deleteFile("appdata.txt");
        //write
        String textToSave = text.getText().toString();

        try {
            if(text == dnum) {
                FileOutputStream fileOutputStream = requireActivity().openFileOutput("appdata.txt", Context.MODE_APPEND);
                fileOutputStream.write((textToSave + "," + array[1] + "," + array[2]+ "," + array[3]).getBytes());
                fileOutputStream.close();
            }else if (text == duser){
                FileOutputStream fileOutputStream = requireActivity().openFileOutput("appdata.txt", Context.MODE_APPEND);
                fileOutputStream.write((array[0] + "," + textToSave + "," + array[2]+ "," + array[3]).getBytes());
                fileOutputStream.close();
            }else {
                FileOutputStream fileOutputStream = requireActivity().openFileOutput("appdata.txt", Context.MODE_APPEND);
                fileOutputStream.write((array[0] + "," + array[1]+ "," + textToSave+ ","  + array[3]).getBytes());
                fileOutputStream.close();
            }
            Toast.makeText(getActivity(),"text Saved",Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        update();
    }
    private void update(){
        readFile();
        boolean isUpdated = myDb.updateData(array2[3],
                array2[0],
                array2[1],
                array2[2]);
        if (isUpdated){
//            Toast.makeText(getActivity(),"Data Updated",Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(getActivity(),"Data Not Updated",Toast.LENGTH_SHORT).show();
        }
    }
    public void readFile(){
        try {
            FileInputStream fileInputStream = requireActivity().openFileInput("appdata.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();


            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "\n");
            }
            String str =stringBuffer.toString();
            array2 = str.split(",");


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
