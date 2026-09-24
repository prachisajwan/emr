package com.example.emr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    DatabaseHelper myDb;
    //SQLiteOpenHelper db;
    EditText login_email, login_id, login_pass;
    Button log_btn;
    Cursor cursor;
    private static final String TAG = "November";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Intent intent= getIntent();
        myDb= new DatabaseHelper(this);
        //db= myDb.getReadableDatabase();
        login_email= (EditText) findViewById(R.id.name_login);
        login_id= (EditText) findViewById(R.id.id_login);
        login_pass= (EditText) findViewById(R.id.pass_login);
        log_btn= (Button) findViewById(R.id.log_btn);

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= login_email.getText().toString();
                String id= login_id.getText().toString();
                String pass= login_pass.getText().toString();
                if(email.equals("") || id.equals("") || pass.equals(""))
                {
                    Log.d(TAG, "very 1st if");
                    Toast.makeText(login_page.this, "Please enter the credentials",
                            Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Log.d(TAG, "very 1st else");
                    Boolean result= myDb.checkcredentials(email, id, pass);

                    if(result==true)
                    {
                        Log.d(TAG, "2nd if");
                        Intent intent= new Intent(login_page.this, userside_records.class);
                        intent.putExtra("ID", id);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);

                    }

                    else
                    {
                        Log.d(TAG, "very 2nd else");
                        Toast.makeText(login_page.this, "Wrong credentials", Toast.LENGTH_SHORT).show();


                    }

                }
            }
        });

        //dialog.show();
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
        Intent intent = new Intent(login_page.this, user_login.class);
        startActivity(intent);
        //   finish();

    }
}