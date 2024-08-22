package com.example.incomeandexpenses;

import android.os.*;
import android.widget.*;
import android.view.*;
import android.database.*;
import android.system.*;
import androidx.appcompat.app.AppCompatActivity;

public class DataEntry extends AppCompatActivity {
    EditText edtDate,edtAmount;
    Spinner spTitle;
    Button btnSubmit;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.data_entry);
        edtDate=findViewById(R.id.edtDate);
        edtAmount=findViewById(R.id.edtAmount);
        spTitle=findViewById(R.id.spTitle);
        btnSubmit=findViewById(R.id.btnSubmit);
        myDatabase=new MyDatabase(this);
        edtDate.setText(MainActivity.getCurrentDate());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting data
                String date=edtDate.getText().toString();
                String title=spTitle.getSelectedItem().toString();
                String amount=edtAmount.getText().toString();
                //validating data
                if(date.equals("")){
                    Toast.makeText(DataEntry.this,"Please Enter Date !", Toast.LENGTH_LONG).show();
                } else if(amount.equals("")) {
                    Toast.makeText(DataEntry.this, "Please Enter Amount !",Toast.LENGTH_LONG).show();
                } else if(title.equals("Select Title")){
                    Toast.makeText(DataEntry.this,"Please Select Title !",Toast.LENGTH_LONG).show();
                }else{
                    //checking income or expenses
                    if(title.equals("Income"))
                        myDatabase.insertData(date,amount,"0");
                    else
                        myDatabase.insertData(date,"0",amount);
                    Toast.makeText(DataEntry.this,"Data Inserted Successfully !",Toast.LENGTH_LONG).show();
                    edtAmount.setText("");
                    spTitle.setSelection(0);
                }
            }
        });
    }
}