package com.example.demo;

import com.example.demo.Handler.AnimationHelper;
import com.example.demo.Handler.ButtonHandler;
import com.example.demo.Handler.StateHandler;
import com.example.demo.Menu.Game.TetrisPane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import com.example.demo.SaveManager;

public class HelloController {
    // Images
    @FXML
    private ImageView tamagotchiBackground;
    @FXML
    private ImageView currentImage;
    @FXML
    private ImageView menuImage;
    @FXML
    private ImageView meat;
    @FXML
    private ImageView cherry;
    @FXML
    private ImageView bred;
    @FXML
    private ImageView selector;
    @FXML
    private ImageView foodBeingEaten;

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

    //Areas
    @FXML
    private AnchorPane gamePane;



    private final AnimationHelper animationHelper = new AnimationHelper();
    private final TamagotchiState tamagotchiState = new TamagotchiState();
    private final StateHandler stateHandler = new StateHandler(this.tamagotchiState);
    private final TetrisPane tetrisPane = new TetrisPane(tamagotchiState);
    private final ButtonHandler buttonHandler = new ButtonHandler(this.tamagotchiState, this.animationHelper, this.stateHandler, this.tetrisPane);

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

        if (!tamagotchiState.isEggActive()){
            stateHandler.startStateTimer(menuImage, currentImage, labelWeight, labelAge);
        }
        stateHandler.updateLifeStage();
        stateHandler.updateWeightDisplay(labelWeight, labelAge);
        tamagotchiState.setStartButtonActive(true);
    }

    @FXML
    public void handleStart(){
        // Bild laden
        Image imageMenu = new Image(getClass().getResource("/images/MenuNoFocus.png").toExternalForm());
        menuImage.setImage(imageMenu);

        if (!tamagotchiState.isEggActive()){
            Image imageEgg = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
            currentImage.setImage(imageEgg);
            tamagotchiState.setEggActive(true);
            tamagotchiState.setWeight(1);
            tamagotchiState.setAge(1);
            tamagotchiState.setMenuActive(true);
            tamagotchiState.setStartButtonActive(false);
        }
//        Image imageEgg = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
//        currentImage.setImage(imageEgg);

        // Bild anzeigen
        menuImage.setVisible(true);
        currentImage.setVisible(true);

        // Start-Button ausblenden
        startButton.setVisible(false);
        tamagotchiState.setStartButtonActive(false);

        tamagotchiState.setMenuActive(true);
//        tamagotchiState.setEggActive(true);
    }

    @FXML
    public void handleClickButtonLeft(){
        // aktiviere Tamagotchi; 3x klicken
        if(tamagotchiState.isMenuActive() && tamagotchiState.isEggActive()){
            buttonHandler.buttonLeftActivateTamagotchi(currentImage);
        }
        if (tamagotchiState.isGameMenuActive()) {
            if (tetrisPane != null) {
                tetrisPane.moveLeft();
            }
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
        // Game-Steuerung Tetris
        if (tamagotchiState.isGameMenuActive()) {
            if (tetrisPane != null) {
                tetrisPane.moveRight();
            }
        }
        if (tamagotchiState.isEatingMenuActive()) {
            buttonHandler.buttonRightSwitchSubMenu1(selector);
        }
    }

    @FXML
    public void handleClickButtonMiddle(){
        //start button klicken
        if (startButton.isVisible()){
            handleStart();
        } else {
        switch (tamagotchiState.imageIndex){
            //Menü
            case 1:
                if (tamagotchiState.isMenuActive()){
                    buttonHandler.buttonMiddleActivateMenu1(currentImage, meat, cherry, bred, selector, foodBeingEaten);
                } else if (tamagotchiState.isEatingMenuActive() && tamagotchiState.isEating()) {
                    buttonHandler.buttonMiddleLeaveMenu1(currentImage, meat, cherry, bred, selector, foodBeingEaten);
                } else if (!tamagotchiState.isEating()){
                    buttonHandler.handleEating(foodBeingEaten, selector, currentImage);
                }
                break;

            case 2:
                if (tamagotchiState.isMenuActive()){
                    buttonHandler.buttonMiddleActivateMenu2(menuImage, currentImage, labelSleep);
                } else {
                    buttonHandler.buttonMiddleLeaveMenu2(menuImage, currentImage, labelSleep);
                }
                break;

                case 3:
                    if (tamagotchiState.isMenuActive()){
                        buttonHandler.buttonMiddleActivateMenu3(currentImage, gamePane);
                    } else if (tetrisPane.gameOver) {
                        buttonHandler.buttonMiddleLeaveMenu3(currentImage, gamePane);
                    } else {
                        tetrisPane.rotateCurrentPiece();
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

}