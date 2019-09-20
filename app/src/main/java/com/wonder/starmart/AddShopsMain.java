package com.wonder.starmart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddShopsMain extends AppCompatActivity {
    EditText mEdtName, mEdtPhone, mEdtAddress, mEdtEmail, mEdtCategory;
    Button mBtnAdd, mBtnList;
    ImageView mImageView;

    final int REQUEST_CODE_GALLERY=999;

    public static SqlHelper mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shops_main);

        mEdtName = findViewById(R.id.edtName);
        mEdtPhone = findViewById(R.id.edtPhone);
        mEdtAddress = findViewById(R.id.edtAddress);
        mEdtEmail = findViewById(R.id.edtEmail);
        mEdtCategory = findViewById(R.id.edtCategory);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnList = findViewById(R.id.btnList);
        mImageView = findViewById(R.id.imageView);

        //creating database
        mSQLiteHelper = new SqlHelper(this,"RECORDDB.sqlite", null,1);

        //creating table in database
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, phone VARCHAR, address VARCHAR, email VARCHAR, category VARCHAR, image BLOB)");

        //select image by on imageview click
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddShopsMain.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );

            }
        });

        //add record to sqlite
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    mSQLiteHelper.insertData(
                            mEdtName.getText().toString().trim(),
                            mEdtPhone.getText().toString().trim(),
                            mEdtAddress.getText().toString().trim(),
                            mEdtEmail.getText().toString().trim(),
                            mEdtCategory.getText().toString().trim(),
                            mImageViewToByte(mImageView)


                    );
                    Toast.makeText(AddShopsMain.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    //reset views
                    mEdtName.setText("");
                    mEdtPhone.setText("");
                    mEdtAddress.setText("");
                    mEdtEmail.setText("");
                    mEdtCategory.setText("");
//                    mImageView.setImageResource(R.drawable.add_photo);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //show record list
        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start record list activity
                startActivity(new Intent(AddShopsMain.this, RecordListActivity.class));

            }
        });


    }

    private static byte[] mImageViewToByte(ImageView mImageView) {
        Bitmap bitmap= ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(this, "Don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
//            Uri imageUri = data.getData();
//            CropImage.activity(imageUri)
//                    .setGuidelines(CropImageView.Guidelines.ON)//enable image guidlines
//                    .setAspectRatio(1,1)//image will be square
//                    .start(this);
//
//        }
//        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if(requestCode==RESULT_OK){
//                Uri resultUri = result.getUri();
//                //set image choosed from gallery to image view
//                mImageView.setImageURI(resultUri);
//            }
//            else if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
//                Exception error = result.getError();
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
    }
}
