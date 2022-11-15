package com.example.demo4;

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


        //update the weights

        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {

                weights[i][j] = weights[i][j] + (tData[i][j] * y);

            }
        }

        bias = bias + y;

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
