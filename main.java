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
		
		if (!player.hasPermission("permission.check")) {
			player.sendMessage(ChatColor.RED + "You do not have permission to run this command.");
			return true;
		}
		
		if (args.length == 0) {
			player.sendMessage(ChatColor.RED + "You did not specify a player!");
			return true;
		}
		
		@SuppressWarnings("deprecation")
		Player targetPlayer = player.getServer().getPlayer(args[0]);
		
		if (targetPlayer == null) {
			player.sendMessage(ChatColor.RED + "Player can not be found.");
			return true;
		}
		
		if (args[1].contentEquals("")) {
			if (targetPlayer.hasPermission(this.getConfig().getString("permission"))) {
				player.sendMessage(ChatColor.GOLD + targetPlayer.getDisplayName() + " has the permission: '" + ChatColor.GREEN + this.getConfig().getString("permission") + ChatColor.WHITE + "'");
			} else {
				player.sendMessage(ChatColor.RED + targetPlayer.getDisplayName() + " does not have permission: ' " + ChatColor.GREEN + this.getConfig().getString("permission") + ChatColor.WHITE + "'");
			}
			
			return true;
		}
		
		if (targetPlayer.hasPermission(args[1])) {
			player.sendMessage(ChatColor.GOLD + targetPlayer.getDisplayName() + " has the permission: '" + ChatColor.GREEN + args[1] + ChatColor.WHITE + "'");
		} else {
			player.sendMessage(ChatColor.RED + targetPlayer.getDisplayName() + " does not have permission: ' " + ChatColor.GREEN + args[1] + ChatColor.WHITE + "'");
		}
		
		return true;
	}
}
