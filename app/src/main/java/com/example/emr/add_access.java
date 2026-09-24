package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_access extends AppCompatActivity {
    Button access_btn;
    EditText access_name, access_id;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_access);
        Intent intent= getIntent();
        access_name= (EditText) findViewById((R.id.access_name));
        access_id= (EditText) findViewById(R.id.access_id);
        access_btn= (Button) findViewById((R.id.access_btn));
        myDb= new DatabaseHelper(this);
        access_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= access_name.getText().toString();
                //String id= login_id.getText().toString();
                String id= access_id.getText().toString();
                if(name.equals("") || id.equals(""))
                {

                    Toast.makeText(add_access.this, "Please enter the credentials",
                            Toast.LENGTH_SHORT).show();
                }

                else
                {
                   // Log.d(TAG, "very 1st else");
                    Boolean result= myDb.checkaccess(name, id);

                    if(result==true)
                    {
                      //  Log.d(TAG, "2nd if");
                        Intent intent= new Intent(add_access.this, add_record.class);
                        intent.putExtra("ID", id);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);

                    }

                    else
                    {
                      //  Log.d(TAG, "very 2nd else");
                        Toast.makeText(add_access.this, "Wrong credentials", Toast.LENGTH_SHORT).show();


                    }

                }
            }
        });

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
        Intent intent = new Intent(add_access.this, hosp_login.class);
        startActivity(intent);
        //   finish();

    }
}