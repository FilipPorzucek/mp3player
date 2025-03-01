module mp3player {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports com.filip.mp3player.main to javafx.graphics;
    opens com.filip.mp3player.main to javafx.fxml;
    exports com.filip.mp3player.controler to javafx.graphics;
    opens com.filip.mp3player.controler to javafx.fxml;

}