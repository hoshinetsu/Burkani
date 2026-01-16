package moe.hoshinetsu.burkani.util;

import moe.hoshinetsu.burkani.PluginBurkani;
import org.bukkit.NamespacedKey;

public final class Keys {
    private Keys(){
        ;
    }

    public static final NamespacedKey KEY_BURKAN = new NamespacedKey(PluginBurkani.getInstance(), "burkan");
    public static final NamespacedKey KEY_STORED = new NamespacedKey(PluginBurkani.getInstance(), "stored_xp");
    public static final NamespacedKey KEY_CAPACITY = new NamespacedKey(PluginBurkani.getInstance(), "xp_capacity");
}
