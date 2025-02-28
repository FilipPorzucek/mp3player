package com.filip.mp3player.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class MainControler {

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private TableView<?> contentTable;

    @FXML
    private MenuItem dirMenuItem;

    @FXML
    private MenuItem fileMenuItem;

    @FXML
    private Menu fileMenuItem1;

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

    }

    private void configureVolume() {
        volumeSlider.addEventFilter(MouseEvent.MOUSE_PRESSED,event->{
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