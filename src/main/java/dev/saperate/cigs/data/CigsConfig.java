package dev.saperate.cigs.data;

import dev.saperate.cigs.Cigs;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Cigs.MODID)
public class CigsConfig implements ConfigData {
    private int numberOfCigsImmune = 16;
    private float respiratoryIssuesIncreasePercentage = 3;
    private int maxRespiratoryIssuesLevel = 4;
    private float respiratoryIssuesWeightMultiplier = 1;

    public int getNumberOfCigsImmune() {
        return numberOfCigsImmune;
    }
    
    public float getRespiratoryIssuesIncreasePercentage() {
        return respiratoryIssuesIncreasePercentage;
    }

    public int getMaxRespiratoryIssuesLevel() {
        return maxRespiratoryIssuesLevel;
    }

    public float getRespiratoryIssuesWeightMultiplier() {
        return respiratoryIssuesWeightMultiplier;
    }
}