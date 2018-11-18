package com.example.ifritee.rdconverter;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FileSystemActivity extends AppCompatActivity {

    private ListView FileSystemList_o;
    private  TextView FullPath_o;
    private String PathName_str;

    private final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_system);
        FileSystemList_o = findViewById(R.id.file_system);
        FileSystemList_o.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object clickItemObj = parent.getAdapter().getItem(position);
                final File OpenFile_o = (File)clickItemObj;
                if(OpenFile_o.isDirectory()) {
                    File Path_o = new File(PathName_str + OpenFile_o.getName() + "/");
                    if(Path_o.canRead()) {
                        PathName_str += OpenFile_o.getName() + "/";
                        OpenDirectory_v(PathName_str);
                    }
                    else {
                        Toast.makeText(FileSystemActivity.this, "Path not read: " + PathName_str + OpenFile_o.getName() + "/", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (OpenFile_o.isFile()) { // Открываем если файл
                    AlertDialog.Builder Dialog_o = new AlertDialog.Builder(FileSystemActivity.this);
                    Dialog_o.setTitle("Открыть файл")
                            .setMessage("Открыть файл " + OpenFile_o.toString())
                            .setCancelable(false)
                            .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).setPositiveButton("Открыть файл", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent FileIntent_o = new Intent();
                                    FileIntent_o.putExtra("FileName", OpenFile_o.toString());
                                    setResult(RESULT_OK, FileIntent_o);
                                    finish();
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog AlertDialog_o = Dialog_o.create();
                    AlertDialog_o.show();
                    }
            }
        });
        FullPath_o = findViewById(R.id.full_path);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        }
        else {
            PathName_str = Environment.getExternalStorageDirectory().toString() + "/";
            OpenDirectory_v(PathName_str);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        OpenDirectory_v(Environment.getExternalStorageDirectory().toString() + "/");
    }

    private void OpenDirectory_v(String BeginPath_str)
    {
        File Path_o = new File(BeginPath_str);
        ArrayList<File> Files_lst = new ArrayList<>(Arrays.asList(Path_o.listFiles()));
        Collections.sort(Files_lst);
        FileSystemList_o.setAdapter(new TwoActivityAdapter(this, Files_lst));
        FullPath_o.setText(BeginPath_str);
    }

    public void BackDirectoryClick_v(View v) {
        File Path_o = new File(PathName_str);
        if(Path_o.getParentFile().canRead()) {
            PathName_str = Path_o.getParent() + "/";
            OpenDirectory_v(PathName_str);
        }
        else {
            Toast.makeText(FileSystemActivity.this, "Path not read: " + Path_o.getParentFile().toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public void ExtClick_v(View v) {
        PathName_str = Environment.getExternalStorageDirectory().toString() + "/";
        OpenDirectory_v(PathName_str);
    }

    public void SDClick_v(View v) {
        PathName_str = "/storage/";
        OpenDirectory_v(PathName_str);
    }


}
