package com.dev.jtunao.lotinnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class TopResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_top_result);
        readResults();
    }
    public void backToMenu_sc(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void initResult(){
        TextView res1 = findViewById(R.id.result1);
        TextView res2 = findViewById(R.id.result2);
        TextView res3 = findViewById(R.id.result3);
        Deque<PlayerResult> topResults = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int score = Integer.parseInt(parts[0]);
                topResults.add(new PlayerResult(score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (topResults.size()>3){
            topResults.pollLast();
        }
        switch (topResults.size()){
            case 1:{
                res1.setText(topResults.getFirst().getScore());
            }break;
            case 2:{
                res1.setText(topResults.getFirst().getScore());
                res2.setText(topResults.getFirst().getScore());
            }break;
            case 3:{
                res1.setText(topResults.getFirst().getScore());
                res2.setText(topResults.getFirst().getScore());
                res3.setText(topResults.getFirst().getScore());
            }break;
            default:
                System.out.println(topResults);
        }
    }
    public void readResults() {
        StringBuilder result = new StringBuilder();
        TextView res1 = findViewById(R.id.result1);
        TextView res2 = findViewById(R.id.result2);
        TextView res3 = findViewById(R.id.result3);
        Deque<PlayerResult> topResults = new LinkedList<>();

        try {
            File file = new File(this.getExternalFilesDir(null), "results.txt");

            if (!file.exists()) {
                //Log.d("FileRead", "File not found")
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int score = Integer.parseInt(parts[1]);
                topResults.add(new PlayerResult(score));
            }
            reader.close();
            while (topResults.size()>3){
                topResults.removeLast();
            }
            switch (topResults.size()){
                case 1:{
                    res1.setText(Integer.toString(topResults.pollFirst().getScore()));
                }break;
                case 2:{
                    res1.setText(Integer.toString(topResults.pollFirst().getScore()));
                    res2.setText(Integer.toString(topResults.pollFirst().getScore()));
                }break;
                case 3:{
                    res1.setText(Integer.toString(topResults.pollFirst().getScore()));
                    res2.setText(Integer.toString(topResults.pollFirst().getScore()));
                    res3.setText(Integer.toString(topResults.pollFirst().getScore()));
                }break;
                default:
                    System.out.println("default switch case "+topResults);
            }
         //   Log.d("FileRead", "Read successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}