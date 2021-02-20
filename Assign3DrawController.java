/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Assign3Draw;

/**
 *
 * @author Austin Emert
 */
import javafx.event.ActionEvent;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

public class Assign3DrawController {
    private enum Mode {LINE, RECT, CIRC};
    private Mode mode = Mode.LINE;
    private Shape currentShape;
    private double X1, Y1, X2, Y2, radius, length, width;
    private double worldBoundX = 800, worldBoundY = 800;
    private String[] shadingString = {"Full","Light","Empty"};
    private String shadeChoice = shadingString[0];
    private ObservableList<String> shadingList = FXCollections.observableArrayList(shadingString);
    private Mode shadingChoice = Mode.LINE;
    
    @FXML
    private TextField firstXfield;

    @FXML
    private TextField firstYfield;

    @FXML
    private TextField secondxField;

    @FXML
    private TextField secondYfield;

    @FXML
    private Label firstX;

    @FXML
    private Label firstY;

    @FXML
    private Label secondX;

    @FXML
    private Label secondY;

    @FXML
    private Label shadingText;

    @FXML
    private Label chooseShape;

    @FXML
    private RadioButton lineButton;

    @FXML
    private ToggleGroup Group1;

    @FXML
    private RadioButton circleButton;

    @FXML
    private RadioButton RectangleButton;

    @FXML
    private ColorPicker colorPick;

    @FXML
    private ComboBox<String> shadingPick;

    @FXML
    private Button drawButton;

    @FXML
    private TextField errorMessage;
    
    @FXML
    private Pane drawPane;

    @FXML
    void circlePick(ActionEvent event) {
        if(circleButton.isArmed() == true){
            secondY.setVisible(false);
            secondYfield.setVisible(false);
            secondX.setText("Radius");
        }
        mode = Mode.CIRC;
    }

    @FXML
    void drawShape(ActionEvent event) {
        drawPane.getChildren().clear();
        errorMessage.setText("");
        if(mode == Mode.RECT){
            try{
                X1 = Double.parseDouble(firstXfield.getText());
                Y1 = Double.parseDouble(firstYfield.getText());
                length = Double.parseDouble(secondxField.getText());
                width = Double.parseDouble(secondYfield.getText());
            } catch (NumberFormatException e) {
                errorMessage.setText("Fill in all text fields PLEASE");
            }
            if((length + X1) > worldBoundX || (length + X1) < 0)
                errorMessage.setText("Shape out of bounds");
            else if((width + Y1) > worldBoundY || (width + Y1) < 0)
                errorMessage.setText("Shape out of bounds");
            currentShape = new Rectangle(X1,Y1,length,width);
            currentShape.setFill(colorPick.getValue());
            currentShape.setStroke(colorPick.getValue());
            switch(shadeChoice){
                case "Full" :
                    currentShape.setOpacity(1.0);
                    break;
                case "Light" :
                    currentShape.setOpacity(0.5);
                    break;
                case "Empty" :
                    currentShape.setOpacity(0.05);
                    break;
            }
            if("Shape out of bounds".equals(errorMessage.getText())){
                //do nothing
            } else{
                drawPane.getChildren().add(currentShape);
            }
        }
        else if(mode == Mode.CIRC){
            try {
                X1 = Double.parseDouble(firstXfield.getText());
                Y1 = Double.parseDouble(firstYfield.getText());
                radius = Double.parseDouble(secondxField.getText());
            } catch (NumberFormatException e) {
                errorMessage.setText("Fill in all text fields PLEASE");
            }
            if((radius + X1) > worldBoundX || (X1 - radius) < 0)
                errorMessage.setText("Shape out of bounds");
            else if((radius + Y1) > worldBoundY || (Y1 - radius) < 0)
                errorMessage.setText("Shape out of bounds");
            currentShape = new Circle(X1,Y1,radius);
            currentShape.setFill(colorPick.getValue());
            currentShape.setStroke(colorPick.getValue());
            switch(shadeChoice){
                case "Full" :
                    currentShape.setOpacity(1.0);
                    break;
                case "Light" :
                    currentShape.setOpacity(0.5);
                    break;
                case "Empty" :
                    currentShape.setOpacity(0.01);
                    break;
            }
            if("Shape out of bounds".equals(errorMessage.getText())){
                //do nothing
            } else{
                drawPane.getChildren().add(currentShape);
            }
        }
        else if(mode == Mode.LINE){
            try {
                X1 = Double.parseDouble(firstXfield.getText());
                Y1 = Double.parseDouble(firstYfield.getText());
                X2 = Double.parseDouble(secondxField.getText());
                Y2 = Double.parseDouble(secondYfield.getText());
            } catch (NumberFormatException e) {
                errorMessage.setText("Fill in all text fields PLEASE");
            }
            if((X1 + X2) > worldBoundX || X1 < 0 || X2 < 0)
                errorMessage.setText("Shape out of bounds");
            else if((Y1 + Y2) > worldBoundY || Y1 < 0 || Y2 < 0)
                errorMessage.setText("Shape out of bounds");
            currentShape = new Line(X1, Y1, X2, Y2);
            currentShape.setFill(colorPick.getValue());
            currentShape.setStroke(colorPick.getValue());
            switch(shadeChoice){
                case "Full" :
                    currentShape.setOpacity(1.0);
                    break;
                case "Light" :
                    currentShape.setOpacity(0.5);
                    break;
                case "Empty" :
                    currentShape.setOpacity(0.01);
                    break;
            }
            if("Shape out of bounds".equals(errorMessage.getText())){
                //do nothing
            } else{
                drawPane.getChildren().add(currentShape);
            }
        }
    }

    @FXML
    void linePick(ActionEvent event) {
        mode = Mode.LINE;
        secondX.setText("Second Point x");
        secondY.setText("Second Point y");
        secondY.setVisible(true);
        secondYfield.setVisible(true);
    }

    //Will not compile without this function
    @FXML
    void pickColor(ActionEvent event) {

    }

    @FXML
    void rectPick(ActionEvent event) {
        if(RectangleButton.isArmed() == true) {
            secondY.setText("Width");
            secondX.setText("Length");
            secondYfield.setVisible(true);
            secondY.setVisible(true);
        }
        mode = Mode.RECT;
    }
    
    //Will not compile without this function
    @FXML
    void shadePicker(ActionEvent event) {
        
    }
    
    public void initialize(){
        shadingPick.setItems(shadingList);
        
        shadingPick.valueProperty().addListener( new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue){
                shadeChoice = newValue;
            }
        }
        );
    }
}