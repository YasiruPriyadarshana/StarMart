package com.wonder.starmart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;



public class ItemMain extends AppCompatActivity {
    TextView txttitle;
    EditText editName,editdescription,editprice;
    Button btnChoose,btnAdd,btnList;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY=999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_main);
        init();

        sqLiteHelper=new SQLiteHelper(this,"ItemDB.sqlite",null,1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ITEM(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, description VARCHAR, price VARCHAR, image BLOG)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        ItemMain.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            editName.getText().toString().trim(),
                            editdescription.getText().toString().trim(),
                            editprice.getText().toString().trim(),
                            imageViewToByte(imageView)
                    );
                    Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
                    editName.setText("");
                    editdescription.setText("");
                    editprice.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }


        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ItemMain.this,ItemList.class);
                startActivity(intent);
            }
        });

    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(),"You don't have permission to access file location!",Toast.LENGTH_SHORT).show();

            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data !=null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        txttitle=(TextView) findViewById(R.id.txttitle);
        editName=(EditText) findViewById(R.id.editName);
        editdescription=(EditText) findViewById(R.id.editdescription);
        editprice=(EditText) findViewById(R.id.editprice);
        btnChoose=(Button) findViewById(R.id.btnChoose);
        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnList=(Button) findViewById(R.id.btnList);
        imageView=(ImageView) findViewById(R.id.imageView);

    }
}
