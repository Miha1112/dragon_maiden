package com.dev.jtunao.lotinnc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private String SAVE_TEXT = "Save_score";
    public static final int REQUEST_CODE = 1;
    static String loadScoreResult1,LoadScoreResult2,LoadScoreResult3;

    static int total = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void permission() {
        if((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

        }else{
            System.out.println("cancel permission");
        }
        if((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

        }else{
            System.out.println("cancel permission");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                System.out.println("permission granted");
            }else{
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textView = findViewById(R.id.total_score_main);
        textView.setText(Integer.toString(total));
    }

    private void init(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    public void goToGameScreen(View view){
        Intent intent = new Intent(this,SpinerActivity.class);
        startActivity(intent);

    }
    public  void goToResultScreen(View view){
        Intent intent = new Intent(this,TopResult.class);
        startActivity(intent);
    }

    public void toSetting(View view){
        Intent intent = new Intent(this, setting_activity.class);
        startActivity(intent);
    }

    public void toPrivacy(View view){
        Intent intent = new Intent(this, PrivacyActivity.class);
        startActivity(intent);
    }

}