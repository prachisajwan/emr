package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class check_access extends AppCompatActivity {
    Button checkaccess;
    EditText name_access, id_access;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_access);
        Intent intent= getIntent();
        name_access= (EditText) findViewById(R.id.pname);
        id_access= (EditText) findViewById(R.id.pid);
        checkaccess= (Button) findViewById(R.id.checkaccess_btn);
        myDb= new DatabaseHelper(this);

        checkaccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= name_access.getText().toString();
                String id_acc= id_access.getText().toString();
                if(name.equals("") || id_acc.equals(""))
                {

                    Toast.makeText(check_access.this, "Please enter the credentials", Toast.LENGTH_SHORT).show();
                }

                else {
                    // Log.d(TAG, "very 1st else");
                    Boolean result = myDb.checkaccess(name, id_acc);

                    if (result == true) {
                        Intent intent = new Intent(check_access.this, view_record.class);
                        intent.putExtra("ID", id_acc);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }
                    else
                    {
                        //  Log.d(TAG, "very 2nd else");
                        Toast.makeText(check_access.this, "Wrong credentials", Toast.LENGTH_SHORT).show();


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
        Intent intent = new Intent(check_access.this, hosp_login.class);
        startActivity(intent);
        //   finish();

    }
}