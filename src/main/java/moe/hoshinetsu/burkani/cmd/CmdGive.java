package moe.hoshinetsu.burkani.cmd;

import moe.hoshinetsu.burkani.item.Burkan;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGive extends ICmd {

    public CmdGive() {
        super("burkani.give");
    }

    private boolean executeAs(CommandSender sender, String[] args) {
        Player player = Bukkit.getPlayer(args[2].trim());
        if (player == null) {
            sendMessage(sender, "&4Player not found!");
            return true;
        }
        return execute(sender, player, args);
    }

    protected boolean execute(CommandSender sender, Player target, String[] args) {
        int xp = 0;
        if (args.length >= 2) {
            try {
                xp = Integer.parseInt(args[1]);
            } catch (NumberFormatException ignored) {
            }
        }
        sendMessage(sender, String.format("&2Given Burkan with &a%d XP &2to &a%s&2.", xp, target.getName()));
        Burkan b = Burkan.getEmpty();
        b.storeXP(xp);
        target.getInventory().addItem(b.asItemStack());
        return true;
    }

    @Override
    public boolean runAsNonPlayer(CommandSender executor, Command command, String name, String[] args) {
        if (args.length >= 3) {
            return executeAs(executor, args);
        }
        sendMessage(executor, "&4Need to provide the target when ran as non-player. Consult &c/burkani help");
        return false;
    }

    @Override
    public boolean runAsPlayer(Player player, Command command, String name, String[] args) {
        if (player.hasPermission(permission)) {
            if (args.length >= 3) {
                return executeAs(player, args);
            }
            return execute(player, player, args);
        }
        return false;
    }

    @Override
    public String getName() {
        return "give";
    }
}