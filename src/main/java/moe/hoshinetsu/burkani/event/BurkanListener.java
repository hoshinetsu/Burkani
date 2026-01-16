package moe.hoshinetsu.burkani.event;

import moe.hoshinetsu.burkani.item.Burkan;
import moe.hoshinetsu.burkani.util.XpManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BurkanListener implements Listener {
    @EventHandler
    public void onBottleThrow(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        ItemStack item = event.getItem();

        if (!Burkan.isBurkan(item)) return;
        // is burkan!
        event.setCancelled(true);

        Burkan b = new Burkan(item);

        Player player = event.getPlayer();
        int xp = XpManager.getPlayerTotalXp(player);
        if (b.isEmpty()) {
            int stored = b.storeXP(xp);
            XpManager.setPlayerTotalXp(player, xp - stored);
        } else {
            int stored = b.getExp();
            b.storeXP(0);
            XpManager.setPlayerTotalXp(player, xp + stored);
        }
    }
}