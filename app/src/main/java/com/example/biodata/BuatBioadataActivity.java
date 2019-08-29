package com.example.biodata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BuatBioadataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn01,btn02;
    EditText Text1,Text2,Text3,Text4,Text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_bioadata);

        dbHelper=new DataHelper(this);
        Text1=(EditText)findViewById(R.id.edt1);
        Text2=(EditText)findViewById(R.id.edt2);
        Text3=(EditText)findViewById(R.id.edt3);
        Text4=(EditText)findViewById(R.id.edt4);
        Text5=(EditText)findViewById(R.id.edt5);
        btn01=(Button)findViewById(R.id.btn01);
        btn02=(Button)findViewById(R.id.btn02);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.execSQL("insert into biodata(no,nama,tanggal,jk,alamat) values('"+
                        Text1.getText().toString()+"','"+
                        Text2.getText().toString()+"','"+
                        Text3.getText().toString()+"','"+
                        Text4.getText().toString()+"','"+
                        Text5.getText().toString()+"')");

                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
                MainActivity.utama.RefreshList();
                finish();
            }

        });

        btn02.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
