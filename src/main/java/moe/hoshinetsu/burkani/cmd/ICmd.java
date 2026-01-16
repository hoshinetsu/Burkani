package moe.hoshinetsu.burkani.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ICmd {
    private final String permission;

    public ICmd(String node){
        this.permission = node;
    }

    protected abstract boolean execute(CommandSender executor, Command command, String name, String[] args);

    public boolean runAsPlayer(Player player, Command command, String name, String[] args) {
        if(player.hasPermission(permission)) {
            return execute(player, command, name, args);
        }
        return false;
    }

    protected abstract String getName();
}
