package com.example.emr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class signup_page extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText id, name, surname, pass, mail, dob, phno;
    Button create_btn, view_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        Intent intent= getIntent();
        myDb= new DatabaseHelper(this);
        id= (EditText) findViewById(R.id.id_text);
        name= (EditText) findViewById(R.id.name_text);
        surname= (EditText) findViewById(R.id.surname_text);
        pass= (EditText) findViewById(R.id.pass_text);
        mail= (EditText) findViewById(R.id.email_text);
        dob= (EditText) findViewById(R.id.dob_text);
        phno= (EditText) findViewById(R.id.phone_text);
        create_btn= (Button) findViewById(R.id.create_btn);
        view_data= (Button) findViewById(R.id.view_data);
        //checkDataEntered();

        create_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                boolean isInserted= myDb.insertData(name.getText().toString(), surname.getText().toString(),
                        id.getText().toString(), mail.getText().toString(), phno.getText().toString(),
                        pass.getText().toString(), dob.getText().toString());
                //checkDataEntered();
                if(isInserted ==true)
                {
                    Toast.makeText(signup_page.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(signup_page.this, user_login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(signup_page.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }

        });

        view_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= myDb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("Name: "+ res.getString(0)+"\n");
                    buffer.append("Surname: "+ res.getString(1)+"\n");
                    buffer.append("ID: "+ res.getString(2)+"\n");
                    buffer.append("Email: "+ res.getString(3)+"\n\n");
                }

                showMessage("Data", buffer.toString());
            }


        });


    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }


//    void checkDataEntered() {
//        if (isEmpty(name)) {
//            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
//            t.show();
//        }
//
//        if (isEmpty(surname)) {
//            surname.setError("Last name is required!");
//        }
//        if (isEmpty(id)) {
//            id.setError("ID is required!");
//        }
//        if (isEmpty(pass)) {
//            pass.setError("Password is required!");
//        }
//        if (isEmpty(dob)) {
//            phno.setError("DOB is required!");
//        }
//        if (isEmpty(phno)) {
//            phno.setError("Phone number is required!");
//        }
//
//        if (isEmail(mail) == false) {
//            mail.setError("Enter valid email!");
//        }
//
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
        Intent intent = new Intent(signup_page.this, user_login.class);
        startActivity(intent);
        //   finish();

    }
}