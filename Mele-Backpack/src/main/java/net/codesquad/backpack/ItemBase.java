/**
 * 
 */
package net.codesquad.backpack;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;

/**
 * @author nano3.
 *
 */
public class ItemBase
{
	private ItemStack itemstack;
	private String name;
	private List<String> lore;
	
	public ItemBase(ItemStack itemstack, String name, List<String> lore) { 
		this.itemstack = itemstack;
		this.name = name;
		this.lore = lore;
		
		applyAddons();
	}
	
	public ItemBase(ItemStack itemstack, String name) { 
		this.itemstack = itemstack;
		this.name = name;
		this.lore = Lists.newArrayList();
		
		applyAddons();
	}
	
	public ItemBase(ItemStack itemstack, List<String> lore) { 
		this.itemstack = itemstack; 
		this.lore = lore;
		
		applyAddons();
	}
	
	public boolean equalsName(ItemStack itemstack) {
		return name.equals(itemstack.getItemMeta().getDisplayName());
	}
	
	public ItemBase setName(String name) {
		this.name = name;
		applyAddons();
		return this;
	}
	
	public ItemBase setLore(List<String> lore) {
		this.lore = lore;
		applyAddons();
		return this;
	} 
	
	private void applyAddons() {
		ItemMeta itemMeta = getItemstack().getItemMeta();
		if (name != null) {
			itemMeta.setDisplayName(name);
		}
		if (!lore.isEmpty()) {
			itemMeta.setLore(lore);
		} 
		getItemstack().setItemMeta(itemMeta);
	}  
	
	public ItemStack getItemstack() {
		return itemstack;
	} 
}
