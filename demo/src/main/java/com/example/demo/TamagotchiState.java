package com.example.demo;

import java.io.Serializable;

//public class TamagotchiState implements Serializable {
  //  private static final long serialVersionUID = 1L;

public class TamagotchiState {
    public TamagotchiState() {
        // Kann leer bleiben – Gson befüllt die Felder selbst
    }

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
    private boolean isTamagotchiDead;

    private boolean isEating;
    private boolean isSleeping;

    private boolean isGameOver = false;

    public int imageIndex = 0;
    public int subMenu6Index = -1; // -1 für "noch im WeightMenü"
    public int clickCountMenu = 0;

    public int weight = 1;
    public int age = 1;

    public int hungry = 100;
    public int clean = 100;
    public int happiness = 100;
    public int health = 100;

    public LifeStage currentStage;

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

    public boolean isTamagotchiDead() {
        return isTamagotchiDead;
    }

    public void setTamagotchiDead(boolean tamagotchiDead) {
        isTamagotchiDead = tamagotchiDead;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getHungry() {
        return hungry;
    }

    public void setHungry(int hungry) {
        this.hungry = hungry;
    }

    public int getClean() {
        return clean;
    }

    public void setClean(int clean) {
        this.clean = clean;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void copyFrom(TamagotchiState other) {
        this.isEggActive = other.isEggActive;
        this.isBabyActive = other.isBabyActive;
        this.isChildActive = other.isChildActive;
        this.isTeenagerActive = other.isTeenagerActive;
        this.isAdultActive = other.isAdultActive;
        this.isTamagotchiDead = other.isTamagotchiDead;
        this.isGameOver = other.isGameOver;
        this.isStartButtonActive = other.isStartButtonActive;
        this.isEating = other.isEating;
        this.isSleeping = other.isSleeping;
        this.weight = other.weight;
        this.age = other.age;
        this.happiness = other.happiness;
        this.hungry = other.hungry;
        this.health = other.health;
        this.clean = other.clean;
        this.clickCountMenu = other.clickCountMenu;
        this.currentStage = other.currentStage;
        this.imageIndex = other.imageIndex;
        this.subMenu6Index = other.subMenu6Index;
        this.isMenuActive = other.isMenuActive;
        this.isWeightMenuActive = other.isWeightMenuActive;
        this.isGameMenuActive = other.isGameMenuActive;
        this.isEatingMenuActive = other.isEatingMenuActive;
        this.isSleepMenuActive = other.isSleepMenuActive;
    }

}
