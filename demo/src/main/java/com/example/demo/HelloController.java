package com.example.demo;

import com.example.demo.Buttons.ButtonHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.net.URL;

public class HelloController {
    // Images
    @FXML
    private ImageView tamagotchiBackground;
    @FXML
    private ImageView currentImage;
    @FXML
    private ImageView menuImage;

    // Buttons
    @FXML
    private Button startButton;
    @FXML
    private Button buttonLeft;
    @FXML
    private Button buttonMiddle;
    @FXML
    private Button buttonRight;

    //Labels
    @FXML
    private Label labelWeight;
    @FXML
    private Label labelAge;

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
    public void initialize() {
        Image imageBackground = new Image(getClass().getResource("/images/TamagotchiUI.png").toExternalForm());
        tamagotchiBackground.setImage(imageBackground);
        currentImage.setVisible(false);
        buttonLeft.setOpacity(0);
        buttonLeft.setMouseTransparent(false);
        buttonMiddle.setOpacity(0);
        buttonMiddle.setMouseTransparent(false);
        buttonRight.setOpacity(0);
        buttonRight.setMouseTransparent(false);

        updateWeightDisplay();
    }

    @FXML
    public void handleStart(){
        // Bild laden
        Image imageMenu = new Image(getClass().getResource("/images/MenuNoFocus.png").toExternalForm());
        menuImage.setImage(imageMenu);

        Image imageEgg = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
        currentImage.setImage(imageEgg);

        // Bild anzeigen
        menuImage.setVisible(true);
        currentImage.setVisible(true);

        // Start-Button ausblenden
        startButton.setVisible(false);

        tamagotchiState.setMenuActive(true);
    }

    public void updateWeightDisplay() {
        labelWeight.setText(tamagotchiState.weight + " kg");
        labelAge.setText(tamagotchiState.age + " yr");
    }

    @FXML
    public void handleClickButtonLeft(){
        if(tamagotchiState.isMenuActive()){
            buttonHandler.handleClickButtonLeftMenuStart(currentImage);
        }
    }

    @FXML
    public void handleClickButtonRight(){
        if (tamagotchiState.isMenuActive() && !tamagotchiState.isWeightMenuActive()){
            buttonHandler.handleClickButtonRightMenu(menuFocusImages, menuImage);
        }
    }

    @FXML
    public void handleClickButtonMiddle(){
        switch (tamagotchiState.imageIndex){
            case 6:
                if (tamagotchiState.isMenuActive()){
                    buttonHandler.handleClickButtonMiddleMenu6(menuImage, currentImage, labelWeight, labelAge);
                } else{
                    buttonHandler.handleButtonMiddleWeightMenu(menuImage, currentImage, labelWeight, labelAge);
                }

            case 2:
                return;
        }
    }
}