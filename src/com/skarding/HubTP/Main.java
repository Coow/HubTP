package com.skarding.HubTP;


import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		registerConfig();
		saveDefaultConfig();
		
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
		    getLogger().info("config.yml not found, creating!");
		    saveDefaultConfig();
		}
	}
	
	@Override
	public void onDisable() {
		//getLogger().info("");
	}
	
	//Register Config
	private void registerConfig() {
			getConfig().options().copyDefaults(true);
			saveConfig();
		}	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		World curworld = (World) Bukkit.getWorld("world");
		
		String ylevel = getConfig().getString("ylevel");
		float fylevel = Float.parseFloat(ylevel);
		
		String spawnx = getConfig().getString("spawnx");
		String spawny = getConfig().getString("spawny");
		String spawnz = getConfig().getString("spawnz");
		
		float fspawnx = Float.parseFloat(spawnx);
		float fspawny = Float.parseFloat(spawny);
		float fspawnz = Float.parseFloat(spawnz);
		
		
		Location spawn = new Location(curworld, fspawnx, fspawny, fspawnz);
		
		
		Player player = event.getPlayer();
				
		if (player.getWorld() == curworld) {
			if(!player.hasPermission("hubtp.admin")) {
				if(player.getLocation().getY() <= (fylevel)){
					player.teleport(spawn);
					
				}
			}
		}
	}
	
	
	
}