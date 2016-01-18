package me.johnnywoof.tpworld;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TPWorld extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Entity) {
			if (args.length == 1) {

				World w = this.getServer().getWorld(args[0]);

				if (w != null) {

					((Entity) sender).teleport(w.getSpawnLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);

					sender.sendMessage(ChatColor.DARK_GREEN + "Teleported to world " + ChatColor.GREEN + w.getName());

				} else {

					sender.sendMessage(ChatColor.RED + "World does not exist or is not loaded.");

				}

			} else {
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Only entities (players) can use this command.");
		}
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

		List<World> worlds = this.getServer().getWorlds();
		List<String> worldNames = new ArrayList<>(worlds.size());

		for (World w : worlds)
			worldNames.add(w.getName());

		return worldNames;
	}

}
