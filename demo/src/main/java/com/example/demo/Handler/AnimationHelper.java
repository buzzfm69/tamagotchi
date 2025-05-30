package com.example.demo.Handler;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.*;


public class AnimationHelper {
    private TranslateTransition idleAnimation;
    private FadeTransition sleepFade;
    private Timeline sleepTextTimeline;

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
        idleAnimation = AnimationHelper.moveRight(imageView, 5, 300);
        idleAnimation.setCycleCount(Animation.INDEFINITE);
        idleAnimation.setAutoReverse(true);

        // Sequenz erstellen
        SequentialTransition sequence = new SequentialTransition(
                jump1,
                fall,
                pause1,
                moveRight,
                moveLeft,
                jump2,
                fall2);
        sequence.setOnFinished(e -> idleAnimation.play());
        sequence.play();
    }

    public void stopIdle() {
        if (idleAnimation != null) {
            idleAnimation.stop();
        }
    }

    public void startIdle(ImageView imageView){
        idleAnimation = new TranslateTransition(Duration.millis(300), imageView);
        idleAnimation.setByX(5);
        idleAnimation.setByX(-5);
        idleAnimation.setCycleCount(TranslateTransition.INDEFINITE);
        idleAnimation.setAutoReverse(true);
        idleAnimation.play();
    }

    public void animateSleepLabel(Label labelSleep){
        // Texte, die im Loop angezeigt werden
        String[] sleepTexts = {"z", "zZ", "zZz"};

        // Timeline für Textwechsel
        sleepTextTimeline = new Timeline(new KeyFrame(Duration.seconds(0.8), event -> {
            String currentText = labelSleep.getText();
            int nextIndex = 0;
            for (int i = 0; i < sleepTexts.length; i++) {
                if (sleepTexts[i].equals(currentText)) {
                    nextIndex = (i + 1) % sleepTexts.length;
                    break;
                }
            }
            labelSleep.setText(sleepTexts[nextIndex]);
        }));
        sleepTextTimeline.setCycleCount(Animation.INDEFINITE);
        sleepTextTimeline.play();

        // Fade-Animation für leichtes Ein-/Ausblenden
        sleepFade = new FadeTransition(Duration.seconds(1.2), labelSleep);
        sleepFade.setFromValue(1.0);
        sleepFade.setToValue(0.4);
        sleepFade.setCycleCount(Animation.INDEFINITE);
        sleepFade.setAutoReverse(true);
        sleepFade.play();

        // Label sichtbar machen
        labelSleep.setVisible(true);
    }

    public void stopSleepLabelAnimation() {
        if (sleepTextTimeline != null) {
            sleepTextTimeline.stop();
        }
        if (sleepFade != null) {
            sleepFade.stop();
        }
    }
}
