package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class SaveManager {
    private static final String SAVE_FILE = "tamagotchi.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // Speichert den Zustand manuell in eine JSON-Datei
    public static void saveState(TamagotchiState state) {
        try (Writer writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(state, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lädt den Zustand manuell aus der JSON-Datei
    public static TamagotchiState loadState() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return new TamagotchiState();
        }

        try (Reader reader = new FileReader(SAVE_FILE)) {
            return gson.fromJson(reader, TamagotchiState.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new TamagotchiState();
        }
    }

}
