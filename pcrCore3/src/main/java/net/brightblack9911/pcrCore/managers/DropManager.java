package net.brightblack9911.pcrCore.managers;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.google.common.collect.Sets;

import net.brightblack9911.pcrCore.basic.DropItem;
import net.brightblack9911.pcrCore.utils.Utils;

public class DropManager
{
	private static DropManager instance;

	private final Set<DropItem> DROPS = Sets.newHashSet();
	private final Set<Player> COBBLESTONE = Sets.newHashSet();

	public DropManager() {
		loadDrops();
	}

	private void registerDrop(String name, Material m, double chance, boolean fortune, Material dropFrom, boolean heightTurn, int minY, int maxY, boolean toolsTurn, int amountMin,
			int amountMax, boolean drop_with_name, Material... tools) {
		DROPS.add(new DropItem(name, m, chance, fortune, dropFrom, heightTurn, minY, maxY, toolsTurn, amountMin, amountMax, drop_with_name, tools));
	}

	public void loadDrops() {
		// nazwa, co ma dropic, szansa, szansa vip, czy fortune, dropFrom,
		// boolean heightTurn, int minY, int maxY, boolean toolsTurn,
		// int amountMin, int amountMax, Material... tools
		registerDrop(Utils.formatColor("DIAMENT"), Material.DIAMOND, 1.5, true, Material.STONE, false, 40, 256, true, 1, 1, false, Material.IRON_PICKAXE,
				Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("EMERALD"), Material.EMERALD, 1.5, true, Material.STONE, false, 40, 256, true, 1, 1, false, Material.IRON_PICKAXE,
				Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("ZLOTO"), Material.GOLD_INGOT, 1.9, true, Material.STONE, false, 40, 256, true, 1, 1, false, Material.STONE_PICKAXE, Material.IRON_PICKAXE,
				Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("ZELAZO"), Material.IRON_INGOT, 2.1, true, Material.STONE, false, 70, 256, true, 1, 1, false, Material.STONE_PICKAXE,
				Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("PERLA"), Material.ENDER_PEARL, 0.1, true, Material.STONE, false, 40, 256, true, 1, 1, false, Material.STONE_PICKAXE,
				Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("WEGIEL"), Material.COAL, 7, true, Material.STONE, false, 70, 256, false, 1, 1, false, Material.STONE_PICKAXE, Material.IRON_PICKAXE,
				Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("JABLKO"), Material.APPLE, 0.3, true, Material.STONE, false, 40, 256, true, 1, 1, false, Material.WOOD_PICKAXE, Material.STONE_PICKAXE,
				Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("Proch"), Material.TNT, 1.5, true, Material.STONE, false, 40, 256, true, 1, 1, false, Material.WOOD_PICKAXE, Material.STONE_PICKAXE,
				Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("OBSIDIAN"), Material.OBSIDIAN, 2.5, true, Material.STONE, false, 40, 256, true, 1, 1, false, Material.STONE_PICKAXE,
				Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("KSIAZKA"), Material.BOOK, 1, true, Material.STONE, false, 40, 256, false, 1, 1, false, Material.WOOD_PICKAXE, Material.STONE_PICKAXE,
				Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
		registerDrop(Utils.formatColor("REDSTONE"), Material.REDSTONE, 1.2, true, Material.STONE, false, 40, 256, false, 1, 1, false, Material.WOOD_PICKAXE,
				Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
		// registerDrop(Utils.formatColor("&c&lSKRZYNIA TAJEMNIC"),
		// Material.CHEST, 1.5, true, Material.STONE, false, 64, 256, false, 1,
		// 1, true, Material.WOOD_PICKAXE,
		// Material.STONE_PICKAXE, Material.IRON_PICKAXE,
		// Material.DIAMOND_PICKAXE);
	}

	public Set<DropItem> getDrops() {
		return DROPS;
	}

	public Set<Player> getDisableCobble() {
		return COBBLESTONE;
	}

	public static DropManager getInstance() {
		if (instance == null) instance = new DropManager();
		return instance;
	}
}
