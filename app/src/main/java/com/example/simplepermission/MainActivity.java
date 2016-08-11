package com.example.simplepermission;


import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    private static final int CODE_CONSTANT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doMagic(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "doMagic: " + "We don't have permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_CONSTANT);
        } else {
            Log.d(TAG, "doMagic: " + "We already have permission");
            writeTimeExternal();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case CODE_CONSTANT:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: " + "User gave permission");
                    writeTimeExternal();
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: " + "User didn't give permission");
                }
        }

    }

    private void writeTimeExternal() {
        File file = android.os.Environment.getExternalStorageDirectory();
        File aux = new File(file, "hello24.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(aux);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(System.currentTimeMillis()); //Writing current milliseconds
            printWriter.flush();
            printWriter.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "doMagic: " + file);
        Log.d(TAG, "doMagic: " + aux);
    }

    public void readMagic(View view) {
        readFileExternal();
    }

    private void readFileExternal() {
        File file = android.os.Environment.getExternalStorageDirectory();
        File aux = new File(file, "hello.txt");

        try {
            FileInputStream fileInputStream = new FileInputStream(aux);
            Scanner scanner = new Scanner(fileInputStream);
            Log.d(TAG, "readMagic: " + scanner.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}