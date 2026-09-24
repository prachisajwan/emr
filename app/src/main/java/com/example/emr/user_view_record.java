package com.example.emr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class user_view_record extends AppCompatActivity {
    ListView user_listview;
    TextView id_textview;
    DatabaseHelper myDb;
    private static final String TAG = "November User";
    ArrayList<String> llist;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_record);
        Intent intent= getIntent();
        user_listview= (ListView) findViewById(R.id.llist_view);
        id_textview= (TextView) findViewById(R.id.textView2);
        myDb= new DatabaseHelper(this);
        // final ArrayList array_list = myDb.getRecords();
        String cname= intent.getStringExtra("ID");
        Log.d(TAG, cname+"");

        id_textview.setText(cname);
        String id_new= id_textview.getText().toString();
        Log.d(TAG, id_new+"");
        llist= myDb.getFullDataUser(id_new);
        Log.d(TAG, llist+"");
        arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, llist);
        user_listview.setAdapter(arrayAdapter);

        user_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id_up= id_textview.getText().toString();

                Intent intent = new Intent(user_view_record.this, Update_records.class);
                intent.putExtra("ID", id_up);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);

            }
        });

    }
//    public void showInputBox(String oldItem, final int index){
//        final Dialog dialog=new Dialog(user_view_record.this);
//        dialog.setTitle("Input Box");
//        dialog.setContentView(R.layout.input_box);
//        TextView txtMessage=(TextView)dialog.findViewById(R.id.txtmessage);
//        txtMessage.setText("Update item");
//        txtMessage.setTextColor(Color.parseColor("#ff2222"));
//        final EditText editText=(EditText)dialog.findViewById(R.id.txtinput);
////        final EditText editText2=(EditText)dialog.findViewById(R.id.txtinput2);
////        final EditText editText3=(EditText)dialog.findViewById(R.id.txtinput3);
//        editText.setText(oldItem);
//        Button bt=(Button)dialog.findViewById(R.id.btdone);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                llist.set(index,editText.getText().toString());
//                arrayAdapter.notifyDataSetChanged();
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                onBackPressed();
                break;
        }
        return true;
    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(user_view_record.this, userside_records.class);
        startActivity(intent);
        finish();

    }
}

