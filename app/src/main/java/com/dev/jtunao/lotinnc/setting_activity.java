package com.dev.jtunao.lotinnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class setting_activity extends AppCompatActivity {


    private ToggleButton aSwitch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);
        aSwitch = findViewById(R.id.toggleButton);
        init();
    }

    public void setSnd(View view){
        SettingManager settingManager = new SettingManager(getApplicationContext());
        ImageButton btn = findViewById(R.id.soundBtn);
        boolean isSoundEnabled = settingManager.isSoundEnabled();
        if (isSoundEnabled){
            settingManager.setSoundEnabled(false);
            btn.setImageResource(R.drawable.off);
        }else {
            settingManager.setSoundEnabled(true);
            btn.setImageResource(R.drawable.off);
        }

    }
    private void init(){
        SettingManager settingManager = new SettingManager(getApplicationContext());
        try {
            ImageButton btn = findViewById(R.id.soundBtn);
            boolean isSoundEnabled = settingManager.isSoundEnabled();
            if (isSoundEnabled) {
                btn.setImageResource(R.drawable.off);
            } else {
                btn.setImageResource(R.drawable.off);
            }
        }catch (Exception e){
            System.out.println("try load sound setting, error");

        }

    }


    public void share(View view){
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", "text");
        startActivity(Intent.createChooser(intent, "Share"));
    }
    public void backToMenu_s(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}