package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.brightblack9911.pcrCore.basic.CorePlugin;
import net.brightblack9911.pcrCore.basic.Cuboid;
import net.brightblack9911.pcrCore.managers.CuboidManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.RandomTpManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.LocationUtils;
import net.brightblack9911.pcrCore.utils.Utils;

public class PlayerInteractListener implements Listener
{
	public final CuboidManager cuboidManager = CuboidManager.getInstance();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		// lewoklik na cuboidzie
		if (ev.getAction() == Action.LEFT_CLICK_BLOCK && CuboidManager.getInstance().contains(ev.getClickedBlock().getLocation())) {
			if (!CuboidManager.getInstance().isInsideCuboid(ev.getPlayer(), ev.getClickedBlock().getLocation())) {
				Utils.sendMsg(ev.getPlayer(), MessagesManager.getInstance().BREAK_BLOCK_IN_ZONE);
				ev.setCancelled(true);
			}
		}
		// prawoklik na cuboidzie
		if (ev.getAction() == Action.RIGHT_CLICK_BLOCK && CuboidManager.getInstance().contains(ev.getClickedBlock().getLocation())) {
			if (!CuboidManager.getInstance().isInsideCuboid(ev.getPlayer(), ev.getClickedBlock().getLocation())) {
				Utils.sendMsg(ev.getPlayer(), MessagesManager.getInstance().PLACE_BLOCK_IN_ZONE);
				ev.setCancelled(true);
			}
		}
		// final Player p = (Player) ev.getPlayer();
		// if (ev.getAction() == Action.RIGHT_CLICK_BLOCK /*&&
		// ev.getClickedBlock().getType().equals(Material.STONE_BUTTON)*/) {
		// if (ev.getAction() == Action.RIGHT_CLICK_BLOCK &&
		// p.getItemInHand().hasItemMeta()) {
		// if
		// (p.getItemInHand().getItemMeta().getDisplayName().equals(Utils.formatColor("&cKreator
		// cuboida"))) {
		// ev.setCancelled(true);
		// if (UserManager.getInstance().getUser(p).hasCuboid()) {
		// Utils.sendMsg(p, MessagesManager.getInstance().HAS_CUBOID_ALREDY);
		// ev.setCancelled(true);
		// return;
		// }
		// if (p.getItemInHand().getAmount() == 1) {
		// p.setItemInHand(null);
		// }
		//
		// p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
		//
		// final Location loc = new Location(p.getLocation().getWorld(),
		// p.getLocation().getBlockX(), p.getLocation().getY() + 80,
		// p.getLocation().getBlockZ());
		//
		// if (!cuboidManager.contains(p.getLocation())) {
		//
		// }
		//
		// if (!cuboidManager
		// .contains(new Location(p.getLocation().getWorld(),
		// p.getLocation().getBlockX() + 11, p.getLocation().getY(),
		// p.getLocation().getBlockZ() + 17))
		// || (!cuboidManager.contains(
		// new Location(p.getLocation().getWorld(), p.getLocation().getBlockX()
		// - 12, p.getLocation().getY(), p.getLocation().getBlockZ() - 11)))) {
		// Utils.sendMsg(p,
		// MessagesManager.getInstance().CREATE_CUBOID_OUT_ZONE);
		// ev.setCancelled(true);
		// return;
		// }
		//
		// cuboidManager.paste("cuboid.schematic", loc);
		//
		// Cuboid cuboid = new Cuboid(p.getName(), p.getUniqueId());
		//
		// cuboid.setCenter(loc);
		//
		// cuboidManager.addCuboid(cuboid);
		//
		// UserManager.getInstance().getUser(p).setCuboid(cuboid);
		//
		// new BukkitRunnable() {
		//
		// @Override
		// public void run() {
		// // TODO: Message
		// p.teleport(loc);
		// p.playEffect(loc, Effect.ENDER_SIGNAL, 30);
		//
		// }
		// }.runTaskLater(CorePlugin.getInstance(), 20L * 2);
		// }
		// }

		System.out.println(RandomTpManager.getInstance().getLocations().size());
		if (LocationUtils.isRandomTpBlock(Material.ENDER_STONE, ev.getClickedBlock())) {
			System.out.println(RandomTpManager.getInstance().getLocations().size());
			RandomTpManager.getInstance().randomTeleport(ev.getPlayer(), ev.getClickedBlock().getWorld().getSpawnLocation(), -2500, 2500);

		}
	}
}
