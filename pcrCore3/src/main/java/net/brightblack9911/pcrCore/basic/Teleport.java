package net.brightblack9911.pcrCore.basic;

import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class Teleport
{
	private Player p;
	private Location loc;
	private BukkitTask taskId;
	private int taskId2;
	private int lvl = 7;

	private final MessagesManager MM = MessagesManager.getInstance();

	private static final Set<String> TELEPORT = Sets.newHashSet();
	private final Map<Player, Integer> LAST_EXP = Maps.newHashMap();

	public Teleport(Player p, Location loc) {
		this.p = p;
		this.loc = loc;

		teleport();
	}

	private void teleport() {
		final User u = UserManager.getInstance().getUser(this.p);
		if (this.p.hasPermission("core.teleport.bypass")) {
			u.setLastLocation(p.getLocation());
			this.p.teleport(this.loc);
			Utils.sendMsg(p, MM.TELEPORT_SUCCESS);
			return;
		}
		TELEPORT.add(p.getName());
		Utils.sendMsg(p, MM.WAIT_ON_TELEPORT);
		lvlTimer();
		taskId = Bukkit.getScheduler().runTaskLater(CorePlugin.getInstance(), new Runnable() {

			@Override
			public void run() {
				if (TELEPORT.contains(p.getName())) {
					if (!p.isOnline()) {
						TELEPORT.remove(p.getName());
					}
					u.setLastLocation(p.getLocation());
					p.teleport(loc);
					Utils.sendMsg(p, MM.TELEPORT_SUCCESS);
					TELEPORT.remove(p.getName());
					Bukkit.getScheduler().cancelTask(taskId.getTaskId());
				}
				Bukkit.getScheduler().cancelTask(taskId.getTaskId());
			}

		}, 7 * 20L);
	}

	private int ticks = 0;
	private int seconds = 0;

	private void lvlTimer() {
		LAST_EXP.put(p, p.getLevel());
		p.setExp(1F);
		p.setLevel(lvl);
		taskId2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(CorePlugin.getInstance(), new Runnable() {
			@Override
			public void run() {
				ticks += 1;
				if (ticks == 20) {
					seconds += 1;
					lvl--;
					p.setLevel(lvl);
					ticks = 0;
					p.setExp(1F);

					switch (seconds) {
						case 3:
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1.33f);
							break;
						case 2:
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1.66f);
							break;
						case 1:
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 2f);
							break;

						default:
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
							break;
					}
				}
				p.setExp(p.getExp() - (1F / 18));
				if (seconds == 7) {
					Bukkit.getScheduler().cancelTask(taskId2);
					p.setLevel(LAST_EXP.get(p));
					LAST_EXP.remove(p);
				}
			}

		}, 0L, 1L);
	}

	public static Set<String> getTeleportsPlayer() {
		return TELEPORT;
	}
}
