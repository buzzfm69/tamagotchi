package com.example.demo.Handler;

import com.example.demo.LifeStage;
import com.example.demo.Menu.Game.TetrisPane;
import com.example.demo.Menu.Menu1;
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
    private final Menu1 menu1;

    public ButtonHandler(TamagotchiState tamagotchiState, AnimationHelper animationHelper, StateHandler stateHandler, TetrisPane tetrisPane) {
        this.tamagotchiState = tamagotchiState;
        this.animationHelper = animationHelper;
        this.stateHandler = stateHandler;
        this.menu6 = new Menu6(tamagotchiState);
        this.menu1 = new Menu1(tamagotchiState);
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

    // Button Rechts Menü1 Switch
    public void buttonRightSwitchSubMenu1(ImageView selector){
        if (selector.getLayoutX() == menu1.getSelectorXPosition1()) {
            selector.setLayoutX(menu1.getSelectorXPosition2());
        } else if (selector.getLayoutX() == menu1.getSelectorXPosition2()) {
            selector.setLayoutX(menu1.getSelectorXPosition3());
        } else {
            selector.setLayoutX(menu1.getSelectorXPosition1());
        }
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

    // aktiviere Menü 1 = Eating Menu
    public void buttonMiddleActivateMenu1(ImageView currentImage, ImageView meat, ImageView cherry, ImageView bred, ImageView selector, ImageView foodBeingEaten){
        animationHelper.stopIdle();
        tamagotchiState.setMenuActive(false);
        tamagotchiState.setEatingMenuActive(true);
        meat.setVisible(true);
        cherry.setVisible(true);
        bred.setVisible(true);
        selector.setVisible(true);
        selector.setLayoutX(menu1.getSelectorXPosition2());
        selector.setLayoutY(menu1.getSelectorYPosition2());

    }

    // verlasse Menü 1 = Eating Menu
    public void buttonMiddleLeaveMenu1(ImageView currentImage, ImageView meat, ImageView cherry, ImageView bred, ImageView selector, ImageView foodBeingEaten){
        tamagotchiState.setMenuActive(true);
        tamagotchiState.setEatingMenuActive(false);
        meat.setVisible(false);
        cherry.setVisible(false);
        bred.setVisible(false);
        selector.setVisible(false);
        foodBeingEaten.setVisible(false);
        tamagotchiState.setEating(false);

        stateHandler.changeStateAndImage(currentImage);
        animationHelper.startIdle(currentImage);
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

        stateHandler.changeStateAndImage(currentImage);
        animationHelper.startIdle(currentImage);
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

    public void handleEating(ImageView foodBeingEaten, ImageView selector, ImageView currentImage){
        tamagotchiState.setEating(true);
        if (selector.getLayoutX() == menu1.getSelectorXPosition1()) {
            foodBeingEaten.setImage(menu1.getBredGif());
            currentImage.setImage(menu1.getEatingTamagotchi());
            foodBeingEaten.setVisible(true);
        } else if (selector.getLayoutX() == menu1.getSelectorXPosition2()) {
            foodBeingEaten.setImage(menu1.getMeatGif());
            currentImage.setImage(menu1.getEatingTamagotchi());
            foodBeingEaten.setVisible(true);
        } else if (selector.getLayoutX() == menu1.getSelectorXPosition3()) {
            foodBeingEaten.setImage(menu1.getCherryGif());
            currentImage.setImage(menu1.getEatingTamagotchi());
            foodBeingEaten.setVisible(true);
        }
    }

}
