package com.example.incomeandexpenses;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.*;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    TextView txtIncome,txtExpenses,txtBalance,txtDetails;
    Button btnEntry,btnReport;
    MyDatabase myDatabase;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        txtIncome=findViewById(R.id.txtIncome);
        txtExpenses=findViewById(R.id.txtExpenses);
        txtBalance=findViewById(R.id.txtBalance);
        txtDetails=findViewById(R.id.txtDetails);
        btnEntry=findViewById(R.id.btnEntry);
        btnReport=findViewById(R.id.btnReport);
        myDatabase=new MyDatabase(this);
        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,
                        DataEntry.class);
                startActivity(intent);
            }
        });
        txtDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,
                        TodayDetails.class);
                startActivity(intent);
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,
                        DateWiseReport.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        //displaying data of current date
        loadData();
    }
    public void loadData(){
        cursor=myDatabase.selectData(getCurrentDate());
        int income=0,expenses=0,balance=0;
        while (cursor.moveToNext()){
            income+=cursor.getInt(2);
            expenses+=cursor.getInt(3);
        }
        balance=income-expenses;
        txtIncome.setText(income+"");
        txtExpenses.setText(expenses+"");
        txtBalance.setText(balance+"");
    }
    public static String getCurrentDate(){
        //current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dat=formatter.format(date);
        return dat;
    }
}