package moe.hoshinetsu.burkani.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public final class RootCmd implements CommandExecutor {
    private final HashMap<String, ICmd> commands = new HashMap<>();

    public RootCmd() {
        registerCmd(new CmdHelp());
        registerCmd(new CmdReload());
        registerCmd(new CmdGive());
    }

    private void registerCmd(ICmd command) {
        commands.put(command.getName(), command);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, @org.jetbrains.annotations.NotNull String @NotNull [] strings) {
        ICmd cmd = null;

        if (strings.length == 0) {
            ICmd.sendMessage(commandSender, "&2Burkani is working correctly. Check out &a/burkani help &2for available commands");
            return true;
        }

        cmd = commands.get(strings[0].trim().toLowerCase());
        if (cmd == null) {
            ICmd.sendMessage(commandSender, "&4Unknown command! Please refer to &c/burkani help");
            return true;
        }

        boolean executed = false;
        if (commandSender instanceof Player player) {
            executed = cmd.runAsPlayer(player, command, s, strings);
            if (!executed) {
                ICmd.sendMessage(commandSender, "&4Insufficient permissions.");
            }
        } else {
            executed = cmd.runAsNonPlayer(commandSender, command, s, strings);
        }

        return executed;
    }
}
