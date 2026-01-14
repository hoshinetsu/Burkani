package moe.hoshinetsu.burkani.globals;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public final class Burkaner {
    private static Burkaner instance;

    private final String DISPLAYNAME = ChatColor.GREEN + "Burkan";

    public ItemStack emptyBurkan(){
        ItemStack burkan = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);

        ItemMeta meta = burkan.getItemMeta();
        assert meta != null;
        meta.setDisplayName(DISPLAYNAME);
        meta.setMaxStackSize(1);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.INFINITY, 1, true);

        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_BURKAN, PersistentDataType.BOOLEAN, true);
        dc.set(Keys.KEY_EXP, PersistentDataType.FLOAT, 0f);
        dc.set(Keys.KEY_LEVELS, PersistentDataType.INTEGER, 0);

        burkan.setItemMeta(meta);
        return burkan;
    }

    public void updateLore(ItemMeta meta){
        assert meta != null;
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        try {
            float exp = dc.get(Keys.KEY_EXP, PersistentDataType.FLOAT);
            int levels = dc.get(Keys.KEY_LEVELS, PersistentDataType.INTEGER);
            if(exp > 0f || levels > 0) {
                meta.setLore(List.of(String.format("Contains %d levels and %f XP", levels, exp), "Throw to obtain them"));
            }
        } catch (NullPointerException ignored){
            ;
        }
        meta.setLore(List.of("Empty", "Throw to store up to 10 levels."));
    }

    public static Burkaner getInstance(){
        return instance;
    }

    private Burkaner(){
        instance = this;
    }

    static {
        new Burkaner();
    }
}
