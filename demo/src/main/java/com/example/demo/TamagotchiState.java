package com.example.demo;

public class TamagotchiState {
    private boolean isMenuActive;
    private boolean isWeightMenuActive = false;
    private boolean isGameMenuActive;
    private boolean isSleepMenuActive = false;

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
}
