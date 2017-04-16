package net.brightblack9911.pcrCore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import net.brightblack9911.pcrCore.utils.Utils;

public class SignChangeListener implements Listener
{
	@EventHandler
	public void onSignChange(SignChangeEvent ev) {
		if (ev.getPlayer().hasPermission("core.coloursign")) for (int i = 0; i <= 3; i++) {
			ev.setLine(i, Utils.formatColor(ev.getLine(i)));
		}
	}
}
