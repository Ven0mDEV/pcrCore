package net.brightblack9911.pcrCore.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

public class ItemUtils
{
	public static void giveToInventory(Player player, ItemStack[] items) {
		Inventory inventory = player.getInventory();
		HashMap<Integer, ItemStack> notStored = inventory.addItem(items);
		for (Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
			player.getWorld().dropItemNaturally(player.getLocation(), e.getValue());
		}
	}
	
	public static void giveToInventory(Player player, ItemStack item) {
		Inventory inventory = player.getInventory();
		HashMap<Integer, ItemStack> notStored = inventory.addItem(item);
		for (Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
			player.getWorld().dropItemNaturally(player.getLocation(), e.getValue());
		}
	}

	public static int getAmountItemOfInventoryRange(Inventory inv, Material type, int rangeStart, int rangeEnd) {
		int amount = 0;
		for (int i = rangeStart; i <= rangeEnd; i++) {
			ItemStack item2 = inv.getItem(i);
			if (item2 == null || item2.getType() != type) continue;
			amount += inv.getItem(i).getAmount();
		}
		return amount;

	}

	public static List<String> getListOfItems(ItemStack[] itemStacks) {
		List<String> listItemName = Lists.newArrayList();
		// StringBuilder stringBuilder = new StringBuilder();
		// for (ItemStack is : itemStacks) {
		// stringBuilder.append(TranslateManager.getInstance().getTranslate(is.getType())
		// + ", ");
		// if (index == 3) {
		// listItemName.add(stringBuilder.toString());
		// stringBuilder.setLength(0);
		// }
		// }
		return listItemName;
	}
	
	public static String getNameItemWithoutFormat(ItemStack is) {
		return ChatColor.stripColor(Utils.formatColor(is.getItemMeta().getDisplayName()));
	}
}
