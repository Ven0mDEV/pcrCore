package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.brightblack9911.pcrCore.basic.CorePlugin;
import net.brightblack9911.pcrCore.basic.Cuboid;
import net.brightblack9911.pcrCore.enums.FarmerType;
import net.brightblack9911.pcrCore.managers.CuboidManager;
import net.brightblack9911.pcrCore.managers.GUIMenuManager;
import net.brightblack9911.pcrCore.managers.ItemManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class BlockPlaceListener implements Listener
{
	public final CuboidManager cuboidManager = CuboidManager.getInstance();

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent ev) {
		final Player p = ev.getPlayer();
		Block block = ev.getBlock();

		/*if (CuboidManager.getInstance().contains(block.getLocation())) {
			if (!CuboidManager.getInstance().isInsideCuboid(p, p.getLocation())) {
				Utils.sendMsg(ev.getPlayer(), MessagesManager.getInstance().PLACE_BLOCK_IN_ZONE);
				ev.setCancelled(true);
			}
		}*/

		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().getDisplayName().equals(Utils.formatColor("&f&lSANDFARMER"))) {
				Utils.createFarmer(FarmerType.SAND, block.getLocation());
			}
			if (p.getItemInHand().getItemMeta().getDisplayName().equals(Utils.formatColor("&f&lBOYFARMER"))) {
				Utils.createFarmer(FarmerType.BOY, block.getLocation());
			}
			if (p.getItemInHand().getItemMeta().getDisplayName().equals(Utils.formatColor("&cKreator cuboida"))) {
				ev.setCancelled(true);
				if (UserManager.getInstance().getUser(p).hasCuboid()) {
					Utils.sendMsg(p, MessagesManager.getInstance().HAS_CUBOID_ALREDY);
					ev.setCancelled(true);
					return;
				}
				if (p.getItemInHand().getAmount() == 1) {
					p.setItemInHand(null);
				}

				p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);

				final Location loc = new Location(p.getLocation().getWorld(), p.getLocation().getBlockX(), p.getLocation().getY() + 80, p.getLocation().getBlockZ());

				if (!cuboidManager.contains(p.getLocation())) {

				}

				if (!cuboidManager.contains(new Location(p.getLocation().getWorld(), p.getLocation().getBlockX() + 11, p.getLocation().getY(), p.getLocation().getBlockZ() + 17))
						|| (!cuboidManager
								.contains(new Location(p.getLocation().getWorld(), p.getLocation().getBlockX() - 12, p.getLocation().getY(), p.getLocation().getBlockZ() - 11)))) {
					Utils.sendMsg(p, MessagesManager.getInstance().CREATE_CUBOID_OUT_ZONE);
					ev.setCancelled(true);
					return;
				}

				cuboidManager.paste("cuboid.schematic", loc);

				Cuboid cuboid = new Cuboid(p.getName(), p.getUniqueId());

				cuboid.setCenter(loc);

				cuboidManager.addCuboid(cuboid);

				UserManager.getInstance().getUser(p).setCuboid(cuboid);

				new BukkitRunnable() {

					@Override
					public void run() {
						// TODO: Message
						p.teleport(loc);
						p.playEffect(loc, Effect.ENDER_SIGNAL, 30);

					}
				}.runTaskLater(CorePlugin.getInstance(), 20L * 2);
			}
		}
		if (block.getType().equals(Material.ENDER_STONE) && p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().getDisplayName().equals(Utils.formatColor(ItemManager.getInstance().generator.getItemMeta().getDisplayName()))) {
				UserManager.getInstance().getUser(p).getGenerator().put(p.getName(), block.getLocation());
				GUIMenuManager.getInstance().openGeneratorGUI(p);
				return;
			}
		}
	}

}
