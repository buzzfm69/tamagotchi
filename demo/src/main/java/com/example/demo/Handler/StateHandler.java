package com.example.demo.Handler;

import com.example.demo.LifeStage;
import com.example.demo.Menu.Menu6;
import com.example.demo.SaveManager;
import com.example.demo.TamagotchiState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StateHandler {
    private TamagotchiState tamagotchiState;

    public StateHandler(TamagotchiState tamagotchiState) {
        this.tamagotchiState = tamagotchiState;
    }

    public void updateState(TamagotchiState newState) {
        this.tamagotchiState = newState;
    }

    // Hunger
    public void decreaseHungry(double amount) {
        tamagotchiState.hungry -= amount;
        if (tamagotchiState.hungry < 0) tamagotchiState.hungry = 0;
    }
    public void increaseHungry(double amount){
        tamagotchiState.hungry += amount;
        if (tamagotchiState.hungry > 100) tamagotchiState.hungry = 100;
    }
    public void updateHungryDisplay(Label labelHungry) {
        labelHungry.setText("Hunger: " + tamagotchiState.hungry+ " / 100");
    }


    // Sauberkeit
    public void decreaseClean(double amount) {
        tamagotchiState.clean -= amount;
        tamagotchiState.happiness--;
        if (tamagotchiState.clean < 0) tamagotchiState.clean = 0;
    }
    public void increaseClean(double amount) {
        tamagotchiState.clean += amount;
        tamagotchiState.happiness++;
        if (tamagotchiState.clean > 100) tamagotchiState.clean = 100;
    }
    public void updateSauberkeitClean(Label labelClean) {
        labelClean.setText("Sauberkeit: " + tamagotchiState.clean + " / 100");
    }


    // Gesundheit
    public void decreaseHealth(double amount) {
        tamagotchiState.health -= amount;
        tamagotchiState.happiness--;
        if (tamagotchiState.health < 0) tamagotchiState.health = 0;
    }
    public void increaseHealth(double amount) {
        tamagotchiState.health += amount;
        tamagotchiState.happiness++;
        if (tamagotchiState.health > 100) tamagotchiState.health = 100;
    }
    public void updateHealthtDisplay(Label labelHealth) {
        labelHealth.setText("Gesundheit: " + tamagotchiState.health + " / 100");
    }


    // Stimmung
    public void decreaseHappiness(double amount) {
        tamagotchiState.happiness -= amount;
        if (tamagotchiState.happiness < 0) tamagotchiState.happiness = 0;
    }
    public void increaseHappiness(double amount) {
        tamagotchiState.happiness += amount;
        if (tamagotchiState.happiness > 100) tamagotchiState.happiness = 100;
    }
    public void updateHappinessDisplay(Label labelHappiness) {
        labelHappiness.setText("Stimmung: " + tamagotchiState.age + " / 100");
    }


    // Gewicht
    public void decreaseWeight(double amount) {
        tamagotchiState.weight -= amount;
        if (tamagotchiState.weight < 0) tamagotchiState.weight = 0;
    }
    public void increaseWeight(double amount) {
        tamagotchiState.weight += amount;
        if (tamagotchiState.weight > 100) tamagotchiState.weight = 100;
    }
    public void updateWeightDisplay(Label labelWeight) {
        labelWeight.setText("Gewicht: " + tamagotchiState.weight + " / 100");
    }


    // Alter
    public void increaseAge(double amount) {
        tamagotchiState.age += amount;
        if (tamagotchiState.age > 100) tamagotchiState.age = 100;
    }
    public void updateAge(Label labelAge) {
        labelAge.setText("Alter: " + tamagotchiState.age + " / 100");
    }


    public void startStateTimer(ImageView menuImage, ImageView currentImage, Label labelWeight, Label labelAge, Label labelSleep, Label labelHungry, Label labelClean, Label labelHealth, Label labelHappiness, Button gameOverButton) {
        Timeline stateTimer = new Timeline(
                new KeyFrame(Duration.seconds(10), e -> {
                    if(!tamagotchiState.isGameOver()){
                        decreaseHungry(20);
                        decreaseClean(0.5);
                        decreaseHappiness(0.25);

                        increaseWeight(2);
                        increaseAge(2);

                        updateLifeStage();
                        changeStateAndImage(currentImage);
                        updateWeightDisplay(labelWeight, labelAge);
                    }
                    gameOver(currentImage, menuImage, gameOverButton, labelWeight, labelAge, labelSleep, labelHungry, labelClean, labelHealth, labelHappiness);
                })
        );
        stateTimer.setCycleCount(Animation.INDEFINITE);
        stateTimer.play();
    }

    public void changeStateAndImage(ImageView currentImage){
        switch (tamagotchiState.getCurrentStage()){
            case EGG:
                Image eggImage = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
                currentImage.setImage(eggImage);
                tamagotchiState.setEggActive(true);
                break;
            case BABY:
                if (tamagotchiState.isSleeping()){
                    Image babySleepingImage = new Image(getClass().getResource("/images/BabySleeping.png").toExternalForm());
                    currentImage.setImage(babySleepingImage);
                }else {
                    Image babySleepingImage = new Image(getClass().getResource("/images/Baby.png").toExternalForm());
                    currentImage.setImage(babySleepingImage);
                }
                tamagotchiState.setBabyActive(true);
                tamagotchiState.setEggActive(false);
                break;
            case CHILD:
                currentImage.setFitWidth(55);
                currentImage.setFitHeight(50);
                if (tamagotchiState.isSleeping()){
                    Image childSleepingImage = new Image(getClass().getResource("/images/ChildSleeping.png").toExternalForm());
                    currentImage.setImage(childSleepingImage);
                }else {
                    Image childImage = new Image(getClass().getResource("/images/Child.png").toExternalForm());
                    currentImage.setImage(childImage);
                }
                tamagotchiState.setChildActive(true);
                tamagotchiState.setBabyActive(false);
                break;
            case TEEN:
                currentImage.setFitWidth(55);
                currentImage.setFitHeight(50);
                if (tamagotchiState.isSleeping()){
                    Image teenSleepingImage = new Image(getClass().getResource("/images/TeenagerSleeping.png").toExternalForm());
                    currentImage.setImage(teenSleepingImage);
                }else {
                    Image teenagerImage = new Image(getClass().getResource("/images/Teenager.png").toExternalForm());
                    currentImage.setImage(teenagerImage);
                }
                tamagotchiState.setTeenagerActive(true);
                tamagotchiState.setChildActive(false);
                break;
            case ADULT:
                currentImage.setFitWidth(55);
                currentImage.setFitHeight(50);
                if (tamagotchiState.isSleeping()){
                    Image adultSleepingImage = new Image(getClass().getResource("/images/AdultSleeping.png").toExternalForm());
                    currentImage.setImage(adultSleepingImage);
                } else {
                    Image adultImage = new Image(getClass().getResource("/images/Adult.png").toExternalForm());
                    currentImage.setImage(adultImage);
                }
                tamagotchiState.setAdultActive(true);
                tamagotchiState.setTeenagerActive(false);
                break;
            default:
            eggImage = new Image(getClass().getResource("/images/Ei.png").toExternalForm());
            currentImage.setImage(eggImage);
            tamagotchiState.setEggActive(true);
            tamagotchiState.setChildActive(false);
            tamagotchiState.setBabyActive(false);
            tamagotchiState.setTeenagerActive(false);
            tamagotchiState.setAdultActive(false);
            break;
/*
            case DEAD:
                if (tamagotchiState.isTamagotchiDead()){
                    //gameOver(currentImage);
                }
                break;
 */
        }
    }

    public void updateLifeStage(){
        if (tamagotchiState.isGameOver()) {
           tamagotchiState.setCurrentStage(LifeStage.DEAD);
        } else {
            if (tamagotchiState.age >= 3 && tamagotchiState.age < 13) {
                tamagotchiState.setCurrentStage(LifeStage.CHILD);
            } else if (tamagotchiState.age >= 13 && tamagotchiState.age < 18) {
                tamagotchiState.setCurrentStage(LifeStage.TEEN);
            } else if (tamagotchiState.age >= 19) {
                tamagotchiState.setCurrentStage(LifeStage.ADULT);
            } else {
                tamagotchiState.setCurrentStage(LifeStage.BABY);
            }
        }
    }

    public void updateWeightDisplay(Label labelWeight, Label labelAge) {
        labelWeight.setText(tamagotchiState.weight + " kg");
        labelAge.setText(tamagotchiState.age + " yr");
    }

    public void resetImagePosition(ImageView currentImage, double x, double y) {
        currentImage.setTranslateX(0);
        currentImage.setTranslateY(0);
        currentImage.setLayoutX(x);
        currentImage.setLayoutY(y);
    }

    public void gameOver(ImageView currentImage, ImageView menuImage, Button gameOverButton, Label labelWeight, Label labelAge, Label labelSleep, Label labelHungry, Label labelClean, Label labelHealth, Label labelHappiness){
        if (tamagotchiState.hungry ==0 || tamagotchiState.health ==0){
            Image emptyScreen = new Image(getClass().getResource("/images/EmptyScreen.png").toExternalForm());
            menuImage.setImage(emptyScreen);

            tamagotchiState.clickCountMenu = 0;
            tamagotchiState.weight = 1;
            tamagotchiState.age = 1;
            tamagotchiState.hungry = 100;
            tamagotchiState.happiness = 100;
            tamagotchiState.health = 100;
            tamagotchiState.clean = 100;

            tamagotchiState.setTamagotchiDead(true);
            tamagotchiState.setGameOver(true);

            tamagotchiState.setMenuActive(false);
            tamagotchiState.setWeightMenuActive(false);
            tamagotchiState.setGameMenuActive(false);
            tamagotchiState.setEatingMenuActive(false);
            tamagotchiState.setSleepMenuActive(false);

            labelWeight.setVisible(false);
            labelAge.setVisible(false);
            labelSleep.setVisible(false);
            labelHungry.setVisible(false);
            labelClean.setVisible(false);
            labelHealth.setVisible(false);
            labelHappiness.setVisible(false);

            updateLifeStage();
            currentImage.setVisible(false);
            gameOverButton.setVisible(true);
        }
    }

}
