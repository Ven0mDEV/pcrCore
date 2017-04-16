package net.brightblack9911.pcrCore.basic;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import com.google.common.collect.Maps;

public class FunnyFillSlot
{
	private BukkitTask taskId;
	private Inventory inventory;
	private int[] slots;

	private ItemStack[] items = new ItemStack[] {new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4)).setName(" ").build(), new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 1)).setName(" ").build() };

	public static Map<Inventory, FunnyFillSlot> funnyInventories = Maps.newHashMap();

	public FunnyFillSlot(Inventory inventory, int[] slots) {
		this.inventory = inventory;
		this.slots = slots;
		funnyInventories.put(inventory, this);
	}

	public void start() {
		taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(CorePlugin.getInstance(), new Runnable() {

			@Override
			public void run() {
				for (int slot : slots) {
					inventory.setItem(slot, getDrawnItem());
				}
				// Bukkit.getScheduler().cancelTask(taskId.getTaskId());
			}

		}, 1, 1);
	}

	private ItemStack getDrawnItem() {
		return items[CorePlugin.getInstance().getRandom().nextInt(items.length)];
	}

	public void stop() {
		Bukkit.getScheduler().cancelTask(taskId.getTaskId());
	}

	public Inventory getInventory() {
		return inventory;
	}

	public static Map<Inventory, FunnyFillSlot> getFunnyInventories() {
		return funnyInventories;
	}
}
