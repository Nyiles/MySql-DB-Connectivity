package com.example.try3;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MyGridPane extends GridPane {
    public MyGridPane(){
        setPadding(new Insets(5));
        setStyle("-fx-background-radius:10;-fx-background-color:white");
        setVgap(5);
        setHgap(15);

    }
public void addphone(TextField textField, MyText myText, TextField textField1, MyText myText1,TextField textField2){
        add(textField,0,0);
        add(myText,1,0);
        add(textField1,2,0);
        add(myText1,3,0);
        add(textField2,4,0);
}
public void addFour(Button button, Button button1, Button button2, Button button3){
        add(button,0,0);
        add(button1,1,0);
        add(button2,2,0);
        add(button3,3,0);

}
    public void addTime(TextField textField, MyText myText, TextField textField1){
        add(textField,0,0);
        add(myText,1,0);
        add(textField1,2,0);

}}
