package com.example.demo.Menu;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Menu6 {
    public void showSubMenu6(int index, ImageView menuImage, Label labelWeight, Label labelAge, Label labelHungry, Label labelClean, Label labelHealth, Label labelHappiness) {
        // Alle Labels unsichtbar machen
        hideSubMenu6(labelHungry, labelClean, labelHealth, labelHappiness);

        switch (index) {
            case 0 -> {
                labelHungry.setVisible(true);
                menuImage.setImage(new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm()));
            }
            case 1 -> {
                labelClean.setVisible(true);
                menuImage.setImage(new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm()));
            }
            case 2 -> {
                labelHealth.setVisible(true);
                menuImage.setImage(new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm()));
            }
            case 3 -> {
                labelHappiness.setVisible(true);
                menuImage.setImage(new Image(getClass().getResource("/images/MenuFocusWeight.png").toExternalForm()));
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
}
