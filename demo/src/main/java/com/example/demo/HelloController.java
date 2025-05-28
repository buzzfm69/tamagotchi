package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private ImageView tamagotchiBackground;

    @FXML
    private ImageView eggImage;

    @FXML
    private ImageView menuImage;

    @FXML
    private ImageView menuFocusFoodImage;

    @FXML
    private Button startButton;

    @FXML
    private Button buttonLeft;

    @FXML
    private Button buttonMiddle;

    @FXML
    private boolean isMenuActive;

    private Button buttonRight;

    private int clickCount = 0;

    private int imageIndex = 0;

    AnimationHelper animationHelper = new AnimationHelper();

    private final String[] menuFocusImages = {
            "/images/MenuFocusFood.png",
            "/images/MenuFocusLight.png",
            "/images/MenuFocusPlay.png",
            "/images/MenuFocusMedicine.png",
            "/images/MenuFocusBathing.png",
            "/images/MenuFocusWeight.png",
            "/images/MenuFocusPacman.png",
            "/images/MenuFocusAttention.png",
            "/images/MenuNoFocus.png"
    };


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
        Image imageMenu = new Image(getClass().getResource("/images/MenuNoFocus.png").toExternalForm());
        menuImage.setImage(imageMenu);

        Image imageEgg = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
        eggImage.setImage(imageEgg);

        // Bild anzeigen
        menuImage.setVisible(true);
        eggImage.setVisible(true);

        // Start-Button ausblenden
        startButton.setVisible(false);

        isMenuActive = true;
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

    @FXML
    public void handleClickButtonRight(){
        if (isMenuActive) {
            // Bildpfad laden
            String path = menuFocusImages[imageIndex];

            // Bild setzen
            Image image = new Image(getClass().getResource(path).toExternalForm());
            menuImage.setImage(image);

            // Index erhöhen, bei 8 zurück auf 0 setzen
            imageIndex = (imageIndex + 1) % menuFocusImages.length;
        }
    }
}