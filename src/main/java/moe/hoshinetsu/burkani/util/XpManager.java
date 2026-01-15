package moe.hoshinetsu.burkani.util;

import org.bukkit.entity.Player;

public final class XpManager {

    private static int getXpToNextLevel(int level) {
        if (level <= 15) return 2 * level + 7;
        if (level <= 30) return 5 * level - 38;
        return 9 * level - 158;
    }

    public static int getPlayerTotalXp(Player player) {
        int level = player.getLevel();
        int xpFromLevels = 0;

        for (int i = 0; i < level; i++) {
            xpFromLevels += getXpToNextLevel(i);
        }

        int xpFromBar = Math.round(player.getExp() * getXpToNextLevel(level));

        return xpFromLevels + xpFromBar;
    }

    public static void setPlayerTotalXp(Player player, int newTotalXp) {
        if (newTotalXp < 0) newTotalXp = 0;

        player.setExp(0);
        player.setLevel(0);
        player.setTotalExperience(0);

        int currentLevel = 0;
        int xpRemaining = newTotalXp;

        while (xpRemaining >= getXpToNextLevel(currentLevel)) {
            xpRemaining -= getXpToNextLevel(currentLevel);
            currentLevel++;
        }

        player.setTotalExperience(newTotalXp);
        player.setLevel(currentLevel);

        float percent = (float) xpRemaining / (float) getXpToNextLevel(currentLevel);
        player.setExp(percent);
    }
}