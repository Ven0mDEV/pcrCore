package net.brightblack9911.pcrCore.managers;

import java.util.List;

import org.bukkit.Material;

import com.google.common.collect.Lists;

import net.brightblack9911.pcrCore.basic.ItemExChange;

public class ExChangeCoinManager
{
	private static ExChangeCoinManager instance;

	private final List<ItemExChange> ITEMS = Lists.newArrayList();

	public ExChangeCoinManager() {
		loadExChangeCoinItems();
	}

	private void loadExChangeCoinItems() {
		registerItem(new ItemExChange(Material.COBBLESTONE, 1));
		registerItem(new ItemExChange(Material.GRASS, 3));
		registerItem(new ItemExChange(Material.DIAMOND, 15));
		registerItem(new ItemExChange(Material.GOLD_INGOT, 8));
		registerItem(new ItemExChange(Material.TNT, 20));
		registerItem(new ItemExChange(Material.BOOKSHELF, 300));
		registerItem(new ItemExChange(Material.REDSTONE_BLOCK, 25));
		registerItem(new ItemExChange(Material.LOG, 5));
		registerItem(new ItemExChange(Material.OBSIDIAN, 10));
	}

	private void registerItem(ItemExChange itemExChange) {
		ITEMS.add(itemExChange);
	}

	public List<ItemExChange> getItems() {
		return ITEMS;
	}

	public static ExChangeCoinManager getInstance() {
		if (instance == null) instance = new ExChangeCoinManager();
		return instance;
	}
}
