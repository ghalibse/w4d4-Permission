package com.example.simplepermission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doMagic(View view) {
        writeTimeExternal();
    }

    public void writeTimeExternal() {
        File file = android.os.Environment.getExternalStorageDirectory();

        File aux = new File(file, "hello.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(aux);
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.println(System.currentTimeMillis());
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

    public void readFileExternal() {
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
