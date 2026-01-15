package moe.hoshinetsu.burkani.util;

import moe.hoshinetsu.burkani.PluginBurkani;
import org.bukkit.NamespacedKey;

public final class Keys {
    private Keys(){
        ;
    }

    public static final NamespacedKey KEY_BURKAN = new NamespacedKey(PluginBurkani.getInstance(), "burkan");
    public static final NamespacedKey KEY_TEXP = new NamespacedKey(PluginBurkani.getInstance(), "experience");
}
