/**
 * 
 */
package net.codesquad.backpack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.codesquad.backpack.commands.BackPackReloadCommand;
import net.codesquad.backpack.commands.GiveBackPackCommand;
import net.codesquad.backpack.listeners.InventoryCloseListener;
import net.codesquad.backpack.listeners.PlayerInteractListener;
import net.codesquad.backpack.managers.BackPackManager;
import net.codesquad.backpack.managers.MessagesManager;
import net.codesquad.backpack.managers.SQLiteManager;

/**
 * @author nano3.
 *
 */
public class BackPackPlugin extends JavaPlugin
{ 
	private static BackPackPlugin instance;
	
	@Override
	public void onEnable() { 
		instance = this; 
		
		saveDefaultConfig();
		
		MessagesManager.getInstance();
		SQLiteManager.getInstance();
		BackPackManager.getInstance(); 
		
		getCommand("givebackpack").setExecutor(new GiveBackPackCommand()); 
		getCommand("backpackreload").setExecutor(new BackPackReloadCommand());
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new PlayerInteractListener(), this);
		pluginManager.registerEvents(new InventoryCloseListener(), this);
	}
	
	public static BackPackPlugin getInstance() {
		return instance;
	}
}
