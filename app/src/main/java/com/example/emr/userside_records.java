package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class userside_records extends AppCompatActivity {
    Button addrecord_btn, viewrecord_btn;
    TextView for_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userside_records);
        Intent intent= getIntent();
        String cname= intent.getStringExtra("ID");
        for_id= (TextView) findViewById(R.id.for_id);
        addrecord_btn= (Button) findViewById(R.id.addrecord_btn);
        viewrecord_btn= (Button) findViewById(R.id.viewrecord_btn);
        for_id.setText(cname);
        addrecord_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id= for_id.getText().toString();
                Intent intent= new Intent(userside_records.this, add_record.class);
                intent.putExtra("ID", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });

        viewrecord_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id= for_id.getText().toString();
                Intent intent= new Intent(userside_records.this, user_view_record.class);
                intent.putExtra("ID", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
        }
        return true;
    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(userside_records.this, user_login.class);
        startActivity(intent);
        //   finish();

    }
}