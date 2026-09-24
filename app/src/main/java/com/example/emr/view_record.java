package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.ArrayList;

public class view_record extends AppCompatActivity {
    ListView hosp_listview;
    TextView id_textview;
    DatabaseHelper myDb;
    private static final String TAG = "November View";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        Intent intent= getIntent();


        hosp_listview= (ListView) findViewById(R.id.hosp_listview);
        id_textview= (TextView) findViewById(R.id.id_textview);
        myDb= new DatabaseHelper(this);
       // final ArrayList array_list = myDb.getRecords();
        String cname= intent.getStringExtra("ID");
        Log.d(TAG, cname+"");

        id_textview.setText(cname);
        String id_new= id_textview.getText().toString();
        Log.d(TAG, id_new+"");
        ArrayList llist= myDb.getFullData(id_new);
        Log.d(TAG, llist+"");
        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, llist);
        hosp_listview.setAdapter(arrayAdapter);

    }
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

        Intent intent = new Intent(view_record.this, hosp_login.class);
        startActivity(intent);
        finish();

    }
}