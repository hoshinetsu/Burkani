package moe.hoshinetsu.burkani.cmd;

import moe.hoshinetsu.burkani.PluginBurkani;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdReload extends ICmd {

    public CmdReload() {
        super("burkani.reload");
    }

    public boolean execute(CommandSender executor, Command command, String name, String[] args){
        PluginBurkani.configs().reload();
        return true;
    }

    @Override
    public String getName() {
        return "reload";
    }
}
