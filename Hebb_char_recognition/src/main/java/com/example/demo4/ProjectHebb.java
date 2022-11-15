package com.example.demo4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ProjectHebb extends Application{


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        GridPane forAll = new GridPane();


        stage.setTitle("X or O ?");

        int[][] inarr = new int[5][5];
        for (int i=0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                inarr[i][j] = -1;
            }
        }


        GridPane layout = new GridPane();
        forAll.setPadding(new Insets(50,20,20,20));
        layout.setVgap(0);
        layout.setHgap(0);



        Button[][] blist = new Button[5][5];
        String[][] db = new String[5][5];
        for (int i=0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                db[i][j] = ".";
            }
        }



        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                blist[i][j] = new Button(" . ");
                GridPane.setConstraints(blist[i][j], i, j);
                int finalI = i;
                int finalJ = j;
                blist[i][j].setOnAction(e -> {
                    if (blist[finalI][finalJ].getText().equals(" . ")) {

                        db[finalI][finalJ] = "#";
                        inarr[finalI][finalJ] = 1;
                        blist[finalI][finalJ].setText("#");

                    } else {

                        db[finalI][finalJ] = ".";
                        inarr[finalI][finalJ] = -1;
                        blist[finalI][finalJ].setText(" . ");

                    }
                });

            }
        }


        Button[] allb = new Button[25];
        int t = 0;

        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                allb[t] = blist[i][j];
                t++;
            }
        }

        AtomicInteger y = new AtomicInteger();

        Menu XorO = new Menu("select if you wanna train");
        ToggleGroup XorOtoggle = new ToggleGroup();

        RadioMenuItem radioX = new RadioMenuItem("X");
        RadioMenuItem radioO = new RadioMenuItem("O");

        radioX.setOnAction(e -> {
            y.set(1);
        });

        radioO.setOnAction(e -> {
            y.set(-1);
        });

        radioX.setToggleGroup(XorOtoggle);
        radioO.setToggleGroup(XorOtoggle);

        XorO.getItems().addAll(radioX, radioO);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(XorO);




        RadioButton radioTrain = new RadioButton("train");
        RadioButton radioAnswer = new RadioButton("answer");

        AtomicReference<String> flag = new AtomicReference<>("");

        radioAnswer.setOnAction(e -> {
            flag.set("answer");
        });

        radioTrain.setOnAction(e -> {
            flag.set("train");

        });

        ToggleGroup trainOrAnswerToggle = new ToggleGroup();

        radioTrain.setToggleGroup(trainOrAnswerToggle);
        radioAnswer.setToggleGroup(trainOrAnswerToggle);


        Button but = new Button("OK");
        Text text = new Text("   _");


        but.setOnAction(e -> {
            if (flag.get().equals("train")) {

                Train.train(inarr, y.get());
                File data = new File("data.txt");
                try{
                    if(!data.exists()){
                        System.out.println("new file created");
                        data.createNewFile();
                }
                PrintWriter out = new PrintWriter(new FileWriter(data, true));
                out.append(y + "\n");
                for (int i=0; i<5; i++) {
                    for (int j = 0; j < 5; j++) {
                        out.append(db[i][j]);
                    }
                    out.append("\n");
                }
                out.close();

                }catch(IOException er){

                }

            } else if (flag.get().equals("answer")) {
                text.setText(Answer.answer(inarr) == 1 ? "   X" : "   O");
            }
        });


        layout.getChildren().addAll(allb);


        forAll.setVgap(40);
        forAll.setHgap(40);

        GridPane radio = new GridPane();



        GridPane.setConstraints(but, 0, 3);
        GridPane.setConstraints(menuBar, 0, 2);
        GridPane.setConstraints(radio, 0, 1);
        GridPane.setConstraints(layout, 0, 0);

        GridPane.setConstraints(radioAnswer, 0, 1);
        GridPane.setConstraints(radioTrain, 0, 0);


        radio.setVgap(7);

        radio.getChildren().addAll(radioAnswer, radioTrain);
        forAll.getChildren().addAll(layout, but, radio, menuBar);

        Color c = Color.rgb(106,99,46);
        GridPane all = new GridPane();
        Rectangle rec = new Rectangle();
        rec.setHeight(300);
        rec.setWidth(5);
        rec.setFill(c);
        text.setStyle("-fx-font-size: 80px;");

        GridPane.setConstraints(forAll, 0, 0);
        GridPane.setConstraints(rec, 1, 0);
        GridPane.setConstraints(text, 2, 0);

        all.setVgap(40);
        all.setHgap(40);
        all.getChildren().addAll(forAll, text, rec);


        Scene scene = new Scene(all, 500, 500);

        stage.setScene(scene);
        stage.show();
    }
}