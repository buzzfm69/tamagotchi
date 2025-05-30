package com.example.demo.Menu;

import com.example.demo.TamagotchiState;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Menu6 {
    private final TamagotchiState tamagotchiState;

    public Menu6(TamagotchiState tamagotchiState) {
        this.tamagotchiState = tamagotchiState;
    }

    public void showSubMenu6(int index, ImageView menuImage, Label labelWeight, Label labelAge, Label labelHungry, Label labelClean, Label labelHealth, Label labelHappiness) {
        // Alle Labels unsichtbar machen
        hideSubMenu6(labelHungry, labelClean, labelHealth, labelHappiness);

        switch (index) {
            case 0 -> {
                labelHungry.setVisible(true);
                menuImage.setImage(new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm()));
                updateHeartImages(tamagotchiState.hungry, menuImage);
            }
            case 1 -> {
                labelClean.setVisible(true);
                menuImage.setImage(new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm()));
                updateHeartImages(tamagotchiState.clean, menuImage);
            }
            case 2 -> {
                labelHealth.setVisible(true);
                menuImage.setImage(new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm()));
                updateHeartImages(tamagotchiState.health, menuImage);
            }
            case 3 -> {
                labelHappiness.setVisible(true);
                menuImage.setImage(new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm()));
                updateHeartImages(tamagotchiState.happiness, menuImage);
            }
            case 4 -> { // Wieder Gewicht + Alter anzeigen
                menuImage.setImage(new Image(getClass().getResource("/images/DisplayWeight.png").toExternalForm()));
                labelWeight.setVisible(true);
                labelAge.setVisible(true);
            }
        }
    }

    public void hideSubMenu6(Label labelHungry, Label labelClean, Label labelHealth, Label labelHappiness){
        labelHungry.setVisible(false);
        labelClean.setVisible(false);
        labelHealth.setVisible(false);
        labelHappiness.setVisible(false);
    }

    public void updateHeartImages(int value, ImageView menuImage){
        String imagePath;

        if (value <= 16){
            imagePath = "/images/0_5Heart.png";
        }else if (value <= 33) {
            imagePath = "/images/1Heart.png";
        } else if (value <= 49) {
            imagePath = "/images/1_5Heart.png";
        } else if (value <= 66) {
            imagePath = "/images/2Heart.png";
        } else if (value <= 83) {
            imagePath = "/images/2_5Heart.png";
        } else {
            imagePath = "/images/3Heart.png";
        }
        Image heartImage = new Image(getClass().getResource(imagePath).toExternalForm());
        menuImage.setImage(heartImage);
    }

}
