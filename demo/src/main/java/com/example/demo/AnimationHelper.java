package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.*;


public class AnimationHelper {
    public TranslateTransition animateJump(ImageView imageView, double height, int cycleCount, double durationMs) {
        TranslateTransition jump = new TranslateTransition(Duration.millis(durationMs), imageView); // Dauer Sprung
        jump.setByY(-height);  // x Pixel nach oben
        jump.setCycleCount(cycleCount);  // Anzahl Sprünge
        jump.setAutoReverse(true);
        return jump;
    }

    public static TranslateTransition move(ImageView imageView, double byX, double byY, double durationMs) {
        TranslateTransition move = new TranslateTransition(Duration.millis(durationMs), imageView);
        move.setByX(byX);
        move.setByY(byY);
        return move;
    }

    // Bewegung nach rechts
    public static TranslateTransition moveRight(ImageView imageView, double distance, double durationMs) {
        TranslateTransition move = new TranslateTransition(Duration.millis(durationMs), imageView);
        move.setByX(distance);
        return move;
    }

    // Bewegung nach links
    public static TranslateTransition moveLeft(ImageView imageView, double distance, double durationMs) {
        return moveRight(imageView, -distance, durationMs);
    }

    // Bewegung nach unten
    public static TranslateTransition moveDown(ImageView imageView, double distance, double durationMs) {
        TranslateTransition move = new TranslateTransition(Duration.millis(durationMs), imageView);
        move.setByY(distance);
        return move;
    }

    // Bewegung nach oben
    public static TranslateTransition moveUp(ImageView imageView, double distance, double durationMs) {
        return moveDown(imageView, -distance, durationMs);
    }

    // Idle Bewegung
    //public TranslateTransition idle(ImageView imageView){
    //    TranslateTransition idle = new TranslateTransition(Duration.millis(300), imageView);
    //    idle.setByX(5);
    //    idle.setCycleCount(Animation.INDEFINITE); // Endlosschleife
    //    idle.setAutoReverse(true);
    //    return idle;
    //}

    public void animateStartSequence(ImageView imageView) {
        // 1. hüpfen 3x
        TranslateTransition jump1 = animateJump(imageView, 10, 3, 250);

        // 2. Kurze Pause
        PauseTransition pause1 = new PauseTransition(Duration.millis(400));

        // 3. Schritte nach rechts
        TranslateTransition moveRight = AnimationHelper.moveRight(imageView, 10, 400);

        // 4. Schritte nach links
        TranslateTransition moveLeft = AnimationHelper.moveLeft(imageView, 10, 400);

        // 5. Wieder springen
        TranslateTransition jump2 = animateJump(imageView, 10, 3, 300);

        // 6. Nach unten (fallen)
        TranslateTransition fall = AnimationHelper.moveDown(imageView, 10, 300);

        TranslateTransition fall2 = AnimationHelper.moveDown(imageView, 15, 500);

        // Idle Animation: leichtes Wackeln
        TranslateTransition idle = AnimationHelper.moveRight(imageView, 5, 300);
        idle.setCycleCount(Animation.INDEFINITE);
        idle.setAutoReverse(true);

        // Sequenz erstellen
        SequentialTransition sequence = new SequentialTransition(
                jump1,
                fall,
                pause1,
                moveRight,
                moveLeft,
                jump2,
                fall2);
        sequence.setOnFinished(e -> idle.play());
        sequence.play();
    }
}
