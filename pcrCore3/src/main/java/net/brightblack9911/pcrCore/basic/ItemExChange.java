package net.brightblack9911.pcrCore.basic;

import org.bukkit.Material;

public class ItemExChange
{
	private Material item;
	private int price;
	
	public ItemExChange(Material item, int price) {
		this.setItem(item);
		this.setPrice(price);
	}

	public Material getItem() {
		return item;
	}

	public void setItem(Material item) {
		this.item = item;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
