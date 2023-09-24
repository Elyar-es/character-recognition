package com.example.demo;

import java.io.*;

public class Answer {


    public static int answer(int[][] tData) {

        //read weights from file
//        String wes = "";
//
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("weights.txt"));
//            wes = reader.readLine();
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        double[][] weights = new double[5][5];
//        String[] warr = wes.split("@");
//
//        int t=0;
//        for (int i=0; i<5; i++) {
//            for (int j=0; j<5; j++) {
//
//                weights[i][j] = Double.parseDouble(warr[t]);
//                t++;
//
//            }
//        }
//
//        double bias = Double.parseDouble(warr[25]);

        int z = 2; // number of classes

        //read from file

        String[] wes = new String[z];
        for (int i = 0; i < z; i++) {
            wes[i] = "";
        }

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

                writer.newLine();

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
            for (int i = 0; i < z; i++) {
                wes[i] = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        double[][][] weights = new double[z][5][5];

        String[][] warr = new String[2][];

        for (int i = 0; i < z; i++) {
            warr[i] = wes[i].split("@");
        }

        int t=0;

        for (int r=0; r<z; r++) {
            for (int i=0; i<5; i++) {
                for (int j=0; j<5; j++) {

                    weights[r][i][j] = Double.parseDouble(warr[r][t]);
                    t++;

                }

            }
            t=0;
        }


        double[] bias = new double[z];

        for (int r=0; r<z; r++) {
            bias[r] = Double.parseDouble(warr[r][25]);
        }


        //calculate the answer

        double[] ans = new double[z];
        for (int i = 0; i < z; i++) {
            ans[i] = 0;
        }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                ans[0] += weights[0][i][j] * tData[i][j];
                ans[1] += weights[1][i][j] * tData[i][j];


            }
        }

        ans[0] += bias[0];
        ans[1] += bias[1];


        System.out.println("X: " + ans[0] + "   O: " + ans[1]);

        if (ans[0] > ans[1]) {
            return +1;
        } else {
            return -1;
        }

    }
}
