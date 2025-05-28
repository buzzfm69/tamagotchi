package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private Button startButton;

    @FXML
    private ImageView tamagotchiBackground;

    @FXML
    private ImageView eggImage;

    @FXML
    private Button buttonLeft;

    @FXML
    private Button buttonMiddle;

    @FXML
    private Button buttonRight;

    @FXML
    private ImageView menuImage;

    private int clickCount = 0;

    AnimationHelper animationHelper = new AnimationHelper();


    @FXML
    public void intialize() {
        Image imageBackground = new Image(getClass().getResource("/images/TamagotchiUI.png").toExternalForm());

        tamagotchiBackground.setImage(imageBackground);
        eggImage.setVisible(false);

        buttonLeft.setOpacity(0);
        buttonLeft.setMouseTransparent(false);

        buttonMiddle.setOpacity(0);
        buttonMiddle.setMouseTransparent(false);

        buttonRight.setOpacity(0);
        buttonRight.setMouseTransparent(false);
    }

    @FXML
    public void handleStart(){
        // Bild laden
        Image imageEgg = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
        eggImage.setImage(imageEgg);

        Image imageMenu = new Image(getClass().getResource("/images/Menu.png").toExternalForm());
        //menuImage.setVisible(true);

        // Bild anzeigen
        eggImage.setVisible(true);

        // Start-Button ausblenden
        startButton.setVisible(false);
    }

    @FXML
    public void handleClickButtonLeft(){
        clickCount++;

        if (clickCount ==3){
            animationHelper.animateStartSequence(eggImage); //Ei Sequenz starten

            // 2. Nach der Animation Bild ändern
            // Verzögerung hinzufügen, damit das neue Bild nicht sofort kommt
            new Thread(() -> {
                try {
                    Thread.sleep(1000); // Warten, bis die Animation sichtbar ist
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // UI-Update zurück im JavaFX-Thread
                javafx.application.Platform.runLater(() -> {
                    Image newImage = new Image(getClass().getResource("/images/Baby.png").toExternalForm());
                    eggImage.setImage(newImage);
                });
            }).start();
        }

    }

}