package me.reverser.tablist.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.reverser.tablist.controller.MessageController;
import me.reverser.tablist.controller.TablistController;

public class TablistExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length >= 1) {
			if(args[0].equalsIgnoreCase("reload")) {
				if(sender.hasPermission(cmd.getPermission())) {
					TablistController.getInstance().reload();
					sender.sendMessage(MessageController.getPrefix() + MessageController.getColorSuccess() + " Tabliste aktualisiert");
				}
				else {
					MessageController.sendPermissionDenial(sender);
				}
			}
			else {
				MessageController.help(sender);
			}
		}
		else {
			MessageController.help(sender);
		}
		
		return true;
	}

}
