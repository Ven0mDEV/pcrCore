package net.brightblack9911.pcrCore.managers;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import net.brightblack9911.pcrCore.basic.ItemBuilder;
import net.brightblack9911.pcrCore.basic.Kit;
import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.utils.ItemUtils;
import redis.clients.jedis.Jedis;

public class KitManager
{
	private static KitManager instance;

	private final List<Kit> KITS = Lists.newArrayList();

	public KitManager() {
		loadKits();
	}

	public void registerKit(String s, ItemStack icon, int sec, boolean availableIfDisable, ItemStack[] items) {
		KITS.add(new Kit(s, icon, sec, availableIfDisable, items));
	}

	public void loadKits() {
		registerKit("START", new ItemStack(Material.WOOD_SWORD, 1), 43200, true, new ItemStack[] { new ItemStack(Material.STONE_SWORD, 1), new ItemStack(Material.STONE_PICKAXE, 1),
				new ItemStack(Material.COOKED_BEEF, 64), new ItemStack(Material.ENDER_CHEST, 1), new ItemStack(Material.WOOD, 32), new ItemStack(Material.ENDER_PEARL) });
		registerKit("Vip", new ItemStack(Material.GOLD_BLOCK, 1), 86400, false, new ItemStack[] {
				new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD, 1)).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.FIRE_ASPECT, 2).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD, 1)).addEnchant(Enchantment.KNOCKBACK, 2).build(), new ItemStack(Material.GOLDEN_APPLE, 5, (byte) 1),
				new ItemStack(Material.ENDER_PEARL, 3), new ItemStack(Material.GOLDEN_APPLE, 10),
				new ItemBuilder(new ItemStack(Material.DIAMOND_HELMET, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_CHESTPLATE, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_LEGGINGS, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_BOOTS, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).build() });
		registerKit("YT", new ItemStack(Material.WOOL, (byte) 14), 86400, false, new ItemStack[] {
				new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD, 1)).addEnchant(Enchantment.DAMAGE_ALL, 3).addEnchant(Enchantment.FIRE_ASPECT, 1).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD, 1)).addEnchant(Enchantment.KNOCKBACK, 1).build(), new ItemStack(Material.GOLDEN_APPLE, 3, (byte) 1),
				new ItemStack(Material.ENDER_PEARL, 1), new ItemStack(Material.GOLDEN_APPLE, 5),
				new ItemBuilder(new ItemStack(Material.DIAMOND_HELMET, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_CHESTPLATE, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_LEGGINGS, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_BOOTS, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 1).build() });

		registerKit("YT+", new ItemStack(Material.WOOL, (byte) 14), 86400, false, new ItemStack[] {
				new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD, 1)).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.FIRE_ASPECT, 2).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD, 1)).addEnchant(Enchantment.KNOCKBACK, 2).build(), new ItemStack(Material.GOLDEN_APPLE, 5, (byte) 1),
				new ItemStack(Material.ENDER_PEARL, 3), new ItemStack(Material.GOLDEN_APPLE, 10),
				new ItemBuilder(new ItemStack(Material.DIAMOND_HELMET, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_CHESTPLATE, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_LEGGINGS, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(new ItemStack(Material.DIAMOND_BOOTS, 1)).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.DURABILITY, 3).build() });

	}

	public List<Kit> getKits() {
		return KITS;
	}

	public void takeKit(Player p, Kit k) {
		ItemUtils.giveToInventory(p, k.getItems());
		if (!p.hasPermission("core.kit.bypass")) {
			User u = UserManager.getInstance().getUser(p);
			u.getKits().put(k.getName(), System.currentTimeMillis());

			try (Jedis j = JedisManager.getInstance().getJedisPool().getResource()) {
				j.hset(p.getUniqueId().toString(), k.getName(), "" + System.currentTimeMillis());
			}
		}
	}

	public Kit getKitByName(String s) {
		for (Kit k : getKits()) {
			if (k.getName().equals(s)) return k;
		}
		return null;
	}

	public static KitManager getInstance() {
		if (instance == null) instance = new KitManager();
		return instance;
	}
}
