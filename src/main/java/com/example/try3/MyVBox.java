package com.example.try3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MyVBox extends VBox {
    public MyVBox(){
        setSpacing(5);
        setPadding(new Insets(10));
        setStyle("-fx-background-color:white");
        setAlignment(Pos.CENTER);
    }
}
