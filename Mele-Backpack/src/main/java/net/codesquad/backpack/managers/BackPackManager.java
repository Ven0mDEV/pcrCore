/**
 * 
 */
package net.codesquad.backpack.managers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import net.codesquad.backpack.BackPack;
import net.codesquad.backpack.ItemBase;
import net.codesquad.backpack.utils.IOUtils;
import net.codesquad.backpack.utils.Utils;

/**
 * @author nano3.
 *
 */
public class BackPackManager
{
	private static BackPackManager instance;
	
	private final List<BackPack> BACKPACKS = Lists.newArrayList();
	
	private ItemBase itembase;
	
	public BackPackManager() {
		itembase = new ItemBase(new ItemStack(Material.DIAMOND_SWORD, 1), Utils.formatColor(MessagesManager.getInstance().getMessage("backpack-item-name")));
		loadBackPacks();
	}
	
	/**
	 * @return the list of backpacks
	 */
	public List<BackPack> getBackPacks() {
		return BACKPACKS;
	}
	
	public BackPack getBackPackById(int id) {
		for (BackPack backpack : getBackPacks()) {
			if (backpack.getId() == id) return backpack;
		}
		return null;
	}
	
	public Inventory getBackPackByItemStack(ItemStack itemstack) {
		return getBackPackById(Integer.parseInt(itemstack.getItemMeta().getLore().get(0).split("#")[1])).getInventory();
	}
	
	public BackPack getBackPackByInventory(Inventory inventory) {
		for (BackPack backpack : getBackPacks()) {
			if (backpack.getInventory().equals(inventory)) return backpack;
		}
		return null;
	}
	
	public ItemStack giveBackPackItem(Player player) { 
		int id = getBackPacks().size();
		id++;
		getBackPacks().add(new BackPack(id, Bukkit.createInventory(null, 27, Utils.formatColor(MessagesManager.getInstance().getMessage("inventory-title")))));
		return getBackPackItem().setLore(Arrays.asList("ID: #" + id)).getItemstack();
	}
	
	private void loadBackPacks() {
		ResultSet resultset = SQLiteManager.getInstance().executeQuery("SELECT * FROM `backpacks`");
		try {
			if (resultset.next()) {
				try {
					getBackPacks().add(new BackPack(resultset.getInt("id"), IOUtils.inventoryFromBase64(resultset.getString("inventory"))));
				}
				catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) { 
			e.printStackTrace();
		}
		try {
			resultset.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public ItemBase getBackPackItem() {
		return itembase;
	}
	
	public static BackPackManager getInstance() {
		if (instance == null) instance = new BackPackManager();
		return instance;
	} 
}
