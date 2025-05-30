package com.example.demo.Handler;

import com.example.demo.LifeStage;
import com.example.demo.Menu.Game.TetrisPane;
import com.example.demo.Menu.Menu6;
import com.example.demo.TamagotchiState;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ButtonHandler {
    private final TamagotchiState tamagotchiState;
    private final AnimationHelper animationHelper;
    private final StateHandler stateHandler;
    private final Menu6 menu6;
    private final TetrisPane tetrisPane;

    public ButtonHandler(TamagotchiState tamagotchiState, AnimationHelper animationHelper, StateHandler stateHandler, TetrisPane tetrisPane) {
        this.tamagotchiState = tamagotchiState;
        this.animationHelper = animationHelper;
        this.stateHandler = stateHandler;
        this.menu6 = new Menu6(tamagotchiState);
        this.tetrisPane = tetrisPane;
    }


    // Button Rechts Hauptmenüs, Menüs Switchen
    public void buttonRightSwitchMenu(String[] menuFocusImages, ImageView menuImage){
        if (tamagotchiState.isMenuActive()) {
            // Bildpfad laden
            String path = menuFocusImages[tamagotchiState.imageIndex];

            // Bild setzen
            Image image = new Image(getClass().getResource(path).toExternalForm());
            menuImage.setImage(image);

            // Index erhöhen, bei 8 zurück auf 0 setzen
            tamagotchiState.imageIndex = (tamagotchiState.imageIndex + 1) % menuFocusImages.length;
        }
    }

    // Button Rechts Menü6 Switch
    public void buttonRightSwitchSubMenu6(
            ImageView menuImage,
            Label labelWeight,
            Label labelAge,
            Label labelHungry,
            Label labelClean,
            Label labelHealth,
            Label labelHappiness){
        tamagotchiState.subMenu6Index = (tamagotchiState.subMenu6Index + 1) % 5;
        labelWeight.setVisible(false);
        labelAge.setVisible(false);
        menu6.showSubMenu6(tamagotchiState.subMenu6Index, menuImage, labelWeight, labelAge, labelHungry, labelClean, labelHealth, labelHappiness);

    }

    // Linker Button beim Start, 3x klicken zum aktivieren
    public void buttonLeftActivateTamagotchi(ImageView currentImage){
        tamagotchiState.clickCountMenu++;

        if (tamagotchiState.isMenuActive()){
            if (tamagotchiState.clickCountMenu ==3){
                animationHelper.animateStartSequence(currentImage); //Ei Sequenz starten

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
                        Image babyImage = new Image(getClass().getResource("/images/Baby.png").toExternalForm());
                        currentImage.setImage(babyImage);
                    });
                    tamagotchiState.setEggActive(false);
                    tamagotchiState.setBabyActive(true);
                    tamagotchiState.setCurrentStage(LifeStage.BABY);
                }).start();
            }
        }
    }

    // aktiviere Menü 2 = Sleep Menü
    public void buttonMiddleActivateMenu2(ImageView menuImage, ImageView currentImage, Label labelSleep){
        animationHelper.stopIdle();
        tamagotchiState.setSleepMenuActive(true);
        tamagotchiState.setMenuActive(false);
        tamagotchiState.setSleeping(true);

        Image menuSleepOnImage = new Image(getClass().getResource("/images/MenuSleepOn.png").toExternalForm());
        menuImage.setImage(menuSleepOnImage);

        labelSleep.setVisible(true);
        //animationHelper.stopIdle();
        animationHelper.animateSleepLabel(labelSleep);
        stateHandler.changeStateAndImage(currentImage);
    }

    // verlasse Menü 2 = Sleep Menü
    public void buttonMiddleLeaveMenu2(ImageView menuImage, ImageView currentImage, Label labelSleep){
        Image imageMenuFocusLight = new Image(getClass().getResource("/images/MenuFocusLight.png").toExternalForm());
        menuImage.setImage(imageMenuFocusLight);

        tamagotchiState.setSleepMenuActive(false);
        tamagotchiState.setSleeping(false);
        tamagotchiState.setMenuActive(true);

        stateHandler.changeStateAndImage(currentImage);

        animationHelper.startIdle(currentImage);
        animationHelper.stopSleepLabelAnimation();
        labelSleep.setVisible(false);
    }

    // aktiviere Menü 3 = Game Menu
    public void buttonMiddleActivateMenu3(ImageView currentImage, AnchorPane gamePane){
        tamagotchiState.setMenuActive(false);
        tamagotchiState.setGameMenuActive(true);
        gamePane.setVisible(true);
        gamePane.getChildren().add(tetrisPane);
        tetrisPane.startGame();


        currentImage.setVisible(false);
    }

    // verlasse Menü 3 = Game Menu
    public void buttonMiddleLeaveMenu3(ImageView currentImage, AnchorPane gamePane){
        tamagotchiState.setMenuActive(true);
        tamagotchiState.setGameMenuActive(false);
        gamePane.setVisible(false);
        gamePane.getChildren().remove(tetrisPane);
        tetrisPane.stopGame();


        currentImage.setVisible(true);
    }

    // aktiviere Menü 6 = Weight Menü
    public void buttonMiddleActivateMenu6(ImageView menuImage, ImageView currentImage, Label labelWeight, Label labelAge) {
        if (tamagotchiState.imageIndex == 6){
            Image weightDisplayImage = new Image(getClass().getResource("/images/DisplayWeight.png").toExternalForm());
            menuImage.setImage(weightDisplayImage);

            tamagotchiState.subMenu6Index = 4;

            tamagotchiState.setMenuActive(false);
            tamagotchiState.setWeightMenuActive(true);
            tamagotchiState.resetAllStates();

            currentImage.setVisible(false);

            labelWeight.setVisible(true);
            labelAge.setVisible(true);
        }
    }

    // verlasse Menü 6
    public void buttonMiddleLeaveMenu6(ImageView menuImage, ImageView currentImage, Label labelWeight, Label labelAge, Label labelHungry, Label labelClean, Label labelHealth, Label labelHappiness){
        Image imageMenuFocusWeight = new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm());
        menuImage.setImage(imageMenuFocusWeight);
        labelWeight.setVisible(false);
        labelAge.setVisible(false);
        menu6.hideSubMenu6(labelHungry, labelClean, labelHealth, labelHappiness);

        tamagotchiState.setWeightMenuActive(false);
        tamagotchiState.setMenuActive(true);

        stateHandler.changeStateAndImage(currentImage);
        currentImage.setVisible(true);
    }

}
