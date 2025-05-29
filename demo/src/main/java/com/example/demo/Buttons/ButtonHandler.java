package com.example.demo.Buttons;

import com.example.demo.AnimationHelper;
import com.example.demo.LifeStage;
import com.example.demo.TamagotchiState;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonHandler {
    private final TamagotchiState tamagotchiState;
    private final AnimationHelper animationHelper;

    public ButtonHandler(TamagotchiState tamagotchiState, AnimationHelper animationHelper) {
        this.tamagotchiState = tamagotchiState;
        this.animationHelper = animationHelper;
    }

    public void changeStateAndImage(ImageView currentImage){
        switch (tamagotchiState.getCurrentStage()){
            case BABY:
                if (tamagotchiState.isSleeping()){
                    Image babySleepingImage = new Image(getClass().getResource("/images/BabySleeping.png").toExternalForm());
                    currentImage.setImage(babySleepingImage);
                }else {
                    Image babySleepingImage = new Image(getClass().getResource("/images/Baby.png").toExternalForm());
                    currentImage.setImage(babySleepingImage);
                }
                tamagotchiState.setBabyActive(true);
                break;
            case CHILD:
                Image childImage = new Image(getClass().getResource("/images/Child.png").toExternalForm());
                currentImage.setImage(childImage);
                tamagotchiState.setChildActive(true);
                break;
            case TEEN:
                Image teenagerImage = new Image(getClass().getResource("/images/Teenager.png").toExternalForm());
                currentImage.setImage(teenagerImage);
                tamagotchiState.setTeenagerActive(true);
                break;
            case ADULT:
                Image adultImage = new Image(getClass().getResource("/images/Adult.png").toExternalForm());
                currentImage.setImage(adultImage);
                tamagotchiState.setAdultActive(true);
                break;
        }
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
        tamagotchiState.setSleepMenuActive(true);
        tamagotchiState.setMenuActive(false);
        tamagotchiState.setSleeping(true);

        Image menuSleepOnImage = new Image(getClass().getResource("/images/MenuSleepOn.png").toExternalForm());
        menuImage.setImage(menuSleepOnImage);

        labelSleep.setVisible(true);
        animationHelper.stopIdle();
        animationHelper.animateSleepLabel(labelSleep);
        changeStateAndImage(currentImage);
    }

    // verlasse Menü 2 = Sleep Menü
    public void buttonMiddleLeaveMenu2(ImageView menuImage, ImageView currentImage, Label labelSleep){
        Image imageMenuFocusLight = new Image(getClass().getResource("/images/MenuFocusLight.png").toExternalForm());
        menuImage.setImage(imageMenuFocusLight);

        tamagotchiState.setSleepMenuActive(false);
        tamagotchiState.setSleeping(false);
        tamagotchiState.setMenuActive(true);

        changeStateAndImage(currentImage);
        animationHelper.startIdle(currentImage);
        animationHelper.stopSleepLabelAnimation();
        labelSleep.setVisible(false);
    }

    // aktiviere Menü 6 = Weight Menü
    public void buttonMiddleActivateMenu6(ImageView menuImage, ImageView currentImage, Label labelWeight, Label labelAge) {
        if (tamagotchiState.imageIndex == 6){
            Image weightDisplayImage = new Image(getClass().getResource("/images/DisplayWeight.png").toExternalForm());
            menuImage.setImage(weightDisplayImage);

            tamagotchiState.setMenuActive(false);
            tamagotchiState.setWeightMenuActive(true);
            tamagotchiState.resetAllStates();

            currentImage.setVisible(false);

            labelWeight.setVisible(true);
            labelAge.setVisible(true);
        }
    }

    // verlasse Menü 6
    public void buttonMiddleLeaveMenu6(ImageView menuImage, ImageView currentImage, Label labelWeight, Label labelAge){
        Image imageMenuFocusWeight = new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm());
        menuImage.setImage(imageMenuFocusWeight);
        labelWeight.setVisible(false);
        labelAge.setVisible(false);

        tamagotchiState.setWeightMenuActive(false);
        tamagotchiState.setMenuActive(true);

        changeStateAndImage(currentImage);
        currentImage.setVisible(true);
    }

}
