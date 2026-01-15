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

    public int getLevel(ItemMeta meta){
        assert meta != null;
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        try {
            int lvl = dc.get(Keys.KEY_LEVELS, PersistentDataType.INTEGER);
            return Math.max(lvl, 0);
        } catch (NullPointerException ignored){
            ;
        }
        return 0;
    }

    public boolean isEmpty(ItemMeta meta) {
        return getLevel(meta) > 0;
    }

    public ItemStack getBurkan(){
        ItemStack burkan = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);

        ItemMeta meta = burkan.getItemMeta();
        assert meta != null;
        meta.setDisplayName(DISPLAYNAME);
        meta.setMaxStackSize(1);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.INFINITY, 1, true);

        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_BURKAN, PersistentDataType.BOOLEAN, true);
        setAmount(meta, 0f, 0);

        burkan.setItemMeta(meta);
        return burkan;
    }

    public void setAmount(ItemMeta meta, float xp, int lvl){
        if(xp < 0) xp = 0;
        if(lvl < 0) lvl = 0;
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_EXP, PersistentDataType.FLOAT, xp);
        dc.set(Keys.KEY_LEVELS, PersistentDataType.INTEGER, lvl);
        updateLore(meta);
    }

    public void updateLore(ItemMeta meta){
        assert meta != null;
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        try {
            int level = getLevel(meta);
            if(level > 0) {
                meta.setLore(List.of(String.format("Contains %d levels", level), "Throw to obtain them"));
                return;
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
