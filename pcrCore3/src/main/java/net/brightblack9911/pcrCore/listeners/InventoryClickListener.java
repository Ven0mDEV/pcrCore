package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.brightblack9911.pcrCore.basic.DropItem;
import net.brightblack9911.pcrCore.basic.Kit;
import net.brightblack9911.pcrCore.basic.Offer;
import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.enums.InventoryCat;
import net.brightblack9911.pcrCore.managers.DropManager;
import net.brightblack9911.pcrCore.managers.GUIMenuManager;
import net.brightblack9911.pcrCore.managers.KitManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.OfferManager;
import net.brightblack9911.pcrCore.managers.Settings;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.ItemUtils;
import net.brightblack9911.pcrCore.utils.StringUtils;
import net.brightblack9911.pcrCore.utils.Utils;

public class InventoryClickListener implements Listener
{
	private final MessagesManager MM = MessagesManager.getInstance();
	private final KitManager KM = KitManager.getInstance();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent ev) {
		if (ev.getClickedInventory() == null) {
			return;
		}

		final Inventory INV = ev.getClickedInventory();
		final Player p = (Player) ev.getWhoClicked();
		User u = UserManager.getInstance().getUser(p);

		ItemMeta meta;
		String name = "";
		ItemStack is = ev.getCurrentItem();
		if (is != null && is.getType() != Material.AIR) {
			if (is.hasItemMeta()) {
				meta = is.getItemMeta();
				name = meta.getDisplayName();
			}

			// ## KITS_GUI ##
			if (INV.getName().equals(InventoryCat.KITS_GUI.getName())) {
				Kit k = KM.getKitByName(ItemUtils.getNameItemWithoutFormat(is));
				if (k != null) {
					if (!p.hasPermission("core.kits." + k.getName())) {
						p.closeInventory();
						Utils.sendMsg(p, MM.NO_PERMISSION.replace("%permission", "core.kits." + k.getName()));
						return;
					}
					
					if (!k.getAvailableIfDisable() && !Settings.getInstance().allowKits) {
						p.closeInventory();
						Utils.sendMsg(p, MM.KITS_CURRENTLY_DISABLE);
						return;
					}
					
					if (u.getKits().containsKey(k.getName())) {
						if (Utils.canUse(u.getKits().get(k.getName()), k.getTime() * 1000)) {
							KitManager.getInstance().takeKit(p, k);
							p.closeInventory();
							Utils.sendMsg(p, MM.TAKE_KIT.replace("%kit", k.getName()));
							return;
						} else {
							Utils.sendMsg(p, MM.NOT_TAKE_KIT.replace("%time",
									StringUtils.getDurationBreakdown((k.getTime() * 1000) - (System.currentTimeMillis() - u.getKits().get(k.getName())))));
							p.closeInventory();
							return;
						}
					} else {
						KitManager.getInstance().takeKit(p, k);
						p.closeInventory();
						Utils.sendMsg(p, MM.TAKE_KIT.replace("%kit", k.getName()));
						return;
					}
				}
				ev.setCancelled(true);
			}
			// ##

			// ## SHOP_GUI ##
			if (INV.getName().equals(InventoryCat.SHOP_GUI.getName())) {
				Offer o = OfferManager.getInstance().getOfferByName(ItemUtils.getNameItemWithoutFormat(is));
				if (o != null) {
					if (u.getCoins() >= o.getPrice()) {
						u.setCoins(u.getCoins() - o.getPrice(), true);
						ItemUtils.giveToInventory(p, o.getBuyItem());
						p.closeInventory();
						Utils.sendMsg(p, MM.BUY_SUCCESS);
						return;
					} else {
						p.closeInventory();
						Utils.sendMsg(p, MM.NOT_ENOUGH_COINS);
						return;
					}
				}
				ev.setCancelled(true);
			}

			// ##

			// ## EXCHANGECOINS_GUI ##
			if (INV.getName().equals(InventoryCat.EXCHANGECOIN_GUI.getName())) {
				if (ev.getSlot() >= 36 && ev.getSlot() <= 54) {
					ev.setCancelled(true);
				}
			}
			// ##

			// ## OWNERS_GUI ##
			if (INV.getName().equals(GUIMenuManager.getInstance().OWNERS_GUI.getName())) {
				ev.setCancelled(true);
			}
			// ##

			// ## DROP_GUI ##
			if (INV.getName().equals(InventoryCat.DROP_GUI.getName())) {
				DropItem drop = DropItem.getDropByName(ItemUtils.getNameItemWithoutFormat(is));
				if (drop != null) {
					drop.changeStatus(p.getUniqueId());
					p.closeInventory();
					GUIMenuManager.getInstance().openDropGUI(p);
				}
				ev.setCancelled(true);
			}
			// ##

			// ## ACHIEVEMENT_GUI ##
			if (INV.getName().equals(InventoryCat.ACHIEVEMENT_GUI.getName())) {
				ev.setCancelled(true);
			}
			// ##

			if (INV.getName().equals(GUIMenuManager.getInstance().GENERATOR_GUI.getName())) {
				ev.setCancelled(true);
				if (name.equals(Utils.formatColor("&a&oSTONE"))) {
					System.out.println("sone kilka");
					if (!u.getGenerator().get(p.getName()).getBlock().equals(Material.ENDER_STONE)) return;
					System.out.println("i jest ender_stone");
					u.getGenerator().get(p.getName()).add(0, 1, 0).getBlock().setType(Material.STONE);

					u.getGenerator().remove(p.getName());
					p.closeInventory();
					return;
				}
				if (name.equals(Utils.formatColor("&a&oOBSIDIAN"))) {
					if (!u.getGenerator().get(p.getName()).getBlock().equals(Material.ENDER_STONE)) return;
					u.getGenerator().get(p.getName()).add(0, 1, 0).getBlock().setType(Material.OBSIDIAN);

					u.getGenerator().remove(p.getName());
					p.closeInventory();
					return;
				}
			}
		}
	}
}
