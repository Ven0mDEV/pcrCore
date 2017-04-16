package net.brightblack9911.pcrCore.listeners;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import net.brightblack9911.pcrCore.basic.CorePlugin;
import net.brightblack9911.pcrCore.basic.DropItem;
import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.managers.DropManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.ItemUtils;
import net.brightblack9911.pcrCore.utils.Utils;

public class BlockBreakListener implements Listener
{
	public final Material[] BLOCKED_BLOCKS = new Material[] { Material.IRON_ORE, Material.GOLD_ORE, Material.EMERALD_ORE, Material.DIAMOND_ORE };

	@EventHandler
	public void onBlockBreak(BlockBreakEvent ev) {
		final Block block = ev.getBlock();
		final Block under = block.getLocation().subtract(0, 1, 0).getBlock();

		/*if (CuboidManager.getInstance().contains(block.getLocation())) {
			if (!CuboidManager.getInstance().isInsideCuboid(ev.getPlayer(), ev.getPlayer().getLocation())) {
				Utils.sendMsg(ev.getPlayer(), MessagesManager.getInstance().BREAK_BLOCK_IN_ZONE);
				ev.setCancelled(true);
			}
		}*/

		if (block.getType() == Material.ENDER_STONE) {
			block.getLocation().add(0, 1, 0).getBlock().setType(Material.AIR);
		}

		if (under.getType() == Material.ENDER_STONE) {
			if (block.getType() == Material.STONE) {
				new BukkitRunnable() {
					public void run() {
						if (under.getType() != Material.ENDER_STONE) return;
						block.setType(Material.STONE);
					}
				}.runTaskLater(CorePlugin.getInstance(), 10L);
			}

			if (ev.getBlock().getType() == Material.OBSIDIAN) {
				new BukkitRunnable() {
					public void run() {
						if (under.getType() != Material.ENDER_STONE) return;
						block.setType(Material.OBSIDIAN);
					}
				}.runTaskLater(CorePlugin.getInstance(), 10L);
			}

		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onBlockBreakDrop(final BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}

		Player p = event.getPlayer();
		Block b = event.getBlock();
		User u = UserManager.getInstance().getUser(p);

		if (b.getType() == Material.MOB_SPAWNER) {
			return;
		}

		List<ItemStack> itemsToAdd = Utils.getDrops(b, event.getPlayer().getItemInHand(), p);

		final Material tool = p.getItemInHand().getType();

		if (!p.getGameMode().equals(GameMode.CREATIVE)) {
			for (Material blocked : BLOCKED_BLOCKS) {
				if (b.getType().equals(blocked)) {
					Utils.sendMsg(p, MessagesManager.getInstance().BLOCK_BLOCKED);
					event.getBlock().setType(Material.AIR);
					event.setCancelled(true);
					return;
				}
			}

			int playerExp = event.getExpToDrop();
			if (b.getType() == Material.STONE) {
				p.giveExp(5);
			}
			event.setExpToDrop(0);
			for (DropItem drop : DropManager.getInstance().getDrops()) {
				double chance_drop = drop.getChance();

				final Material drop_material = drop.getMaterial();
				final Material dropFrom = drop.getDropFrom();

				final List<Material> toolsList = drop.getTools();

				final boolean heightTurnOn = drop.getHeightStatus();
				final boolean fortune = drop.getFortuneStatus();
				final boolean toolsTurnOn = drop.getToolsStatus();
				final boolean drop_with_name = drop.getDropWithName();

				final int heightMin = drop.getMinHeight();
				final int heightMax = drop.getMaxHeight();
				final int amountMin = drop.getAmountMin();
				int amountMax = drop.getAmountMax();

				if (!dropFrom.equals(b.getType())) continue;

				if (p.hasPermission("drop.vip")) chance_drop *= 2;

				if (!Utils.chance(chance_drop)) continue;

				if (heightTurnOn) {
					if (b.getLocation().getY() > heightMin) {
						continue;
					}
					if (b.getLocation().getY() > heightMax) {
						continue;
					}
				}

				if (toolsTurnOn == true && !toolsList.contains(tool)) continue;

				if (drop.isDisabled(p.getUniqueId())) break;

				final int ench = p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

				if (fortune && ench > 0) amountMax += ench;

				playerExp += (int) 3;

				final int amount = Utils.randInt(amountMin, amountMax);

				ItemStack derop = new ItemStack(drop_material, amount);

				// block.getLocation().getWorld().dropItemNaturally(block.getLocation(),
				// derop);
				itemsToAdd.add(derop);

				u.getDropStat().put(drop, u.getDropStat().get(drop) + 1);
				// break;
			}
			Utils.recalculateDurability(p, p.getItemInHand());
			ItemUtils.giveToInventory(p, (ItemStack[]) itemsToAdd.toArray(new ItemStack[itemsToAdd.size()]));
			b.setType(Material.AIR);

		}
	}

}
