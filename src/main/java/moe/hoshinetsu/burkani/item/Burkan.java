package moe.hoshinetsu.burkani.item;

import moe.hoshinetsu.burkani.PluginBurkani;
import moe.hoshinetsu.burkani.util.Keys;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Burkan {
    private final ItemStack stack;
    private final ItemMeta meta;
    private final String type;
    private int capacity;

    public Burkan(ItemStack stack) {
        this.stack = stack;
        type = "burkan";

        meta = stack.getItemMeta();
        assert meta != null;

        meta.setDisplayName(PluginBurkani.configs().getItemName(type));
        meta.setMaxStackSize(1);

        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_BURKAN, PersistentDataType.BOOLEAN, true);
        try {
            capacity = dc.get(Keys.KEY_CAPACITY, PersistentDataType.INTEGER);
        } catch (NullPointerException e) {
            capacity = PluginBurkani.configs().getCapacity();
            dc.set(Keys.KEY_CAPACITY, PersistentDataType.INTEGER, capacity);
        }
        updateLore();
    }

    public static Burkan getEmpty() {
        return new Burkan(new ItemStack(Material.EXPERIENCE_BOTTLE, 1));
    }

    public static boolean isBurkan(ItemStack item) {
        if (item == null || item.getType() != Material.EXPERIENCE_BOTTLE || item.getItemMeta() == null)
            return false;
        return item.getItemMeta().getPersistentDataContainer().has(Keys.KEY_BURKAN);
    }

    public boolean isEmpty() {
        return getExp() == 0;
    }

    public boolean isFull() {
        return getExp() >= capacity;
    }

    public void updateLore() {
        int state = (isEmpty() ? 1 : 0) + (isFull() ? 2 : 0);
        meta.setLore(PluginBurkani.configs().processLore(type, state, getExp(), capacity));
        asItemStack();
    }

    public int getExp() {
        assert meta != null;
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        try {
            int exp = dc.get(Keys.KEY_STORED, PersistentDataType.INTEGER);
            return Math.min(Math.max(exp, 0), capacity);
        } catch (NullPointerException ignored) {
            return 0;
        }
    }

    public int storeXP(int xp) {
        assert meta != null;
        if (xp < 0) xp = 0;
        int stored = Math.min(xp, capacity);
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_STORED, PersistentDataType.INTEGER, stored);
        updateLore();
        return stored;
    }

    public ItemStack asItemStack() {
        stack.setItemMeta(meta);
        return stack;
    }
}
