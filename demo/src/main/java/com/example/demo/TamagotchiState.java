package com.example.demo;

public class TamagotchiState {
    public boolean isMenuActive;
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
}
