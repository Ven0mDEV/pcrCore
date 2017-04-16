package net.brightblack9911.pcrCore.listeners;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.brightblack9911.pcrCore.managers.KitManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.RandomTpManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class PlayerJoinListener implements Listener
{
	private final MessagesManager MM = MessagesManager.getInstance();
	
	private final String[] ANTYBOT_MESSAGE = new String[] { "&8####### &f&o&lANTY-BOT &8#######", " &7Wejdz ponownie na serwer.", "&8########################"};
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		Player p = ev.getPlayer();
		
		ev.setJoinMessage(Utils.formatColor(MessagesManager.getInstance().joinMsg));
		UserManager.getInstance().loadUser(p);
		UserManager.getInstance().getUser(p).setOnlineTime(System.currentTimeMillis());
		for (String messages : MM.joinMotdMsg) {
			Utils.sendMsg(p, messages);
		}
		
		if (!p.hasPlayedBefore()) {
			KitManager.getInstance().takeKit(p, KitManager.getInstance().getKitByName("START"));
			if (!MM.firstJoinMsg.equals("none")) Utils.bcMessage(MM.firstJoinMsg.replace("%player", p.getName()));

			p.kickPlayer(Utils.formatColor(StringUtils.join(ANTYBOT_MESSAGE, "\n")));
		}
		RandomTpManager.getInstance().randomTeleport(p, p.getWorld().getSpawnLocation(), 2500, 2500);
	}
}
