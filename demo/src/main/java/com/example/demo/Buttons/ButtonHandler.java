package com.example.demo.Buttons;

import com.example.demo.AnimationHelper;
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

    // Button Rechts Hauptmenüs, Menüs Switchen
    public void handleClickButtonRightMenu(String[] menuFocusImages, ImageView menuImage){
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
    public void handleClickButtonLeftMenuStart(ImageView currentImage){
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
                }).start();
            }
        }
    }

    // aktiviere Menü 2 = Sleep Menü
    public void handleClickButtonMiddleMenu2(ImageView menuImage, ImageView currentImage){
        tamagotchiState.setSleepMenuActive(true);
        tamagotchiState.setMenuActive(false);
        tamagotchiState.setSleeping(true);

        Image menuSleepOnImage = new Image(getClass().getResource("/images/MenuSleepOn.png").toExternalForm());
        menuImage.setImage(menuSleepOnImage);

        Image babySleepingImage = new Image(getClass().getResource("/images/BabySleeping.png").toExternalForm());
        currentImage.setImage(babySleepingImage);
        animationHelper.stopIdle();
    }

    // aktiviere Menü 6 = Weight Menü
    public void handleClickButtonMiddleMenu6(ImageView menuImage, ImageView currentImage, Label labelWeight, Label labelAge) {
        if (tamagotchiState.imageIndex == 6){
            Image weightDisplayImage = new Image(getClass().getResource("/images/DisplayWeight.png").toExternalForm());
            menuImage.setImage(weightDisplayImage);

            tamagotchiState.setMenuActive(false);
            tamagotchiState.setWeightMenuActive(true);
            tamagotchiState.setBabyActive(false);

            currentImage.setVisible(false);

            labelWeight.setVisible(true);
            labelAge.setVisible(true);
        }
    }

    // verlasse Menü 6
    public void handleButtonMiddleWeightMenu(ImageView menuImage, ImageView currentImage, Label labelWeight, Label labelAge){
        Image imageMenuFocusWeight = new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm());
        menuImage.setImage(imageMenuFocusWeight);
        labelWeight.setVisible(false);
        labelAge.setVisible(false);

        tamagotchiState.setWeightMenuActive(false);
        tamagotchiState.setMenuActive(true);
        tamagotchiState.setBabyActive(true);

        currentImage.setVisible(true);
    }

}
