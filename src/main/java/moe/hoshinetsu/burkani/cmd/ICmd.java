package moe.hoshinetsu.burkani.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ICmd {
    protected final String permission;

    public ICmd(String node) {
        this.permission = node;
    }

    public static void sendMessage(CommandSender executor, Object... message) {
        String[] parsed = new String[message.length];
        for (int i = 0; i < parsed.length; i++) {
            parsed[i] = message[i].toString().replace('&', ChatColor.COLOR_CHAR);
        }
        executor.sendMessage(parsed);
    }

    protected boolean execute(CommandSender executor, Command command, String name, String[] args) {
        return true;
    }

    public boolean runAsPlayer(Player player, Command command, String name, String[] args) {
        if (player.hasPermission(permission)) {
            return execute(player, command, name, args);
        }
        return false;
    }

    public boolean runAsNonPlayer(CommandSender executor, Command command, String name, String[] args) {
        return execute(executor, command, name, args);
    }

    protected abstract String getName();
}
