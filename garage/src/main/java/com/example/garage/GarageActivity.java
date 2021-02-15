package com.example.garage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.example.common.CommonActivity;

public class GarageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, CommonActivity.class).putExtra("TOOLBAR_COLOR", Color.BLUE).putExtra("APP_NAME","GarageApp"));
        finish();
        super.onCreate(savedInstanceState);
    }
}