module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Buttons;
    opens com.example.demo.Buttons to javafx.fxml;
    exports com.example.demo.Menu;
    opens com.example.demo.Menu to javafx.fxml;
}