package me.NickC0610.permissionChecker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		System.out.println("(!) Permission Checker is online");
		
		saveDefaultConfig();
	}
	
	public void onDisable() {
		System.out.print("(!) Permission Checker going offline.");
		saveConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		Player player = (Player) sender;
		
		if (args.length != 0) {
			@SuppressWarnings("deprecation")
			Player targetPlayer = player.getServer ().getPlayer(args[0]);
			
			if (targetPlayer != null) {
				if  (targetPlayer.hasPermission(this.getConfig().getString("permission"))) {
					player.sendMessage(ChatColor.GOLD + "They do have " + this.getConfig().getString("permission") + "!");
				} else {
					player.sendMessage(ChatColor.RED + "They do not have " + this.getConfig().getString("permission") + "!");
						
					}
				}
		}
		
		if (args.length == 0) {
			player.sendMessage(ChatColor.RED + "You did not specify a player!");
		}
		
		return true;
	}
}
