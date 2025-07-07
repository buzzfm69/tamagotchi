package com.example.demo.Menu;

import com.example.demo.TamagotchiState;
import javafx.scene.image.Image;

public class Menu1 {
  private final TamagotchiState tamagotchiState;
  private final Image BabyEating = new Image(getClass().getResource("/gifs/BabyEating.GIF").toExternalForm());
  private final Image ChildEating = new Image(getClass().getResource("/gifs/ChildEating.GIF").toExternalForm());
  private final Image TeenagerEating = new Image(getClass().getResource("/gifs/TeenagerEating.GIF").toExternalForm());
  private final Image AdultEating = new Image(getClass().getResource("/gifs/AdultEating.GIF").toExternalForm());
  private final Image BredGif = new Image(getClass().getResource("/gifs/Bred.GIF").toExternalForm());
  private final Image CherryGif = new Image(getClass().getResource("/gifs/Cherry.GIF").toExternalForm());
  private final Image MeatGif = new Image(getClass().getResource("/gifs/Meat.GIF").toExternalForm());
  private final Image BredPng = new Image(getClass().getResource("/images/Bred.PNG").toExternalForm());
  private final Image CherryPng = new Image(getClass().getResource("/images/Cherry.PNG").toExternalForm());
  private final Image MeatPng = new Image(getClass().getResource("/images/Meat.PNG").toExternalForm());
  private final Image Selector = new Image(getClass().getResource("/images/Selector.PNG").toExternalForm());

  private final double selectorXPosition1 = 170;
  private final double selectorYPosition1 = 173;

  private final double selectorXPosition2 = 193;
  private final double selectorYPosition2 = 173;

  private final double selectorXPosition3 = 215;
  private final double selectorYPosition3 = 173;


  public Menu1(TamagotchiState tamagotchiState) {
    this.tamagotchiState = tamagotchiState;
  }

  public Image getBabyEating() {
    return BabyEating;
  }

  public Image getChildEating() {
    return ChildEating;
  }

  public Image getTeenagerEating() {
    return TeenagerEating;
  }

  public Image getAdultEating() {
    return AdultEating;
  }

  public Image getBredGif() {
    return BredGif;
  }

  public Image getCherryGif() {
    return CherryGif;
  }

  public Image getMeatGif() {
    return MeatGif;
  }

  public Image getBredPng() {
    return BredPng;
  }

  public Image getCherryPng() {
    return CherryPng;
  }

  public Image getMeatPng() {
    return MeatPng;
  }

  public Image getSelector() {
    return Selector;
  }

  public double getSelectorXPosition1() {
    return selectorXPosition1;
  }

  public double getSelectorYPosition1() {
    return selectorYPosition1;
  }

  public double getSelectorXPosition2() {
    return selectorXPosition2;
  }

  public double getSelectorYPosition2() {
    return selectorYPosition2;
  }

  public double getSelectorXPosition3() {
    return selectorXPosition3;
  }

  public double getSelectorYPosition3() {
    return selectorYPosition3;
  }

  public Image getEatingTamagotchi() {
    switch (tamagotchiState.getCurrentStage()) {
      case BABY -> {
        return this.BabyEating;
      }
      case CHILD -> {
        return this.ChildEating;
      }
      case TEEN -> {
        return this.TeenagerEating;
      }
      case ADULT -> {
        return this.AdultEating;
      }

    }
    tamagotchiState.hungry+=10;
    return null;
  }
}
