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
    @FXML
    private Label labelSleep;
    @FXML
    private Label labelHungry;
    @FXML
    private Label labelClean;
    @FXML
    private Label labelHealth;
    @FXML
    private Label labelHappiness;


    private final AnimationHelper animationHelper = new AnimationHelper();
    private final TamagotchiState tamagotchiState = new TamagotchiState();
    private final ButtonHandler buttonHandler = new ButtonHandler(this.tamagotchiState, this.animationHelper);

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

    private final String[] menu2switchImages = {
            "/images/0_5Heart.png",
            "/images/1Heart.png",
            "/images/1_5Heart",
            "/images/2Heart.png",
            "/images/2_5Heart.png",
            "/images/3Heart.png"
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

        tamagotchiState.startStateTimer(labelHungry);
        updateWeightDisplay();
    }

    @FXML
    public void handleStart(){
        // Bild laden
        Image imageMenu = new Image(getClass().getResource("/images/MenuNoFocus.png").toExternalForm());
        menuImage.setImage(imageMenu);

        //tamagotchiState.getCurrentStage();
        //buttonHandler.changeStateAndImage(currentImage);

        Image imageEgg = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
        currentImage.setImage(imageEgg);

        // Bild anzeigen
        menuImage.setVisible(true);
        currentImage.setVisible(true);

        // Start-Button ausblenden
        startButton.setVisible(false);
        tamagotchiState.setStartButtonActive(false);

        tamagotchiState.setMenuActive(true);

        tamagotchiState.setEggActive(true);
    }

    public void updateWeightDisplay() {
        labelWeight.setText(tamagotchiState.weight + " kg");
        labelAge.setText(tamagotchiState.age + " yr");
    }

    @FXML
    public void handleClickButtonLeft(){
        // aktiviere Tamagotchi; 3x klicken
        if(tamagotchiState.isMenuActive() && tamagotchiState.isEggActive()){
            buttonHandler.buttonLeftActivateTamagotchi(currentImage);
        }
    }

    @FXML
    public void handleClickButtonRight(){
        // switche Menüs im Hauptmenü
        if (tamagotchiState.isMenuActive() && !tamagotchiState.isEggActive()){
            buttonHandler.buttonRightSwitchMenu(menuFocusImages, menuImage);
        }
        // switchen im Menü 6
        if (tamagotchiState.isWeightMenuActive()){
            buttonHandler.buttonRightSwitchSubMenu6(menuImage, labelWeight, labelAge, labelHungry, labelClean, labelHealth, labelHappiness);
        }
    }

    // aktiviere & verlassen Menüs
    @FXML
    public void handleClickButtonMiddle(){
        switch (tamagotchiState.imageIndex){
            //Menü
            case 2:
                if (tamagotchiState.isMenuActive()){
                    buttonHandler.buttonMiddleActivateMenu2(menuImage, currentImage, labelSleep);
                } else {
                    buttonHandler.buttonMiddleLeaveMenu2(menuImage, currentImage, labelSleep);
                }
                break;

            case 6:
                if (tamagotchiState.isMenuActive()){
                    buttonHandler.buttonMiddleActivateMenu6(menuImage, currentImage, labelWeight, labelAge);
                } else{
                    buttonHandler.buttonMiddleLeaveMenu6(menuImage, currentImage, labelWeight, labelAge, labelHungry, labelClean, labelHealth, labelHappiness);
                }
                break;
        }
    }
}