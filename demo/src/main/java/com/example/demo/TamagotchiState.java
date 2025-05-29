package com.example.demo;

public class TamagotchiState {
    private boolean isMenuActive;
    private boolean isWeightMenuActive;

    private boolean isBabyActive;

    public int imageIndex = 0;
    public int clickCountMenu = 0;

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
}
