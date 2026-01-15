package moe.hoshinetsu.burkani.event;

import moe.hoshinetsu.burkani.util.Burkaner;
import moe.hoshinetsu.burkani.util.Keys;
import moe.hoshinetsu.burkani.util.XpManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BurkanListener implements Listener {
    @EventHandler
    public void onBottleThrow(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.EXPERIENCE_BOTTLE || item.getItemMeta() == null)
            return;
        ItemMeta meta = item.getItemMeta();
        if (!meta.getPersistentDataContainer().has(Keys.KEY_BURKAN))
            return;
        event.setCancelled(true);

        Burkaner b = Burkaner.getInstance();
        Player player = event.getPlayer();
        int xp = XpManager.getPlayerTotalXp(player);

        if (b.isEmpty(meta)) {
            b.setAmount(meta, Math.min(xp, 160));
            xp = Math.max(0, xp - 160);
            XpManager.setPlayerTotalXp(player, xp);
        } else {
            xp += b.getExp(meta);
            XpManager.setPlayerTotalXp(player, xp);
            b.setAmount(meta, 0);
        }

        item.setItemMeta(meta);
    }
}