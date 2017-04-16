package net.brightblack9911.pcrCore.listeners;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.google.common.collect.Maps;

import net.brightblack9911.pcrCore.managers.ChatManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class AsyncPlayerChatListener implements Listener
{
	private final Map<String, Long> COOLDOWN = Maps.newHashMap();

	private final long COOLDOWN_TIME = 5;

	private final String FORMAT = "{TAG} {PREFIX}{PLAYER}&8: &f{SUFFIX}{MESSAGE}";

	@EventHandler
	public void AsyncPlayerChat(AsyncPlayerChatEvent ev) {
		String msg = ev.getMessage();
		Player p = ev.getPlayer();

		// ## cooldown chat ##
		if (COOLDOWN.containsKey(p.getName())) {
			if (!Utils.canUse(COOLDOWN.get(p.getName()), COOLDOWN_TIME * 1000) && !p.hasPermission("core.chat.cooldown.bypass")) {
				Utils.sendMsg(p, MessagesManager.getInstance().NEXT_MESSAGE_FOR.replace("%time",
						Long.toString(COOLDOWN_TIME - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - COOLDOWN.get(p.getName())))));
				ev.setCancelled(true);
				return;
			}
			COOLDOWN.put(p.getName(), System.currentTimeMillis());
		}

		COOLDOWN.put(p.getName(), System.currentTimeMillis());
		// ##

		// ## formatting chat ##
		PermissionUser userpex = PermissionsEx.getUser(p.getName());

		String result = "";

		result = FORMAT.replace("{PREFIX}", userpex.getPrefix() != null ? userpex.getPrefix() : "").replace("{SUFFIX}", userpex.getSuffix() != null ? userpex.getSuffix() : "")
				.replace("{PLAYER}", "%1$s").replace("{MESSAGE}", p.hasPermission("core.chatcolor") ? Utils.formatColor(msg) : "%2$s");

		ev.setFormat(Utils.formatColor(result));

		if (p.hasPermission("core.chatcolor")) {
			ev.setMessage(Utils.formatColor(msg));
		}

		if (msg.endsWith("?") || msg.endsWith(".") || msg.endsWith("!") || msg.endsWith("..") || msg.endsWith("...")) return;
		ev.setMessage(msg + ".");
		// ##

		if (!ChatManager.getInstance().getChatStatus()) {
			Utils.sendMsg(p, "&4Blad: &cChat jest aktualnie wylaczony!");
			ev.setCancelled(true);
		}

		// ## sound send message on chat ##
		for (Player pe : Bukkit.getOnlinePlayers()) {
			pe.playSound(pe.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
		}
	}

}
