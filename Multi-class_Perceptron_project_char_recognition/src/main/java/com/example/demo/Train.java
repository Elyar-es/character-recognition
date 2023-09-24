package com.example.demo;

import java.io.*;

public class Train {

    public static void train(int[][] tData, int y) {

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

        //update the weights

        while (true) {

            double teta = 0.5;

            double alfa = 0.99;

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



            int fx = 0;

            if (ans[0] > teta) {
                fx = 1;
            } else if (ans[0] < -teta) {
                fx = -1;
            }

            int fo = 0;

            if (ans[1] > teta) {
                fo = 1;
            } else if (ans[1] < -teta) {
                fo = -1;
            }

            if (fx != y) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        weights[0][i][j] = weights[0][i][j] + (tData[i][j] * y * alfa);
                    }
                }
                bias[0] = bias[0] + (y * alfa);
            } else {
                break;
            }

            if (fo != (y * -1)) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        weights[1][i][j] = weights[1][i][j] + (tData[i][j] * (y * -1) * alfa);
                    }
                }
                bias[1] = bias[1] + ((y * -1) * alfa);
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

                        writer.write(weights[0][i][j] + "@");

                    }
                }
                writer.write(bias[0] + "");

                writer.newLine();

                for (int i=0; i<5; i++) {
                    for (int j=0; j<5; j++) {

                        writer.write(weights[1][i][j] + "@");

                    }
                }
                writer.write(bias[1] + "");

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
