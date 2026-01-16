package moe.hoshinetsu.burkani.item;

import moe.hoshinetsu.burkani.PluginBurkani;
import moe.hoshinetsu.burkani.util.Keys;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class Burkan {
    private ItemStack stack;
    private ItemMeta meta;
    private int capacity;

    public Burkan(ItemStack stack) {
        this.stack = stack;
        meta = stack.getItemMeta();
        try {
            capacity = meta.getPersistentDataContainer().get(Keys.KEY_CAPACITY, PersistentDataType.INTEGER);
        } catch (NullPointerException e) {
            capacity = PluginBurkani.configs().getCapacity();
        }
    }

    public static boolean isBurkan(ItemStack item) {
        if (item == null || item.getType() != Material.EXPERIENCE_BOTTLE || item.getItemMeta() == null)
            return false;
        return item.getItemMeta().getPersistentDataContainer().has(Keys.KEY_BURKAN);
    }

    public ItemStack asItemStack() {
        stack.setItemMeta(meta);
        return stack;
    }

    public int storeXP(int xp) {
        assert meta != null;
        if(xp < 0) xp = 0;
        int stored = Math.min(xp, capacity);
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_STORED, PersistentDataType.INTEGER, stored);
        asItemStack(); // update itemstack
        return xp - stored;
    }

    public static ItemStack getEmpty() {
        ItemStack burkan = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta meta = burkan.getItemMeta();
        assert meta != null;
        meta.setDisplayName(PluginBurkani.configs().getItemName());
        meta.setMaxStackSize(1);
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_BURKAN, PersistentDataType.BOOLEAN, true);
        burkan.setItemMeta(meta);
        return burkan;
    }

    private void nameItem(String s1, String s2) {
        meta.setLore(List.of(
                ChatColor.DARK_GREEN + "Capacity: 160 XP",
                ChatColor.DARK_GREEN + s1,
                ChatColor.DARK_GREEN + s2));
        asItemStack();
    }

    public int getExp() {
        assert meta != null;
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        try {
            int exp = dc.get(Keys.KEY_STORED, PersistentDataType.INTEGER);
            return Math.min(Math.max(exp, 0), capacity);
        } catch (NullPointerException ignored){
            ;
        }
        return 0;
    }

    public boolean isEmpty() {
        return getExp() == 0;
    }

    public boolean isFull(){
        return getExp() >= capacity;
    }

    public void updateLore() {
        try {
            int xp = getExp();
            if(xp > 0) {
                nameItem(
                        String.format("Contains %d/160 XP", xp),
                        "Throw to obtain");
                return;
            }
        } catch (NullPointerException ignored){
            ;
        }
        nameItem(
                "Currently Empty",
                "Throw to store");
        asItemStack();
    }
}
