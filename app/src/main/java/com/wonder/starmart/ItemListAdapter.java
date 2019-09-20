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

public class ItemListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Item> itemsList;

    public ItemListAdapter(Context context, int layout, ArrayList<Item> itemsList) {
        this.context = context;
        this.layout = layout;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textName,textdes,textprice;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row=view;
        ViewHolder holder=new ViewHolder();

        if(row == null){

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout, null);

            holder.textName=(TextView) row.findViewById(R.id.textName);
            holder.textdes=(TextView) row.findViewById(R.id.textdes);
            holder.textprice=(TextView) row.findViewById(R.id.textprice);
            row.setTag(holder);

        }
        else{
            holder= (ViewHolder) row.getTag();
        }

        Item item= itemsList.get(position);

        holder.textName.setText(item.getName());
        holder.textdes.setText(item.getDescription());
        holder.textprice.setText(item.getPrice());

        byte[] itemImage=item.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0,itemImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
