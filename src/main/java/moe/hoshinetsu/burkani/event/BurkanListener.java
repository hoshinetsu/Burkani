package moe.hoshinetsu.burkani.event;

import moe.hoshinetsu.burkani.globals.Burkaner;
import moe.hoshinetsu.burkani.globals.Keys;
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
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.EXPERIENCE_BOTTLE) {
            if(item.getItemMeta() == null) return;
            ItemMeta meta = item.getItemMeta();
            if(!meta.getPersistentDataContainer().has(Keys.KEY_BURKAN)) return;
            event.setCancelled(true);
            Player player = event.getPlayer();
            if(Burkaner.isEmpty(meta)) {

            } else {
                int lvl = player.getLevel();
                float xp = player.getExp();
                if(lvl < 10) {
                    Burkaner.getInstance().setAmount(meta, xp, lvl);
                } else {
                    Burkaner.getInstance().setAmount(meta, 0, 10);
                }
            }
            item.setItemMeta(meta);
        }
    }
}
