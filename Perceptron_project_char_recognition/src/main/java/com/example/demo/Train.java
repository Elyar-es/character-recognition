package com.example.demo;

import java.io.*;

public class Train {

    public static void train(int[][] tData, int y) {

        //read from file

        String wes = "";

        File ww = new File("weights.txt");

        if (!ww.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("weights.txt"));
                for (int i=0; i<5; i++) {
                    for (int j=0; j<5; j++) {

                        writer.write(0 + "@");

                    }
                }

                writer.write(0 + "");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            BufferedReader reader = new BufferedReader(new FileReader("weights.txt"));
            wes = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[][] weights = new double[5][5];
        String[] warr = wes.split("@");

        int t=0;
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {

                weights[i][j] = Double.parseDouble(warr[t]);
                t++;

            }

        }

        double bias = Double.parseDouble(warr[25]);

        //update the weights

        while (true) {

            double teta = 0.5;

            double alfa = 0.99;

            double ans = 0;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {

                    ans += weights[i][j] * tData[i][j];

                }
            }

            ans += bias;

            int fy = 0;

            if (ans > teta) {
                fy = 1;
            } else if (ans < -teta) {
                fy = -1;
            }

            if (fy != y) {

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {

                        weights[i][j] = weights[i][j] + (tData[i][j] * y * alfa);

                    }
                }

                bias = bias + (y * alfa);

            } else {
                break;
            }



        }

        //write to file

        {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("weights.txt"));

                for (int i=0; i<5; i++) {
                    for (int j=0; j<5; j++) {

                        writer.write(weights[i][j] + "@");

                    }
                }

                writer.write(bias + "");
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
