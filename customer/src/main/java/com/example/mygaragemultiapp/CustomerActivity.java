package com.example.mygaragemultiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.example.common.CommonActivity;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, CommonActivity.class).putExtra("TOOLBAR_COLOR", Color.GREEN).putExtra("APP_NAME","CustomerApp"));
        finish();
        super.onCreate(savedInstanceState);
    }
}