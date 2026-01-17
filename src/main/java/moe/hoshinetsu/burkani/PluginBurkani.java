package moe.hoshinetsu.burkani;

import moe.hoshinetsu.burkani.cmd.RootCmd;
import moe.hoshinetsu.burkani.config.Configs;
import moe.hoshinetsu.burkani.event.BurkanListener;
import moe.hoshinetsu.burkani.item.Burkan;
import moe.hoshinetsu.burkani.util.Keys;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PluginBurkani extends JavaPlugin {

    public static PluginBurkani instance;
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

    @Override
    public void onEnable() {
        cfg.onEnable();

        ShapedRecipe recipe = new ShapedRecipe(Keys.KEY_BURKAN, Burkan.getEmpty().asItemStack());
        recipe.shape(" M ", "MBM", " M ");
        recipe.setIngredient('B', Material.EXPERIENCE_BOTTLE);
        recipe.setIngredient('M', Material.COPPER_INGOT);
        Bukkit.addRecipe(recipe);

        getServer().getPluginManager().registerEvents(new BurkanListener(), this);
        Objects.requireNonNull(getCommand("burkani")).setExecutor(new RootCmd());
    }

    @Override
    public void onDisable() {
        System.out.println("Ajde or*spuchugu!");
    }
}
