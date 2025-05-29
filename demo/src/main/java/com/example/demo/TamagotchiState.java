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
    public int clickCountMenu = 0;

    public int weight = 1;
    public int age = 1;

    public int hunger = 100;
    public int sauberkeit = 100;
    public int stimmung = 100;
    public int gesundheit = 100;

    private LifeStage currentStage;
    private Timeline hungerTimer;

    // Hunger
    public void decreaseHunger(int amount) {
        hunger -= amount;
        if (hunger < 0) hunger = 0;
    }
    public void increaseHunger(int amount){
        hunger += amount;
        if (hunger > 100) hunger = 100;
    }
    public void updateHungerDisplay(Label labelHunger) {
        labelHunger.setText("Hunger: " + hunger + " / 100");
    }

    // Sauberkeit
    public void decreaseSauberkeit(int amount) {
        sauberkeit -= amount;
        if (sauberkeit < 0) sauberkeit = 0;
    }
    public void increaseSauberkeit(int amount) {
        sauberkeit += amount;
        if (sauberkeit > 100) sauberkeit = 100;
    }
    public void updateSauberkeitDisplay(Label labelSauberkeit) {
        labelSauberkeit.setText("Sauberkeit: " + sauberkeit + " / 100");
    }

    // Gesundheit
    public void decreaseGesundheit(int amount) {
        gesundheit -= amount;
        if (gesundheit < 0) gesundheit = 0;
    }
    public void increaseGesundheit(int amount) {
        gesundheit += amount;
        if (gesundheit > 100) gesundheit = 100;
    }
    public void updateGesundheitDisplay(Label labelGesundheit) {
        labelGesundheit.setText("Gesundheit: " + gesundheit + " / 100");
    }

    // Stimmung
    public void decreaseStimmung(int amount) {
        stimmung -= amount;
        if (stimmung < 0) stimmung = 0;
    }
    public void increaseStimmung(int amount) {
        stimmung += amount;
        if (stimmung > 100) stimmung = 100;
    }
    public void updateStimmungDisplay(Label labelStimmung) {
        labelStimmung.setText("Stimmung: " + stimmung + " / 100");
    }

    // Gewicht + Alter
    //TODO: decrease, increase, update

    public void startStateTimer(Label labelHunger) {
        Timeline stateTimer = new Timeline(
                new KeyFrame(Duration.seconds(10), e -> {
                    decreaseHunger(10);
                    updateHungerDisplay(labelHunger);
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
