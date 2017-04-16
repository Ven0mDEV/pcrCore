package net.brightblack9911.pcrCore.basic;

import java.util.List;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.brightblack9911.pcrCore.utils.Utils;

public class ItemBuilder
{
	private ItemStack item;
	private String name;
	private List<String> lore;
	private Map<Enchantment, Integer> enchant;

	public ItemBuilder(ItemStack item) {
		this.item = item;
		this.lore = Lists.newArrayList();
		this.enchant = Maps.newHashMap();
	}

	public ItemBuilder setName(String name) {
		this.name = Utils.formatColor(name);
		return this;
	}

	public ItemBuilder addLore(String lore) {
		this.lore.add(Utils.formatColor(lore));
		return this;
	}

	public ItemBuilder addLore(List<String> lore) {
		for (String lore2 : lore) {
			this.lore.add(Utils.formatColor(lore2));
		}
		return this;
	}

	public ItemBuilder addEnchant(Enchantment enchant, int amount) {
		this.enchant.put(enchant, amount);
		return this;
	}

	public ItemStack build() {
		ItemMeta meta = item.getItemMeta();
		if (name != null) {
			meta.setDisplayName(name);
		}
		if (!lore.isEmpty()) {
			meta.setLore(lore);
		}
		if (!enchant.isEmpty()) {
			for (Map.Entry<Enchantment, Integer> entry : enchant.entrySet()) {
				meta.addEnchant(entry.getKey(), entry.getValue(), true);
			}
		}
		item.setItemMeta(meta);
		return item;
	}
}
