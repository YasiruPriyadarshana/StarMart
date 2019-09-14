package com.wonder.starmart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class ftFragmentThree extends Fragment {

    EditText inputmnum;

    View inflatedView = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ft_fragment_three,container,false);
        view.bringToFront();
        final Button btnFrag3=(Button)view.findViewById(R.id.btnnext3);
        inputmnum=(EditText)view.findViewById(R.id.inputemail);

        inputmnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String field=inputmnum.getText().toString().trim();
                btnFrag3.setEnabled(!field.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wirteFile();
                Intent intent=new Intent(getActivity(),Home.class);
                getActivity().startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void wirteFile(){
        String textToSave = inputmnum.getText().toString();
        try {
            FileOutputStream fileOutputStream = requireActivity().openFileOutput("appdata.txt",Context.MODE_APPEND);
            fileOutputStream.write(textToSave.getBytes());
            fileOutputStream.close();

            Toast.makeText(getActivity(),"text Saved",Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void onDetach() {
        super.onDetach();
        File file = new File(requireActivity().getFilesDir(), "appdata.txt");
        if (file.exists())
            requireActivity().deleteFile("appdata.txt");
    }
}
