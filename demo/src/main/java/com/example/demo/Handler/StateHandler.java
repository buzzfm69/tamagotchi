package com.example.demo.Handler;

import com.example.demo.LifeStage;
import com.example.demo.Menu.Menu6;
import com.example.demo.TamagotchiState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StateHandler {
    private final TamagotchiState tamagotchiState;
    private final Menu6 menu6;

    public StateHandler(TamagotchiState tamagotchiState) {
        this.tamagotchiState = tamagotchiState;
        this.menu6 = new Menu6(tamagotchiState);
    }

    // Hunger
    public void decreaseHungry(int amount) {
        tamagotchiState.hungry -= amount;
        if (tamagotchiState.hungry < 0) tamagotchiState.hungry = 0;
    }
    public void increaseHungry(int amount){
        tamagotchiState.hungry += amount;
        if (tamagotchiState.hungry > 100) tamagotchiState.hungry = 100;
    }
    public void updateHungryDisplay(Label labelHungry) {
        labelHungry.setText("Hunger: " + tamagotchiState.hungry+ " / 100");
    }


    // Sauberkeit
    public void decreaseClean(int amount) {
        tamagotchiState.clean -= amount;
        if (tamagotchiState.clean < 0) tamagotchiState.clean = 0;
    }
    public void increaseClean(int amount) {
        tamagotchiState.clean += amount;
        if (tamagotchiState.clean > 100) tamagotchiState.clean = 100;
    }
    public void updateSauberkeitClean(Label labelClean) {
        labelClean.setText("Sauberkeit: " + tamagotchiState.clean + " / 100");
    }


    // Gesundheit
    public void decreaseHealth(int amount) {
        tamagotchiState.health -= amount;
        if (tamagotchiState.health < 0) tamagotchiState.health = 0;
    }
    public void increaseHealth(int amount) {
        tamagotchiState.health += amount;
        if (tamagotchiState.health > 100) tamagotchiState.health = 100;
    }
    public void updateHealthtDisplay(Label labelHealth) {
        labelHealth.setText("Gesundheit: " + tamagotchiState.health + " / 100");
    }


    // Stimmung
    public void decreaseHappiness(int amount) {
        tamagotchiState.happiness -= amount;
        if (tamagotchiState.happiness < 0) tamagotchiState.happiness = 0;
    }
    public void increaseHappiness(int amount) {
        tamagotchiState.happiness += amount;
        if (tamagotchiState.happiness > 100) tamagotchiState.happiness = 100;
    }
    public void updateHappinessDisplay(Label labelHappiness) {
        labelHappiness.setText("Stimmung: " + tamagotchiState.age + " / 100");
    }


    // Gewicht
    public void decreaseWeight(int amount) {
        tamagotchiState.weight -= amount;
        if (tamagotchiState.weight < 0) tamagotchiState.weight = 0;
    }
    public void increaseWeight(int amount) {
        tamagotchiState.weight += amount;
        if (tamagotchiState.weight > 100) tamagotchiState.weight = 100;
    }
    public void updateWeightDisplay(Label labelWeight) {
        labelWeight.setText("Gewicht: " + tamagotchiState.weight + " / 100");
    }


    // Alter
    public void increaseAge(int amount) {
        tamagotchiState.age += amount;
        if (tamagotchiState.age > 100) tamagotchiState.age = 100;
    }
    public void updateAge(Label labelAge) {
        labelAge.setText("Alter: " + tamagotchiState.age + " / 100");
    }


    public void startStateTimer(ImageView menuImage) {
        Timeline stateTimer = new Timeline(
                new KeyFrame(Duration.seconds(10), e -> {
                    decreaseHungry(20);

                    decreaseClean(5);

                    increaseAge(1);
                    updateLifeStage();
                })
        );
        stateTimer.setCycleCount(Animation.INDEFINITE);
        stateTimer.play();
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

    public void updateLifeStage(){
        if (tamagotchiState.age >= 3 && tamagotchiState.age < 6) {
            tamagotchiState.setCurrentStage(LifeStage.CHILD);
        } else if (tamagotchiState.age >= 6 && tamagotchiState.age < 9) {
            tamagotchiState.setCurrentStage(LifeStage.TEEN);
        } else if (tamagotchiState.age >= 9) {
            tamagotchiState.setCurrentStage(LifeStage.ADULT);
        } else {
            tamagotchiState.setCurrentStage(LifeStage.BABY);
        }
    }
}
