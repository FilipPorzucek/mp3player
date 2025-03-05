package com.filip.mp3player.controler;


import javafx.fxml.FXML;

public class MainControler {

    @FXML
    private ContentPaneControler contentPaneController;

    @FXML
    private  ControlPaneControler controlPaneController;

    @FXML
    private  MenuPaneControler menuPaneController;



    public void initialize(){
        System.out.println("Main controller created");
        System.out.println(contentPaneController);
        System.out.println(controlPaneController);
        System.out.println(menuPaneController);

    }



}