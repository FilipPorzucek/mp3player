package com.filip.mp3player.controler;


import com.filip.mp3player.mp3.Mp3Parser;
import com.filip.mp3player.mp3.Mp3Song;
import com.filip.mp3player.player.Mp3Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;

public class MainControler {

    @FXML
    private ContentPaneControler contentPaneController;

    @FXML
    private  ControlPaneControler controlPaneController;

    @FXML
    private  MenuPaneControler menuPaneController;

    private Mp3Player player;



    public void initialize(){
        createPlayer();
        configureTableClick();
        configureButtons();
        configureMenu();
    }




    private void configureButtons() {
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        ToggleButton playButton = controlPaneController.getPlayButton();
        Button previousButton = controlPaneController.getPreviousButton();
        Button nextButton = controlPaneController.getNextButton();

        playButton.setOnAction(event -> {
            if(playButton.isSelected()){
                player.play();
            }else {
                player.getMediaPlayer().pause();
            }
        });

        nextButton.setOnAction(event -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex()+1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });

        previousButton.setOnAction(event -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex()-1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });


    }


    private void createPlayer() {
        ObservableList<Mp3Song> items = contentPaneController.getContentTable().getItems();
         player = new Mp3Player(items);

    }

    private void configureTableClick() {
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED,event->{
            if (event.getClickCount()==2){
                int selectedIndex = contentTable.getSelectionModel().getSelectedIndex();
                playSelectedSong(selectedIndex);
            }
        });
    }

    private void playSelectedSong(int selectedIndex) {
        player.loadSong(selectedIndex);
        configureProgressBar();
        configureVolume();
    }

    private void configureVolume() {
        Slider volumeSlider = controlPaneController.getVolumeSlider();
        volumeSlider.valueProperty().unbind();
        volumeSlider.setMax(1.0);
        volumeSlider.valueProperty().bindBidirectional(player.getMediaPlayer().volumeProperty());
    }

    private void configureProgressBar() {
        Slider progresSlider = controlPaneController.getProgresSlider();

        player.getMediaPlayer().setOnReady(()->progresSlider.setMax(player.getLoadedSongLength()));
        player.getMediaPlayer().currentTimeProperty().addListener((arg,oldVal,newVal)->{
            progresSlider.setValue(newVal.toSeconds());
        });

        progresSlider.valueProperty().addListener((observable,oldValue,newValue)->{
            if(progresSlider.isValueChanging()){
                player.getMediaPlayer().seek(Duration.seconds(newValue.doubleValue()));
            }
        });
    }

    private void configureMenu() {
        MenuItem openFile = menuPaneController.getFileMenuItem();
        MenuItem openDir = menuPaneController.getDirMenuItem();
        
        openFile.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3","*mp3"));
            File file = fc.showOpenDialog(new Stage());

            try {
                contentPaneController.getContentTable().getItems().add(Mp3Parser.createMp3Song(file));
            } catch (TagException | IOException e) {
              e.printStackTrace();
            }
        });

        openDir.setOnAction(event -> {
            DirectoryChooser dc = new DirectoryChooser();
            File dir = dc.showDialog(new Stage());

            try {
                contentPaneController.getContentTable().getItems().addAll(Mp3Parser.creteMp3List(dir));
            } catch (TagException | IOException e) {
             e.printStackTrace();
            }
        });


    }
    


    }


