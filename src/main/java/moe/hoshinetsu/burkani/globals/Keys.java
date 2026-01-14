package moe.hoshinetsu.burkani.globals;

import moe.hoshinetsu.burkani.PluginBurkani;
import org.bukkit.NamespacedKey;

public final class Keys {
    private Keys(){
        ;
    }

    public static final NamespacedKey KEY_BURKAN = new NamespacedKey(PluginBurkani.getInstance(), "burkan");
    public static final NamespacedKey KEY_EXP = new NamespacedKey(PluginBurkani.getInstance(), "exp");
    public static final NamespacedKey KEY_LEVELS = new NamespacedKey(PluginBurkani.getInstance(), "levels");
}
