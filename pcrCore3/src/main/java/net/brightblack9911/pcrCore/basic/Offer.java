package net.brightblack9911.pcrCore.basic;

import org.bukkit.inventory.ItemStack;

public class Offer
{
	private String name;
	private ItemStack is;
	private ItemStack icon;
	private int price;

	public Offer(String name, ItemStack icon, ItemStack is, int price) {
		this.name = name;
		this.icon = icon;
		this.is = is;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public ItemStack getBuyItem() {
		return this.is.clone();
	}

	public ItemStack getIconItem() {
		return this.icon;
	}

	public int getPrice() {
		return this.price;
	}

}
