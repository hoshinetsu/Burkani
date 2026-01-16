package moe.hoshinetsu.burkani.cmd;

import moe.hoshinetsu.burkani.PluginBurkani;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public final class CmdExecutor implements CommandExecutor {
    private final HashMap<String, ICmd> commands = new HashMap<>();

    public CmdExecutor(){
        registerCmd(new CmdReload());
    }

    private void registerCmd(ICmd command){
        commands.put(command.getName(), command);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        ICmd cmd = null;

        if(strings.length == 0) {
            commandSender.sendMessage("&2Burkani is working correctly. Check out &a/burkani help &2for available commands");
            return true;
        }

        cmd = commands.get(strings[0].trim().toLowerCase());
        if(cmd == null) {
            commandSender.sendMessage("&4Unknown command! Please refer to &e/burkani help");
            return true;
        }

        boolean executed = false;
        if(commandSender instanceof Player player){
            executed = cmd.runAsPlayer(player, command, s, strings);
            if(!executed) {
                commandSender.sendMessage("&4Insufficient permissions.");
            }
        } else {
            executed = cmd.execute(commandSender, command, s, strings);
        }

        return executed;
    }
}
