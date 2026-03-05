package dev.saperate.cigs.data;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerData {
    private int cigsSmoked = 0, respiratoryIssuesLevel = 0;

    public int getCigsSmoked() {
        return cigsSmoked;
    }

    public int getRespiratoryIssuesLevel() {
        return respiratoryIssuesLevel;
    }
    
    public void setCigsSmoked(int cigsSmoked) {
        if(cigsSmoked < 0)
            throw new RuntimeException("Amount of cigs smoked cannot be lower than 0");
        this.cigsSmoked = cigsSmoked;
    }

    public void setRespiratoryIssuesLevel(int respiratoryIssuesLevel) {
        this.respiratoryIssuesLevel = respiratoryIssuesLevel;
    }

    public static PlayerData get(PlayerEntity player) {
        return CigStateDataLoaderSaver.getPlayerState(player);
    }
}
