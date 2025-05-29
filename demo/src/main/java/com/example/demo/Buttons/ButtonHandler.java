package com.example.demo.Buttons;

import com.example.demo.AnimationHelper;
import com.example.demo.TamagotchiState;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonHandler {
    private final TamagotchiState tamagotchiState;
    AnimationHelper animationHelper = new AnimationHelper();


    public ButtonHandler(TamagotchiState tamagotchiState) {
        this.tamagotchiState = tamagotchiState;
    }

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
                    tamagotchiState.setBabyActive(true);
                }).start();
            }
        }
    }

    public void handleClickButtonMiddleMenu6(ImageView menuImage, ImageView currentImage, Label labelWeight, Label labelAge) {
        Image weightDisplayImage = new Image(getClass().getResource("/images/DisplayWeight.png").toExternalForm());
        menuImage.setImage(weightDisplayImage);

        tamagotchiState.setMenuActive(false);
        tamagotchiState.setWeightMenuActive(true);
        tamagotchiState.setBabyActive(false);

        currentImage.setVisible(false);

        labelWeight.setVisible(true);
        labelAge.setVisible(true);
    }

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
