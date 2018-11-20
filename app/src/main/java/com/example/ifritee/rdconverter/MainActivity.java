package com.example.ifritee.rdconverter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DialogFragment DialogAbout_o;
    static final private int FILE_SYS_ACTIVITY = 1;
    private ListView MainList_o;
    private ArrayList<File> ListItems_lst = new ArrayList<>();
    private FileConvertAdapter FileConvertAdapter_o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogAbout_o = new DialogAbout();
        MainList_o = findViewById(R.id.lvRDfiles);
        MainList_o.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object clickItemObj = parent.getAdapter().getItem(position);
                final File OpenFile_o = (File)clickItemObj;

            }
        });
        FileConvertAdapter_o=new FileConvertAdapter(this, ListItems_lst);
        MainList_o.setAdapter(FileConvertAdapter_o);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu MainMenu_o) {
        getMenuInflater().inflate(R.menu.main_menu, MainMenu_o);
        return super.onCreateOptionsMenu(MainMenu_o);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Выведем в Toast информацию о нажатом пункте меню
        StringBuilder sb = new StringBuilder();
        sb.append("Пункт меню");
        sb.append("\r\n itemId: " + String.valueOf(item.getItemId()));
        sb.append("\r\n title: " + item.getTitle());
        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case R.id.mmenu_about:
                Log.i("Кнопка нажата", "О программе");
                // вызываем диалог
                DialogAbout_o.show(getSupportFragmentManager(), "DialogAbout");
                break;
            case R.id.mmenu_exit:
                Log.i("Кнопка нажата", "Выход");
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addFileClick(View v) {
        Intent intent = new Intent(MainActivity.this, FileSystemActivity.class);
        startActivityForResult(intent, FILE_SYS_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, data.getStringExtra("FileName"), Toast.LENGTH_SHORT).show();
        if(resultCode == RESULT_OK) {
            if(requestCode == FILE_SYS_ACTIVITY) {
                ListItems_lst.add(new File(data.getStringExtra("FileName")));
                FileConvertAdapter_o.notifyDataSetChanged();
                Toast.makeText(this, data.getStringExtra("FileName"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
