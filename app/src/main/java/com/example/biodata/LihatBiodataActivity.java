package com.example.biodata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatBiodataActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button Button2;
    TextView Text1,Text2 ,Text3, Text4,Text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_biodata);

        dbHelper = new DataHelper(this);
        Text1 = (TextView) findViewById(R.id.txt1);
        Text2 = (TextView) findViewById(R.id.txt2);
        Text3 = (TextView) findViewById(R.id.txt3);
        Text4 = (TextView) findViewById(R.id.txt4);
        Text5 = (TextView) findViewById(R.id.txt5);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama='" +
                getIntent().getStringExtra("Nama") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            Text1.setText(cursor.getString(0).toString());
            Text2.setText(cursor.getString(1).toString());
            Text3.setText(cursor.getString(2).toString());
            Text4.setText(cursor.getString(3).toString());
            Text5.setText(cursor.getString(4).toString());
        }

        Button2 = (Button) findViewById(R.id.btn01);
        Button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
