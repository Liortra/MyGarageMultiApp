package com.example.common;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.common.controllers.GarageController;
import com.example.common.controllers.ScreenTimeManager;
import com.example.common.entities.Garage;

public class CommonActivity extends AppCompatActivity {
    public static int TOOLBAR_COLOR;
    public static String APP_NAME = "";

    private long startTimeStamp = 0;
    private final int ONE_MINUTE = 60;
    private ScreenTimeManager screenTimeManager;

    private Toolbar toolbar;
    private TextView main_LBL_title, main_LBL_isOpen, main_LBL_address, main_LBL_cars, main_LBL_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        init();
        listeners();
    }

    private void init() {
        //Getting data from another activity
        TOOLBAR_COLOR = getIntent().getIntExtra("TOOLBAR_COLOR", Color.WHITE);
        APP_NAME = getIntent().getStringExtra("APP_NAME");
        // here the part that make the app to look different
        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(TOOLBAR_COLOR);
        toolbar.setTitle(APP_NAME);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(TOOLBAR_COLOR);
        }
        // same for both apps
        main_LBL_title = findViewById(R.id.main_LBL_title);
        main_LBL_isOpen = findViewById(R.id.main_LBL_isOpen);
        main_LBL_address = findViewById(R.id.main_LBL_address);
        main_LBL_cars = findViewById(R.id.main_LBL_cars);
        main_LBL_time = findViewById(R.id.main_LBL_time);
        // for count the time of screen
        screenTimeManager = ScreenTimeManager.initHelper(this.getApplicationContext());
    }

    private void listeners() {
        // get the data from DB
        downloadInfoFromData(garage -> {
            main_LBL_title.setText(String.format("Garage Name: %s", garage.getName()));
            String status = garage.isOpen() ? "Open" : "Close";
            main_LBL_isOpen.setText(String.format("Status: %s", status));
            main_LBL_address.setText(String.format("Address: %s", garage.getAddress()));
            for (String car : garage.getCars()) {
                String carTxt = main_LBL_cars.getText().toString();
                carTxt += "\n" + car;
                main_LBL_cars.setText(carTxt);
            }
        });
    }

    public interface CallBack_Data {
        void dataReady(Garage garage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimeStamp = System.currentTimeMillis();
        totalScreenTime();
    }

    @Override
    protected void onPause() {
        super.onPause();
        long duration = (System.currentTimeMillis() - startTimeStamp) / 1000;
        screenTimeManager.saveScreenTime(duration, APP_NAME);
    }

    private void downloadInfoFromData(CallBack_Data callBack) {
        new GarageController().fetchAllGarage(garage -> {
            if (callBack != null)
                callBack.dataReady(garage);
        });
    }

    private void totalScreenTime() {
        ScreenTimeManager.getInstance().getTotalScreenTime(APP_NAME, time -> {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (time >= ONE_MINUTE) {
                    long min = time / ONE_MINUTE;
                    long sec = time % ONE_MINUTE;
                    main_LBL_time.setText(String.format("Total Screen time \n%d Minutes \n%d Seconds", min, sec));
                } else
                    main_LBL_time.setText(String.format("Total Screen time %d Seconds", time));
            });
        });
    }
}
