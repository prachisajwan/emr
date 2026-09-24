package com.example.emr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add_record extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText record_hospitalname, record_test, record_prescription, record_result, record_report, record_bill;
    TextView record_id;
    Button record_addbtn, record_viewbtn;
    private static final String TAG = "November";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Intent intent = getIntent();
        String cname= intent.getStringExtra("ID");
        record_id = (TextView) findViewById(R.id.record_id);
        record_hospitalname = (EditText) findViewById(R.id.record_hospitalname);
        record_test = (EditText) findViewById(R.id.record_test);
        record_prescription = (EditText) findViewById(R.id.record_prescription);
        record_result = (EditText) findViewById(R.id.record_result);
        record_report = (EditText) findViewById(R.id.record_report);
        record_bill = (EditText) findViewById(R.id.record_bill);
        record_addbtn = (Button) findViewById(R.id.record_add);
        record_viewbtn = (Button) findViewById(R.id.record_view);

        myDb = new DatabaseHelper(this);
        record_id.setText(cname);

        record_addbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean result = checkDataEntered();
                Log.d(TAG, "result: " + result);
                if(result) {
                    boolean isInserted = myDb.insertRecords(record_id.getText().toString(),
                            record_hospitalname.getText().toString(), record_test.getText().toString(),
                            record_prescription.getText().toString(), record_result.getText().toString(),
                            record_report.getText().toString(), record_bill.getText().toString());
                    //checkDataEntered();
                    Log.d(TAG, "very 1st else");
                    Log.d(TAG, "Data insterted: " + isInserted);
                    if (isInserted == true) {
                        Toast.makeText(add_record.this, "Data Inserted", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(add_record.this, MainActivity.class);
//                    startActivity(intent);
                    } else {
                        Toast.makeText(add_record.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        record_viewbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Cursor res = myDb.getAllRecords();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Hospital Name: " + res.getString(1) + "\n");
                    buffer.append("Test: " + res.getString(2) + "\n");
                    buffer.append("Prescription: " + res.getString(3) + "\n");
                    buffer.append("Result: " + res.getString(4) + "\n\n");
                }

                showMessage("Data", buffer.toString());
            }


        });
    }
    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    boolean checkDataEntered() {
        boolean result = true;
//        if (isEmpty(record_id)) {
//            Toast t = Toast.makeText(this, "You must enter ID!", Toast.LENGTH_SHORT);
//            t.show();
//        }

        if (isEmpty(record_hospitalname)) {
            record_hospitalname.setError("Hospital name is required!");
            result = false;
        }
        if (isEmpty(record_test)) {
            record_test.setError("Test name is required!");
            result = false;
        }
        if (isEmpty(record_prescription)) {
            record_prescription.setError("Prescription is required!");
            result = false;
        }
        if (isEmpty(record_result)) {
            record_result.setError("Result is required!");
            result = false;
        }
        return result;

    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
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
        Intent intent = new Intent(add_record.this, MainActivity.class);
        startActivity(intent);
     finish();

    }
}