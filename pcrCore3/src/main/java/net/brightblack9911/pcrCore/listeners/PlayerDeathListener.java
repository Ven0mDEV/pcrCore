package net.brightblack9911.pcrCore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.brightblack9911.pcrCore.utils.Utils;

public class PlayerDeathListener implements Listener
{
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent ev) {
		Player p = ev.getEntity();
		
		p.getWorld().strikeLightningEffect(p.getLocation());
		
		p.getWorld().dropItemNaturally(p.getLocation(), Utils.getSkull(p.getName()));
	}
}
