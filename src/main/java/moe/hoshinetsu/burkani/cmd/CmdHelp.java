package moe.hoshinetsu.burkani.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdHelp extends ICmd {

    public CmdHelp() {
        super("burkani.help");
    }

    public boolean execute(CommandSender executor, Command command, String name, String[] args) {
        sendMessage(executor, "&2=== &aAll commands for Burkani &2===");
        sendMessage(executor, "&2Reload plugin configuration: &a/burkani reload");
        sendMessage(executor, "&2Obtain the Burkan Item: &a/burkani give <amount of xp> <to player>");
        sendMessage(executor, "&2Arguments marked <like this> are optional. Skip them to give an empty Burkan to yourself.");
        return true;
    }

    @Override
    public String getName() {
        return "help";
    }
}
