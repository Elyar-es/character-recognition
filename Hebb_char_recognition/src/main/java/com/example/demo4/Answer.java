package com.example.demo4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Answer {


    public static int answer(int[][] tData) {

        //read weights from file

        String wes = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader("weights.txt"));
            wes = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[][] weights = new int[5][5];
        String[] warr = wes.split("@");

        int t=0;
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {

                weights[i][j] = Integer.parseInt(warr[t]);
                t++;

            }
        }

        int bias = Integer.parseInt(warr[25]);


        //calculate the answer

        int ans = 0;

        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {

                ans += weights[i][j] * tData[i][j];

            }
        }

        ans += bias;

        if (ans >= 0) {
            return (+1);
        } else {
            return (-1);
        }


    }
}
