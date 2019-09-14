package com.wonder.starmart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class fTFragmentone extends Fragment {
    EditText inputmnum;

    View inflatedView = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_f_tfragmentone,container,false);
        Button btnFrag=(Button)view.findViewById(R.id.btnnext);

        btnFrag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentTransaction fr=requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_place,new ftFragmentTwo(),null);
                fr.commit();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bind your view here
    }
    private void wirteFile(){
        String textToSave = inputmnum.getText().toString();
        try {
            FileOutputStream fileOutputStream = getActivity().openFileOutput("appdata.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(textToSave.getBytes());
            fileOutputStream.close();

            Toast.makeText(getActivity(),"text Saved",Toast.LENGTH_SHORT).show();

            inputmnum.setText("");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
