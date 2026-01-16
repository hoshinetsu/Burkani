package moe.hoshinetsu.burkani;

import moe.hoshinetsu.burkani.config.Configs;
import moe.hoshinetsu.burkani.event.BurkanListener;
import moe.hoshinetsu.burkani.item.Burkan;
import moe.hoshinetsu.burkani.util.Keys;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginBurkani extends JavaPlugin {

    public static PluginBurkani instance = new PluginBurkani();
    private final Configs cfg;

    public PluginBurkani() {
        instance = this;
        cfg = new Configs(instance);
    }

    public static PluginBurkani getInstance() {
        return instance;
    }

    public static Configs configs() {
        return getInstance().cfg;
    }

    private void initCrafting() {
        ShapedRecipe recipe = new ShapedRecipe(Keys.KEY_BURKAN, Burkan.getEmpty().asItemStack());

        recipe.shape(" M ", "MBM", " M ");
        recipe.setIngredient('B', Material.EXPERIENCE_BOTTLE);
        recipe.setIngredient('M', Material.COPPER_INGOT);

        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onEnable() {
        initCrafting();
        cfg.onEnable();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BurkanListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Ajde or*spuchugu!");
    }
}
