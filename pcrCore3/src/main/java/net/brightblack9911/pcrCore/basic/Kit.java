package net.brightblack9911.pcrCore.basic;

import org.bukkit.inventory.ItemStack;

public class Kit
{
	private String s;
	private ItemStack icon;
	private int sec;
	boolean availableIfDisable;
	private ItemStack[] items;

	public Kit(String s, ItemStack icon, int sec, boolean availableIfDisable, ItemStack[] items) {
		this.s = s;
		this.icon = icon;
		this.sec = sec;
		this.availableIfDisable = availableIfDisable;
		this.items = items;
	}

	public String getName() {
		return s;
	}

	public ItemStack getIconItem() {
		return icon;
	}

	public int getTime() {
		return sec;
	}

	public ItemStack[] getItems() {
		return items;
	}

	public boolean getAvailableIfDisable() {
		return availableIfDisable;
	}
}
