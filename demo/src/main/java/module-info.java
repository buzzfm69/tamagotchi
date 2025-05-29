module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
  requires java.desktop;
  requires javafx.media;


  opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Handler;
    opens com.example.demo.Handler to javafx.fxml;
    exports com.example.demo.Menu;
    opens com.example.demo.Menu to javafx.fxml;
  exports com.example.demo.Menu.Game;
  opens com.example.demo.Menu.Game to javafx.fxml;
}