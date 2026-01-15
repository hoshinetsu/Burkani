package moe.hoshinetsu.burkani;

import moe.hoshinetsu.burkani.event.BurkanListener;
import moe.hoshinetsu.burkani.globals.Keys;
import moe.hoshinetsu.burkani.globals.Burkaner;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;


public final class PluginBurkani extends JavaPlugin {

    public static PluginBurkani instance;

    public PluginBurkani(){
        instance = this;
    }

    public static PluginBurkani getInstance(){
        return instance;
    }

    private void initCrafting(){
        ShapedRecipe recipe = new ShapedRecipe(Keys.KEY_BURKAN, Burkaner.getInstance().getBurkan());

        recipe.shape(" M ", "MBM", " M ");
        recipe.setIngredient('B', Material.EXPERIENCE_BOTTLE);
        recipe.setIngredient('M', Material.COPPER_INGOT);

        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onEnable() {
        initCrafting();
        getServer().getPluginManager().registerEvents(new BurkanListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Ajde or*spuchugu!");
    }
}
