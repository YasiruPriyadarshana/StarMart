package com.wonder.starmart;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Item> list;
    ItemListAdapter adapter=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_activity);

        gridView=(GridView) findViewById(R.id.gridView);
        list=new ArrayList<>();
        adapter=new ItemListAdapter(this,R.layout.items,list);
        gridView.setAdapter(adapter);

        //get all data from sqlite
        Cursor cursor=ItemMain.sqLiteHelper.getData("SELECT * FROM ITEM");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String description=cursor.getString(2);
            String price=cursor.getString(3);
            byte[] image=cursor.getBlob(4);

            list.add(new Item(id,name,description,price,image));


        }
    }
}
