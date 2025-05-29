package com.example.demo.Buttons;

import com.example.demo.AnimationHelper;
import com.example.demo.TamagotchiState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonHandler {
    private final TamagotchiState tamagotchiState;
    AnimationHelper animationHelper = new AnimationHelper();

    public ButtonHandler(TamagotchiState tamagotchiState) {
        this.tamagotchiState = tamagotchiState;
    }

    public void handleClickButtonRightMenu(String[] menuFocusImages, ImageView menuImage){
        if (tamagotchiState.isMenuActive) {
            // Bildpfad laden
            String path = menuFocusImages[tamagotchiState.imageIndex];

            // Bild setzen
            Image image = new Image(getClass().getResource(path).toExternalForm());
            menuImage.setImage(image);

            // Index erhöhen, bei 8 zurück auf 0 setzen
            tamagotchiState.imageIndex = (tamagotchiState.imageIndex + 1) % menuFocusImages.length;
        }
    }

    public void handleClickButtonLeftMenu(ImageView eggImage){
        tamagotchiState.clickCountMenu++;

        if (tamagotchiState.clickCountMenu ==3){
            animationHelper.animateStartSequence(eggImage); //Ei Sequenz starten

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
                    Image newImage = new Image(getClass().getResource("/images/Baby.png").toExternalForm());
                    eggImage.setImage(newImage);
                });
            }).start();
        }
    }
}
