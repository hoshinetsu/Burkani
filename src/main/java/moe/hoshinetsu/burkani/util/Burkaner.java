package moe.hoshinetsu.burkani.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public final class Burkaner {
    private static Burkaner instance;

    private final String DISPLAYNAME = ChatColor.GREEN + "Exp Burkan";

    public int getExp(ItemMeta meta) {
        assert meta != null;
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        try {
            int exp = dc.get(Keys.KEY_TEXP, PersistentDataType.INTEGER);
            return Math.max(exp, 0);
        } catch (NullPointerException ignored){
            ;
        }
        return 0;
    }

    public boolean isEmpty(ItemMeta meta) {
        return getExp(meta) == 0;
    }

    public ItemStack getBurkan(){
        ItemStack burkan = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);

        ItemMeta meta = burkan.getItemMeta();
        assert meta != null;
        meta.setDisplayName(DISPLAYNAME);
        meta.setMaxStackSize(1);

        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_BURKAN, PersistentDataType.BOOLEAN, true);
        setAmount(meta, 0);

        burkan.setItemMeta(meta);
        return burkan;
    }

    public void setAmount(ItemMeta meta, int xp){
        assert meta != null;
        if(xp < 0) xp = 0;
        PersistentDataContainer dc = meta.getPersistentDataContainer();
        dc.set(Keys.KEY_TEXP, PersistentDataType.INTEGER, xp);
        updateLore(meta);
    }

    private void nameItem(ItemMeta meta, String s1, String s2){
        meta.setLore(List.of(ChatColor.DARK_GREEN + "Capacity: 160 XP", ChatColor.DARK_GREEN + s1, ChatColor.DARK_GREEN + s2));
    }

    public void updateLore(ItemMeta meta){
        assert meta != null;
        try {
            int xp = getExp(meta);
            if(xp > 0) {
                nameItem(meta,
                        String.format("Contains %d/160 XP", xp),
                        "Throw to obtain");
                return;
            }
        } catch (NullPointerException ignored){
            ;
        }
        nameItem(meta,
                "Currently Empty",
                "Throw to store");
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
