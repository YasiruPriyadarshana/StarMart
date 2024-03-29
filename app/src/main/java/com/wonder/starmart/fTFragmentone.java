package com.wonder.starmart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.FileInputStream;


public class fTFragmentone extends Fragment {
    private EditText inputmnum;
    private Button btnFrag;
    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_f_tfragmentone, container, false);
        btnFrag = (Button) view.findViewById(R.id.btnnext);
        inputmnum = (EditText) view.findViewById(R.id.input);


            String name = inputmnum.getText().toString().trim();
            btnFrag.setEnabled(!name.isEmpty());


        inputmnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name=inputmnum.getText().toString().trim();
                btnFrag.setEnabled(!name.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        btnFrag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                wirteFile();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_place, new ftFragmentTwo(), null);
                fr.commit();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }







    private void wirteFile() {

        String textToSave = inputmnum.getText().toString();
        String space = ",";
        try {
            FileOutputStream fileOutputStream = requireActivity().openFileOutput("appdata.txt", Context.MODE_APPEND);
            fileOutputStream.write((textToSave + space).getBytes());
            fileOutputStream.close();

            Toast.makeText(getActivity(), "text Saved", Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
