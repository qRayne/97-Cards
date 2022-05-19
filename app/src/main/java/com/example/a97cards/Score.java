package com.example.a97cards;

public class Score {
    // Classe creer pour faciliter les entres de score dans la database
    private int score;

    public Score(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
