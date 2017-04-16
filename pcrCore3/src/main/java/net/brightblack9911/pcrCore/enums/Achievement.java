package net.brightblack9911.pcrCore.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Achievement {
	GET_FIRST_GOLDENAPPLE("Zdobadz pierwszego koxa", new ItemStack(Material.GOLDEN_APPLE, 1, (byte) 1)), CREATE_GUILD("Stworz gildie",
			new ItemStack(Material.PAPER, 1)), KILL_THREE_ENEMY("Zabij z zimna krwia 10 wrogow", new ItemStack(Material.DIAMOND_SWORD, 1));

	private String name;
	private ItemStack icon;

	Achievement(String name, ItemStack icon) {
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public ItemStack getIcon() {
		return icon;
	}

}
