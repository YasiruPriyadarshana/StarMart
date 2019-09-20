package com.wonder.starmart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Model> recordList;

    public RecordListAdapter(Context context, int layout, ArrayList<Model> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPhone, txtAddress, txtEmail, txtCategory;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row==null){
            LayoutInflater inflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.txtName = row.findViewById(R.id.txtName);
            holder.txtPhone = row.findViewById(R.id.txtPhone);
            holder.txtAddress = row.findViewById(R.id.txtAddress);
            holder.txtEmail = row.findViewById(R.id.txtEmail);
            holder.txtCategory = row.findViewById(R.id.txtCategory);
            holder.imageView = row.findViewById(R.id.imgIcon);
            row.setTag(holder);

        }
        else{
            holder = (ViewHolder)row.getTag();
        }

        Model model = recordList.get(i);

        holder.txtName.setText(model.getName());
        holder.txtPhone.setText(model.getPhone());
        holder.txtAddress.setText(model.getAddress());
        holder.txtEmail.setText(model.getEmail());
        holder.txtCategory.setText(model.getCategory());

        byte[] recordImage = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        holder.imageView.setImageBitmap(bitmap);


        return row;
    }
}
