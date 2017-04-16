package net.brightblack9911.pcrCore.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Crops;
import org.bukkit.material.NetherWarts;

import net.brightblack9911.pcrCore.basic.CorePlugin;
import net.brightblack9911.pcrCore.enums.FarmerType;
import net.brightblack9911.pcrCore.managers.DropManager;

public class Utils
{
	private static ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);

	// ## formatting colors ##
	public static String formatColor(String s) {
		if (s == null) return "";
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	// ##

	// ## method to easy send message ##
	public static boolean sendMsg(CommandSender cs, String s) {
		if (!(cs instanceof Player)) {
			ChatColor.stripColor(formatColor(s));
		}
		if ((s != null) || (s != "")) cs.sendMessage(formatColor(s));
		return true;
	}
	// ##

	public static void bcMessage(String s) {
		Bukkit.broadcastMessage(formatColor(s));

	}

	public static ItemStack getSkull(String s) {
		SkullMeta sm = (SkullMeta) is.getItemMeta();
		sm.setOwner(s);
		is.setItemMeta(sm);
		return is;
	}

	public static boolean isInteger(String s) {
		return Pattern.matches("-?[0-9]+", s.subSequence(0, s.length()));
	}

	public static void createFarmer(FarmerType farmerType, Location location) {
		switch (farmerType) {
			case BOY:
				for (int i = 0; i < 60; i++) {
					Location location2 = new Location(location.getWorld(), location.getX(), location.getY() + i, location.getZ());
					if (!(location2.getBlock().getType() == Material.AIR)) return;
					location2.getBlock().setType(Material.OBSIDIAN);
				}

				break;
			case SAND:
				for (int i = 0; i < 60; i++) {
					Location location2 = new Location(location.getWorld(), location.getX(), location.getY() + i, location.getZ());
					if (!(location2.getBlock().getType() == Material.AIR)) return;
					location2.getBlock().setType(Material.SAND);
				}
				
				break;

			default:
				break;
		}
	}

	public static boolean canUse(long saveTime, long time) {
		if (System.currentTimeMillis() - saveTime >= time) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static List<ItemStack> getDrops(final Block block, final ItemStack item, Player p) {
		final List<ItemStack> items = new ArrayList<>();
		final Material type = block.getType();
		int amount = 1;
		byte data;
		switch (type) {
			case STONE:
				if (DropManager.getInstance().getDisableCobble().contains(p)) {
					block.getDrops().remove(Material.COBBLESTONE);
					break;
				}
				amount = 1;
				items.add(new ItemStack(Material.COBBLESTONE, amount));
				break;
			case NETHER_WARTS:
				final NetherWarts warts = (NetherWarts) block.getState().getData();
				amount = (warts.getState().equals(NetherWartsState.RIPE) ? (randInt(0, 2) + 2) : 1);
				items.add(new ItemStack(Material.NETHER_STALK, amount));
				break;
			case COCOA:
				final CocoaPlant plant = (CocoaPlant) block.getState().getData();
				amount = (plant.getSize().equals(CocoaPlant.CocoaPlantSize.LARGE) ? 3 : 1);
				items.add(new ItemStack(Material.INK_SACK, amount, (short) 3));
				break;
			case PUMPKIN_STEM:
				items.add(new ItemStack(Material.PUMPKIN_SEEDS, 1));
				break;
			case MELON_STEM:
				items.add(new ItemStack(Material.MELON_SEEDS, 1));
				break;
			case CARROT:
				data = block.getState().getData().getData();
				switch (data) {
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
						amount = 1;
						break;
					case 7:
						amount = randInt(1, 3);
						break;
				}
				items.add(new ItemStack(Material.CARROT_ITEM, amount));
				break;
			case POTATO:
				data = block.getState().getData().getData();
				switch (data) {
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
						amount = 1;
						break;
					case 7:
						amount = randInt(1, 3);
						break;

				}
				items.add(new ItemStack(Material.POTATO_ITEM, amount));
				break;
			case CROPS:
				final Crops wheat = (Crops) block.getState().getData();
				int seedamount = 1;
				if (wheat.getState() == CropState.RIPE) {
					items.add(new ItemStack(Material.WHEAT, randInt(1, 2)));
					seedamount = 1 + randInt(0, 2);
				}
				items.add(new ItemStack(Material.SEEDS, seedamount));
				break;
			case SUGAR_CANE_BLOCK:
				amount = 1;
				items.add(new ItemStack(Material.SUGAR_CANE, amount));
				break;
			case DOUBLE_PLANT:
				data = block.getData();
				if (data == 11) {
					Block under = block.getRelative(0, -1, 0);
					if (under.getType() == Material.DOUBLE_PLANT) {
						items.addAll(under.getDrops(item));
						under.setType(Material.AIR);
					}
				} else {
					items.addAll(block.getDrops(item));
				}
				break;
			case REDSTONE_WIRE:
			case WOODEN_DOOR:
				data = block.getData();
				if (data == 8) {
					Block under = block.getRelative(0, -1, 0);
					if (under.getType() == Material.WOODEN_DOOR) {
						items.addAll(under.getDrops(item));
						under.setType(Material.AIR);
					}
				} else {
					items.addAll(block.getDrops(item));
				}
				break;
			case IRON_DOOR_BLOCK:
				data = block.getData();
				if (data == 8) {
					Block under = block.getRelative(0, -1, 0);
					if (under.getType() == Material.IRON_DOOR_BLOCK) {
						items.addAll(under.getDrops(item));
						under.setType(Material.AIR);
					}
				} else {
					items.addAll(block.getDrops(item));
				}
				break;
			case TRIPWIRE:
			case LEVER:
			case WOOD_BUTTON:
			case STONE_BUTTON:
			case DIODE_BLOCK_ON:
			case DIODE_BLOCK_OFF:
			case REDSTONE_COMPARATOR_OFF:
			case REDSTONE_COMPARATOR_ON:
			case DAYLIGHT_DETECTOR:
				items.addAll(block.getDrops(item));
				break;
			case REDSTONE_ORE:
			case GLOWING_REDSTONE_ORE:
				if (item.containsEnchantment(Enchantment.SILK_TOUCH) && block.getType().isBlock()) {
					items.add(new ItemStack(Material.REDSTONE_ORE));
					break;
				}
				items.addAll(block.getDrops(item));
				break;
			case SNOW:
				items.add(new ItemStack(Material.SNOW_BALL, block.getData()));
				break;
			default:
				if (item.containsEnchantment(Enchantment.SILK_TOUCH) && block.getType().isBlock()) {
					items.add(new ItemStack(block.getType(), 1, (short) block.getData()));
					break;
				}
				// for (ItemStack is : block.getDrops(item)) {
				// if (is.equals(new ItemStack(Material.COBBLESTONE))) continue;
				// items.add(is);
				// }
				items.addAll(block.getDrops(item));
				break;
		}
		return items;
	}

	public static float rand(int min, int max) {
		return min + (new Random()).nextInt(max - min);
	}
	
	public static void recalculateDurability(Player player, ItemStack item) {
		if (item.getType().getMaxDurability() == 0) {
			return;
		}
		int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
		short d = item.getDurability();
		if (enchantLevel > 0) {
			if (100 / (enchantLevel + 1) > randInt(0, 100)) {
				if (d == item.getType().getMaxDurability()) {
					player.getInventory().clear(player.getInventory().getHeldItemSlot());
					player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
				} else {
					item.setDurability((short) (d + 1));
				}
			}
		} else if (d == item.getType().getMaxDurability()) {
			player.getInventory().clear(player.getInventory().getHeldItemSlot());
			player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
		} else {
			item.setDurability((short) (d + 1));
		}
	}
	
	public static boolean chance(final double chance) {
		return Math.random() * 100.0 <= chance;
	}

	public static int randInt(final int min, final int max) {
		final int randomNum = CorePlugin.getInstance().getRandom().nextInt(max - min + 1) + min;
		return randomNum;
	}
}
