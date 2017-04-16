package net.brightblack9911.pcrCore.listeners;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import net.brightblack9911.pcrCore.enums.Achievement;
import net.brightblack9911.pcrCore.managers.AchievementManager;

public class PrepareItemCraftListener implements Listener
{
	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent ev) {
		if (ev.getRecipe().getResult().equals(new ItemStack(Material.GOLDEN_APPLE, (byte) 1))) {
			for (HumanEntity he : ev.getViewers()) {
				if (he instanceof Player) {
					AchievementManager.getInstance().getAchievement((Player) he, Achievement.GET_FIRST_GOLDENAPPLE, 1);
				}
			}

		}
	}
}
