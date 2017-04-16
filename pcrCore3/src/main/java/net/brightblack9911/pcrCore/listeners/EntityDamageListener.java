package net.brightblack9911.pcrCore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.brightblack9911.pcrCore.managers.UserManager;

public class EntityDamageListener implements Listener
{
	public void onEntityDamage(EntityDamageEvent ev) {
		if (ev.getEntity() instanceof Player) {
			Player p = (Player) ev.getEntity();
			if (UserManager.getInstance().getUser(p).isFreeze()) {
				ev.setCancelled(true);
			}
		}
	}
}
