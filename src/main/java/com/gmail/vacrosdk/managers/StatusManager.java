package com.gmail.vacrosdk.managers;

import com.gmail.vacrosdk.types.DifficultyType;


public class StatusManager {
    private boolean isStarted;
    private boolean isOpen;
    private DifficultyType currentDifficulty;

    public void openGame() {
        isOpen = true;
    }

    public void closeGame() {
        isOpen = false;
    }

    public void startGame() {
        isStarted = true;
        isOpen = false;
        currentDifficulty = DifficultyType.EASY;
    }

    public void stopGame() {
        isStarted = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public DifficultyType getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void increaseDifficulty() {
        if(currentDifficulty == DifficultyType.EASY) {
            currentDifficulty = DifficultyType.MEDIUM;
        }else if(currentDifficulty == DifficultyType.MEDIUM) {
            currentDifficulty = DifficultyType.HARD;
        }
    }

    public void reset() {
        currentDifficulty = DifficultyType.EASY;
        isStarted = false;
        isOpen = false;
    }
}
