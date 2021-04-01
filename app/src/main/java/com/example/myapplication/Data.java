package com.example.myapplication;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {
    private double lastUpdate;

    private double money = 0.0;
    private double delta = 0.0;

    private final List<Double> upgradeDeltas = new ArrayList<>();
    private final List<Double> upgradePrices = new ArrayList<>();
    private final List<Double> ownedUpgrades = new ArrayList<>();

    public Data() {
        this.lastUpdate = System.currentTimeMillis() / 1000.0;
    }

    public double getLastUpdate() {
        return lastUpdate;
    }

    public double getScore() {
        return score;
    }

    public double getDelta() {
        return delta;
    }

    public void setLastUpdate(double lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public List<Double> getUpgradeDeltas() {
        return upgradeDeltas;
    }

    public List<Double> getUpgradePrices() {
        return upgradePrices;
    }

    public List<Double> getOwnedUpgrades() {
        return ownedUpgrades;
    }

    /**
     * Increases the score by a certain amount.
     *
     * @param amount the amount
     */
    public void addScore(double amount) {
        this.score += amount;
    }
}