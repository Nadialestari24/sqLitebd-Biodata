package com.example.biodata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity utama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button klik=(Button)findViewById(R.id.btn2);

        klik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buat=new Intent(MainActivity.this,BuatBioadataActivity.class);
                startActivity(buat);

            }
        });
        utama=this;
        dbcenter=new DataHelper(this);
        RefreshList();
    }
    public void RefreshList(){
        SQLiteDatabase db=dbcenter.getReadableDatabase();
        cursor=db.rawQuery("SELECT*FROM biodata",null);
        daftar=new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0;cc<cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            daftar[cc]=cursor.getString(1).toString();
        }
        ListView01=(ListView)findViewById(R.id.lv_Biodata);
        ListView01.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View arg0, int arg2, long arg3) {
                final String selection = daftar[arg2];//.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogItem = {"Lihat Biodata", "Update Biodata", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item) {
                            case 0:
                                Intent lihat = new Intent(getApplicationContext(), LihatBiodataActivity.class);
                                lihat.putExtra("Nama", selection);
                                startActivity(lihat);
                                break;
                            case 1:
                                Intent update = new Intent(getApplicationContext(), UpdateBiodataActivity.class);
                                update.putExtra("Nama", selection);
                                startActivity(update);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("Delete From Biodata Where Nama='" + selection + "'");
                                RefreshList();
                                break;
                        }

                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
        }

    }

