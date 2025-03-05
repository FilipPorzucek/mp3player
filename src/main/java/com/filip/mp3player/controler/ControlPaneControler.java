package com.filip.mp3player.controler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class ControlPaneControler {

    @FXML
    private Button nextButton;

    @FXML
    private ToggleButton playButton;

    @FXML
    private Button previousButton;

    @FXML
    private Slider progresSlider;

    @FXML
    private Slider volumeSlider;

    public void initialize(){
        configureButtons();
        configureVolume();
        configureSlider();
    }

    private void configureSlider() {
        volumeSlider.valueProperty().addListener((observable,oldValue,newValue)->
                System.out.println("Zmiana głóśności"+newValue.doubleValue()));
    }

    private void configureVolume() {
        volumeSlider.addEventFilter(MouseEvent.MOUSE_PRESSED, event->{
            System.out.println("Wcisnieto przycisk na suwaku głośności");
        });
    }

    private void configureButtons() {
        previousButton.setOnAction(event-> System.out.println("Poprzednia piosenka"));
        nextButton.setOnAction((x-> System.out.println("Następna piosenka")));
        playButton.setOnAction(event->{
            if(playButton.isSelected()){
                System.out.println("Play");
            }else System.out.println("Stop");
        });
    }

}
