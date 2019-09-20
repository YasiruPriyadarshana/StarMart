  package com.wonder.starmart;

  import android.app.Activity;
  import android.app.Dialog;
  import android.content.DialogInterface;
  import android.database.Cursor;
  import android.os.Bundle;
  import android.view.View;
  import android.widget.AdapterView;
  import android.widget.EditText;
  import android.widget.ImageView;
  import android.widget.ListView;
  import android.widget.Toast;

  import androidx.appcompat.app.AlertDialog;
  import androidx.appcompat.app.AppCompatActivity;

  import java.util.ArrayList;

  public class RecordListActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<Model> mList;
    RecordListAdapter mAdapter = null;

    ImageView imageViewIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this,R.layout.row, mList);
        mListView.setAdapter(mAdapter);

        //get all data from sqlite
        Cursor cursor = AddShopsMain.mSQLiteHelper.getData("SELECT*FROM RECORD");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String address = cursor.getString(3);
            String email = cursor.getString(4);
            String category = cursor.getString(5);
            byte[] image = cursor.getBlob(6);
            //add to list
            mList.add(new Model(id, name, phone, address, email, category, image));

        }

        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            Toast.makeText(this, "No record found...", Toast.LENGTH_SHORT).show();
        }
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                CharSequence[] items = {"Update","Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(RecordListActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){
                            Cursor c = AddShopsMain.mSQLiteHelper.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }

                        }
                        if(i == 1){

                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    private void  showDialogUpdate(Activity activity, int position){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update");

//        imageViewIcon = dialog.findViewById(R.id.imageViewRecord);
//        EditText edtName = dialog.findViewById(R.id.imageViewRecord);
//        EditText edtPhone = dialog.findViewById(R.id.imageViewRecord);
//        EditText edtAddress = dialog.findViewById(R.id.imageViewRecord);
//        EditText edtEmail = dialog.findViewById(R.id.imageViewRecord);
//        EditText edtCategory = dialog.findViewById(R.id.imageViewRecord);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().widthPixels*0.7);
        dialog.getWindow().setLayout(width,height);

        imageViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
