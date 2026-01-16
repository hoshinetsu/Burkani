package moe.hoshinetsu.burkani.config;

import moe.hoshinetsu.burkani.PluginBurkani;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public final class Configs {
    private PluginBurkani pb;
    private FileConfiguration fc;

    public Configs(PluginBurkani pb){
        this.pb = pb;
    }

    public void reload(){
        pb.reloadConfig();
        fc = pb.getConfig();
    }

    public void onEnable() {
        pb.saveDefaultConfig();
        reload();
    }

    public int getCapacity(){
        return fc.getInt("burkan-capacity");
    }

    public ConfigurationSection getLang(){
        return fc.getConfigurationSection("lang");
    }

    public String getItemName(){
        return getLang().getString("burkan-name");
    }
}
