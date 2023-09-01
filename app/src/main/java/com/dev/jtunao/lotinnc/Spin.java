package com.dev.jtunao.lotinnc;

public class Spin {
    boolean isAuto = false;
    Integer score,active_baraban, timer,spinCost = 0;
    Integer add_score = 100;

    public void addBaraban(){
        if(active_baraban<3){
            active_baraban++;
        }
    }
    public void removeBaraban(){
        if(active_baraban>1){
            active_baraban--;
        }
    }



}
