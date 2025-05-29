package com.example.demo;

import com.example.demo.Buttons.ButtonHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController {
    // Images
    @FXML
    private ImageView tamagotchiBackground;
    @FXML
    private ImageView eggImage;
    @FXML
    private ImageView menuImage;
    @FXML
    private ImageView menuFocusFoodImage;

    // Buttons
    @FXML
    private Button startButton;
    @FXML
    private Button buttonLeft;
    @FXML
    private Button buttonMiddle;
    @FXML
    private Button buttonRight;

    private final TamagotchiState tamagotchiState = new TamagotchiState();
    private final ButtonHandler buttonHandler = new ButtonHandler(this.tamagotchiState);


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

        tamagotchiState.isMenuActive = true;
    }

    @FXML
    public void handleClickButtonLeft(){
        buttonHandler.handleClickButtonLeftMenu(eggImage);
    }

    @FXML
    public void handleClickButtonRight(){
        buttonHandler.handleClickButtonRightMenu(menuFocusImages, menuImage);
    }
}