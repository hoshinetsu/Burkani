package moe.hoshinetsu.burkani.config;

import moe.hoshinetsu.burkani.PluginBurkani;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public final class Configs {
    private final PluginBurkani pb;
    private FileConfiguration fc;

    public Configs(PluginBurkani pb) {
        this.pb = pb;
    }

    private String processColors(String s) {
        if (s == null) return "<missing config>";
        return s.replace('&', ChatColor.COLOR_CHAR);
    }

    public void reload() {
        pb.reloadConfig();
        fc = pb.getConfig();
    }

    public void onEnable() {
        pb.saveDefaultConfig();
        reload();
    }

    public int getCapacity() {
        return fc.getInt("burkan-capacity", 160);
    }

    public ConfigurationSection getLang() {
        return fc.getConfigurationSection("item");
    }

    public String getItemName() {
        return processColors(getLang().getString("burkan-name"));
    }

    public List<String> processLore(int state, int stored, int capacity) {
        String stateLore = "lore-";
        switch (state) {
            case 1: {
                stateLore += "empty";
                break;
            }
            case 2: {
                stateLore += "full";
                break;
            }
            default: {
                stateLore += "stored";
                break;
            }
        }
        List<String> lore = getLang().getStringList(stateLore);
        for (int i = 0; i < lore.size(); i++) {
            String line = processColors(lore.get(i));
            line = line.replace("%stored%", Integer.toString(stored));
            line = line.replace("%capacity%", Integer.toString(capacity));
            lore.set(i, line);
        }
        return lore;
    }
}
