package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TamagotchiState {
    private boolean isMenuActive;
    private boolean isWeightMenuActive = false;
    private boolean isGameMenuActive;
    private boolean isSleepMenuActive = false;
    private boolean isStartButtonActive;

    private  boolean isEggActive;
    private boolean isBabyActive;
    private boolean isChildActive;
    private boolean isTeenagerActive;
    private boolean isAdultActive;

    private boolean isSleeping;

    public int imageIndex = 0;
    public int subMenu6Index = -1; // -1 für "noch im WeightMenü"
    public int clickCountMenu = 0;

    public int weight = 1;
    public int age = 1;

    public int hungry = 100;
    public int clean = 100;
    public int happiness = 100;
    public int health = 100;

    private LifeStage currentStage;
    private Timeline hungerTimer;

    // Hunger
    public void decreaseHungry(int amount) {
        hungry -= amount;
        if (hungry < 0) hungry = 0;
    }
    public void increaseHungry(int amount){
        hungry += amount;
        if (hungry > 100) hungry = 100;
    }
    public void updateHungryDisplay(Label labelHungry) {
        labelHungry.setText("Hunger: " + hungry+ " / 100");
    }


    // Sauberkeit
    public void decreaseClean(int amount) {
        clean -= amount;
        if (clean < 0) clean = 0;
    }
    public void increaseClean(int amount) {
        clean += amount;
        if (clean > 100) clean = 100;
    }
    public void updateSauberkeitClean(Label labelClean) {
        labelClean.setText("Sauberkeit: " + clean + " / 100");
    }


    // Gesundheit
    public void decreaseHealth(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }
    public void increaseHealth(int amount) {
        health += amount;
        if (health > 100) health = 100;
    }
    public void updateHealthtDisplay(Label labelHealth) {
        labelHealth.setText("Gesundheit: " + health + " / 100");
    }


    // Stimmung
    public void decreaseHappiness(int amount) {
        happiness -= amount;
        if (happiness < 0) happiness = 0;
    }
    public void increaseHappiness(int amount) {
        happiness += amount;
        if (happiness > 100) happiness = 100;
    }
    public void updateHappinessDisplay(Label labelHappiness) {
        labelHappiness.setText("Stimmung: " + age + " / 100");
    }


    // Gewicht
    public void decreaseWeight(int amount) {
        weight -= amount;
        if (weight < 0) weight = 0;
    }
    public void increaseWeight(int amount) {
        weight += amount;
        if (weight > 100) weight = 100;
    }
    public void updateWeightDisplay(Label labelWeight) {
        labelWeight.setText("Gewicht: " + weight + " / 100");
    }


    // Alter
    public void decreaseAge(int amount) {
        age -= amount;
        if (age < 0) age = 0;
    }
    public void increaseAge(int amount) {
        age += amount;
        if (age > 100) age = 100;
    }
    public void updateAge(Label labelAge) {
        labelAge.setText("Alter: " + age + " / 100");
    }


    public void startStateTimer(Label labelHungry) {
        Timeline stateTimer = new Timeline(
                new KeyFrame(Duration.seconds(10), e -> {
                    decreaseHungry(10);
                    updateHungryDisplay(labelHungry);
                })
        );
        stateTimer.setCycleCount(Animation.INDEFINITE);
        stateTimer.play();
    }

    public LifeStage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(LifeStage stage) {
        this.currentStage = stage;
    }

    public boolean isMenuActive() {
        return isMenuActive;
    }

    public void setMenuActive(boolean menuActive) {
        isMenuActive = menuActive;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public int getClickCountMenu() {
        return clickCountMenu;
    }

    public void setClickCountMenu(int clickCountMenu) {
        this.clickCountMenu = clickCountMenu;
    }

    public boolean isWeightMenuActive() {
        return isWeightMenuActive;
    }

    public void setWeightMenuActive(boolean weightMenuActive) {
        isWeightMenuActive = weightMenuActive;
    }

    public boolean isBabyActive() {
        return isBabyActive;
    }

    public void setBabyActive(boolean babyActive) {
        isBabyActive = babyActive;
    }

    public boolean isChildActive() {
        return isChildActive;
    }

    public void setChildActive(boolean childActive) {
        isChildActive = childActive;
    }

    public boolean isTeenagerActive() {
        return isTeenagerActive;
    }

    public void setTeenagerActive(boolean teenagerActive) {
        isTeenagerActive = teenagerActive;
    }

    public boolean isAdultActive() {
        return isAdultActive;
    }

    public void setAdultActive(boolean adultActive) {
        isAdultActive = adultActive;
    }

    public boolean isGameMenuActive() {
        return isGameMenuActive;
    }

    public void setGameMenuActive(boolean gameMenuActive) {
        isGameMenuActive = gameMenuActive;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getSleepMenuActive() {
        return isSleepMenuActive;
    }

    public void setSleepMenuActive(Boolean sleepMenuActive) {
        isSleepMenuActive = sleepMenuActive;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public boolean isEggActive() {
        return isEggActive;
    }

    public void setEggActive(boolean eggActive) {
        isEggActive = eggActive;
    }

    public boolean isSleepMenuActive() {
        return isSleepMenuActive;
    }

    public void setSleepMenuActive(boolean sleepMenuActive) {
        isSleepMenuActive = sleepMenuActive;
    }

    public void resetAllStates() {
        setBabyActive(false);
        setChildActive(false);
        setTeenagerActive(false);
        setAdultActive(false);
    }

    public boolean isStartButtonActive() {
        return isStartButtonActive;
    }

    public void setStartButtonActive(boolean startButtonActive) {
        isStartButtonActive = startButtonActive;
    }
}
