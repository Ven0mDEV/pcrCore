package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import net.brightblack9911.pcrCore.basic.FunnyFillSlot;
import net.brightblack9911.pcrCore.basic.ItemExChange;
import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.enums.InventoryCat;
import net.brightblack9911.pcrCore.managers.ExChangeCoinManager;
import net.brightblack9911.pcrCore.managers.GUIMenuManager;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.UserManager;
import net.brightblack9911.pcrCore.utils.ItemUtils;
import net.brightblack9911.pcrCore.utils.Utils;

public class InventoryCloseListener implements Listener
{
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent ev) {
		Player p = (Player) ev.getPlayer();
		User u = UserManager.getInstance().getUser(p);
		if (ev.getInventory().getName().equals(InventoryCat.EXCHANGECOIN_GUI.getName())) {
			int takeCoins = 0;
			for (ItemExChange itemExChange : ExChangeCoinManager.getInstance().getItems()) {
				takeCoins += (ItemUtils.getAmountItemOfInventoryRange(ev.getInventory(), itemExChange.getItem(), 0, 35) * itemExChange.getPrice());
			}
			u.setCoins(u.getCoins() + takeCoins, true);
			Utils.sendMsg(p, MessagesManager.getInstance().GET_COINS.replace("%amount", Integer.toString(takeCoins)));
		}
		if (ev.getInventory().getName().equals(GUIMenuManager.getInstance().GENERATOR_GUI.getName())) {
			if (FunnyFillSlot.getFunnyInventories().containsKey(ev.getInventory())) {
				FunnyFillSlot.getFunnyInventories().get(ev.getInventory()).stop();
				System.out.println("to ten sam OBIEKT");
			}
			if (u.getGenerator().containsKey(p.getName())) {
				u.getGenerator().get(p.getName()).getBlock().setType(Material.AIR);
				u.getGenerator().remove(p.getName());
			}
		}
		if (ev.getInventory().getName().equals(InventoryCat.ACHIEVEMENT_GUI.getName())) {
			if (FunnyFillSlot.getFunnyInventories().containsKey(ev.getInventory())) {
				FunnyFillSlot.getFunnyInventories().get(ev.getInventory()).stop();
				System.out.println("to ten sam OBIEKT");

			}
		}
	}
}
