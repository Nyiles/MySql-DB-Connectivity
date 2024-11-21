package com.example.try3;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MyText extends Text {
    public MyText(String str){
        super(str);

        setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,24));
        setFill(Color.GRAY);
    }
    public MyText(String str, int newSize){
        super(str);
        setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, newSize));
        setFill(Color.GRAY);
    }
}
