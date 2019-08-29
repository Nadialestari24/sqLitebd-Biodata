package com.example.biodata;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBiodataActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn01,btn02;
    EditText Text1,Text2,Text3,Text4,Text5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);

        dbHelper=new DataHelper(this);
        Text1=(EditText)findViewById(R.id.edt01);
        Text2=(EditText)findViewById(R.id.edt02);
        Text3=(EditText)findViewById(R.id.edt03);
        Text4=(EditText)findViewById(R.id.edt04);
        Text5=(EditText)findViewById(R.id.edt05);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT* FROM biodata WHERE nama='" +
                getIntent().getStringExtra("Nama")+"'", null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            Text1.setText(cursor.getString(0).toString());
            Text2.setText(cursor.getString(1).toString());
            Text3.setText(cursor.getString(2).toString());
            Text4.setText(cursor.getString(3).toString());
            Text5.setText(cursor.getString(4).toString());
        }
        btn01=(Button)findViewById(R.id.btn01);
        btn02=(Button)findViewById(R.id.btn02);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getReadableDatabase();
                db.execSQL("update biodata set nama = '"+
                        Text2.getText().toString()+"',tanggal='"+
                        Text3.getText().toString()+"',jk='"+
                        Text4.getText().toString()+"',alamat='"+
                        Text5.getText().toString()+"'where no='"+
                        Text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
                MainActivity.utama.RefreshList();
                finish();
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
