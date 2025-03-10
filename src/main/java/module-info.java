module mp3player {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jid3lib;

    exports com.filip.mp3player.main to javafx.graphics;
    opens com.filip.mp3player.controler to javafx.fxml;
    opens com.filip.mp3player.mp3 to javafx.base;

}