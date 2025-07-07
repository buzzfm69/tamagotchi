package com.example.demo;

import java.io.Serializable;

//public class TamagotchiState implements Serializable {
  //  private static final long serialVersionUID = 1L;

public class TamagotchiState {
    private boolean isMenuActive;
    private boolean isWeightMenuActive = false;
    private boolean isGameMenuActive;
    private boolean isEatingMenuActive;
    private boolean isSleepMenuActive = false;
    private boolean isStartButtonActive;

    private  boolean isEggActive;
    private boolean isBabyActive;
    private boolean isChildActive;
    private boolean isTeenagerActive;
    private boolean isAdultActive;

    private boolean isEating;
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

    public boolean isEatingMenuActive() {
        return isEatingMenuActive;
    }

    public void setEatingMenuActive(boolean eatingMenuActive) {
        isEatingMenuActive = eatingMenuActive;
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

    public boolean isEating() {
        return isEating;
    }

    public void setEating(boolean eating) {
        isEating = eating;
    }
}
