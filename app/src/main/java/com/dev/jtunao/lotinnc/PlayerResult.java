package com.dev.jtunao.lotinnc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlayerResult {
    private String playerName = "name";
    private int score;

    public PlayerResult(int score){
        this.score = score;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void saveResult(Context context,int score) {
        try {
            File file = new File(context.getExternalFilesDir(null), "results.txt");

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("FileSave" + " File created");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(playerName +  "," + score);
            writer.newLine();
            writer.close();
            System.out.println("FileSave" + " File created");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FileSave" + " error");
        }
    }
    public void getResult(){
        try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))){
            String line;
            while ((line = reader.readLine())!= null){
                String[] parts = line.split(",");
                int score = Integer.parseInt(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
