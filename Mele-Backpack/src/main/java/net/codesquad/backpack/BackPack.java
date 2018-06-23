package net.codesquad.backpack;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.inventory.Inventory;

import net.codesquad.backpack.managers.SQLiteManager;
import net.codesquad.backpack.utils.IOUtils;

public class BackPack
{
	private int id;
	private Inventory inventory;

	public BackPack(int id, Inventory inventory) {
		this.id = id;
		this.inventory = inventory;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	public void save() {
		ResultSet resultset = SQLiteManager.getInstance().executeQuery("SELECT * FROM `backpacks` WHERE `id`= '" + this.id + "'");
		try {
			while (resultset.next()) { 
				SQLiteManager.getInstance().executeUpdate("UPDATE `backpacks` SET `inventory` = '" + IOUtils.inventoryToBase64(this.inventory) + "' WHERE `id` = '" + this.id + "'");
				return;
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultset.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SQLiteManager.getInstance().executeUpdate("INSERT INTO `backpacks` (id, inventory) VALUES (NULL, '" + IOUtils.inventoryToBase64(this.inventory) + "')");
	}
}
