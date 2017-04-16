package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.brightblack9911.pcrCore.basic.Teleport;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class PlayerMoveListener implements Listener
{

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev) {
		Player p = ev.getPlayer();
		
		Location from = ev.getFrom();
		Location to = ev.getTo();
		
		if (from == null || to == null) return;
		if (from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ()) return;
		
		if (UserManager.getInstance().getUser(ev.getPlayer()).isFreeze()) {
			Utils.sendMsg(p, "&cJestes sprawdzany!");
			ev.setTo(ev.getFrom());
			return;
		}

		if (Teleport.getTeleportsPlayer().contains(p.getName())) {
			Teleport.getTeleportsPlayer().remove(p.getName());
			Utils.sendMsg(p, MessagesManager.getInstance().TELEPORT_CANCELED);
		}
	}

}
