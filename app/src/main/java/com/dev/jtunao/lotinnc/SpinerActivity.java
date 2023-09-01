package com.dev.jtunao.lotinnc;

import static com.dev.jtunao.lotinnc.MainActivity.total;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SpinerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiner);
        init();
    }
    EditText editText;
    SharedPreferences sharedPreferences;
    private String SAVE_TEXT = "Save_score";
    private TextView spin_result_1,spin_result_2,spin_result_3,total_score,baraban_count;
    private ImageView baraban_1,baraban_2,baraban_3,backBtn;
    private Button btn;
    private Integer[] imgArray = {R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4,R.drawable.img_5,R.drawable.img_6};
    private Random random, random2,random3,random4;
    private  Random res1,re2,res3;
    private int old_deegre, deegre = 0;
    private int old_deegre2, deegre2 = 0;
    private int old_deegre3, deegre3 = 0;
    private int active_baraban = 3;
    private int spinCost = 30;
    private int addScore, addScore1,addScore2 = 0;
    private static final float FACTOR =4.86f;
    private Boolean isAuto = false;

    private int[] numbs = {
            32,15,19,4,21,2,25,17,34,6,27,13,36,11,30,8,23,10,5,24,16,33,1,20,14,13,9,22,18,29,7,28,12,35,3,26,0
    };
    private String[] numbs1 = {
            "32","15","19","4","21","2","25","17","34","6","27","13","36","11","30","8","23","10","5","24"
            ,"16","33","1","20","14","13","9","22","18","29","7","28","12","35","3","26","0"
    };


    public void onClickStart(View view){
        spin();
    }

    private void spin(){
        old_deegre = deegre % 360;
        old_deegre2 = deegre2 % 360;
        old_deegre3 = deegre3 % 360;
        deegre = random.nextInt(500);
        deegre2 = random2.nextInt(500);
        deegre3 = random3.nextInt(500);

        switch (active_baraban){
            case 1:{
                int index = 0;
                for (int i =0; i <deegre;i++){
                    baraban_1.setImageResource(imgArray[index]);
                    index++;
                    if(index >= imgArray.length){
                        index = 0;
                    }
                }
                int result = random.nextInt(50);
                total = total + result;
                total_score.setText( Integer.toString(total));
            }break;
            case 2:{
                int index = 0;
                int index1 = 0;
                for (int i =0; i <deegre;i++){
                    baraban_1.setImageResource(imgArray[index]);
                    index++;
                    if(index >= imgArray.length){
                        index = 0;
                    }
                }
                for (int i =0; i <deegre2;i++){
                    baraban_1.setImageResource(imgArray[index1]);
                    index1++;
                    if(index1 >= imgArray.length){
                        index1 = 0;
                    }
                }
                int result = random.nextInt(100);
                total = total + result;
                total_score.setText( Integer.toString(total));
            }break;
            case 3:{
                int index = 0;
                int index1 = 0;
                int index2 = 0;
                for (int i =0; i <deegre;i++){
                    baraban_1.setImageResource(imgArray[index]);
                    index++;
                    if(index >= imgArray.length){
                        index = 0;
                    }
                }
                for (int i =0; i <deegre2;i++){
                    baraban_2.setImageResource(imgArray[index1]);
                    index1++;
                    if(index1 >= imgArray.length){
                        index1 = 0;
                    }
                }
                for (int i =0; i <deegre3;i++){
                    baraban_3.setImageResource(imgArray[index2]);
                    index2++;
                    if(index2 >= imgArray.length){
                        index2 = 0;
                    }
                }
                int result = random.nextInt(250);
                total = total + result;
                total_score.setText( Integer.toString(total));
            }break;

        }
        total-=spinCost;
        if(total - spinCost <=0){
            total = 100;
        }
        if(isAuto){
            timer();
        }
        total_score.setText(Integer.toString(total));
    }
    public void addBaraban(View view){
        if(active_baraban<3){
            active_baraban++;
            spinCost+= 10;
        }
        RelativeLayout ruller_l_2 = findViewById(R.id.ruller_2_l);
        RelativeLayout ruller_l_3 = findViewById(R.id.ruller_3_l);

        switch (active_baraban){
            case 1:{
                ruller_l_2.setVisibility(View.INVISIBLE);
                ruller_l_3.setVisibility(View.INVISIBLE);
                baraban_3.setVisibility(View.INVISIBLE);
                baraban_2.setVisibility(View.INVISIBLE);
            }break;
            case 2:{
                ruller_l_2.setVisibility(View.VISIBLE);
                ruller_l_3.setVisibility(View.INVISIBLE);
                baraban_3.setVisibility(View.INVISIBLE);
                baraban_2.setVisibility(View.VISIBLE);
            }break;
            case 3:{
                ruller_l_2.setVisibility(View.VISIBLE);
                ruller_l_3.setVisibility(View.VISIBLE);
                baraban_3.setVisibility(View.VISIBLE);
                baraban_2.setVisibility(View.VISIBLE);
            }break;
        }

        baraban_count.setText("Now active " + active_baraban + " roulette");
    }
    public void removeBaraban(View view){
        if(active_baraban>1){
            active_baraban--;
            spinCost-= 10;
        }
        RelativeLayout ruller_l_2 = findViewById(R.id.ruller_2_l);
        RelativeLayout ruller_l_3 = findViewById(R.id.ruller_3_l);

        switch (active_baraban){
            case 1:{
                ruller_l_2.setVisibility(View.INVISIBLE);
                ruller_l_3.setVisibility(View.INVISIBLE);
                baraban_3.setVisibility(View.INVISIBLE);
                baraban_2.setVisibility(View.INVISIBLE);
            }break;
            case 2:{
                ruller_l_2.setVisibility(View.VISIBLE);
                ruller_l_3.setVisibility(View.INVISIBLE);
                baraban_3.setVisibility(View.INVISIBLE);
                baraban_2.setVisibility(View.VISIBLE);
            }break;
            case 3:{
                ruller_l_2.setVisibility(View.VISIBLE);
                ruller_l_3.setVisibility(View.VISIBLE);
                baraban_3.setVisibility(View.VISIBLE);
                baraban_2.setVisibility(View.VISIBLE);
            }break;
        }
        baraban_count.setText("Now active " + active_baraban + " roulette");
    }

    private void init(){

        spin_result_1 = findViewById(R.id.rezult_first);
        spin_result_2 = findViewById(R.id.rezult_second);
        spin_result_3 = findViewById(R.id.rezult_third);
        total_score = findViewById(R.id.total_score);
        baraban_count = findViewById(R.id.active_baraban_count);
        total_score.setText(Integer.toString(total));

        baraban_1 = findViewById(R.id.ruller_1);
        baraban_2 = findViewById(R.id.ruller_2);
        baraban_3 = findViewById(R.id.ruller_3);
        backBtn = findViewById(R.id.bakc_to_menu);



        random = new Random();

        random2 = new Random();

        random3 = new Random();

        random4 = new Random();
    }


    public void backToMenu_sp(View view) throws IOException {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        PlayerResult playerResult = new PlayerResult(total);
        playerResult.saveResult(this,total);
    }

    private void saveText(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(SAVE_TEXT, Integer.toString(total));
        ed.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    private void timer(){
        CountDownTimer timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                spin();
                if(isAuto){
                    timer();
                }
            }
        };
        timer.start();
    }
    public void automaticSpin(View view){
        System.out.println("Try start auto spin");
        isAuto = !isAuto;
        if(isAuto){
            timer();
        }
    }
}