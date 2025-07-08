package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.demo.TamagotchiState;
import com.example.demo.SaveManager;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tamagotchu-gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // Zustand laden
        TamagotchiState loadedState = SaveManager.loadState();

        // Controller holen
        HelloController controller = fxmlLoader.getController();
        controller.setState(loadedState); // Übergib geladenen Zustand

        // Beim Beenden speichern
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SaveManager.saveState(controller.getState());  // <- hier mit aktuellem Zustand
            System.out.println("Tamagotchi gespeichert!");
        }));

        stage.setTitle("Tamagotchi");

        stage.setWidth(400);
        stage.setHeight(450);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}