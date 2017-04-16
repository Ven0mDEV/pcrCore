package net.brightblack9911.pcrCore.managers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;

import net.brightblack9911.pcrCore.basic.DropItem;
import net.brightblack9911.pcrCore.basic.FunnyFillSlot;
import net.brightblack9911.pcrCore.basic.ItemBuilder;
import net.brightblack9911.pcrCore.basic.ItemExChange;
import net.brightblack9911.pcrCore.basic.Kit;
import net.brightblack9911.pcrCore.basic.Offer;
import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.enums.Achievement;
import net.brightblack9911.pcrCore.enums.InventoryCat;
import net.brightblack9911.pcrCore.utils.ItemUtils;
import net.brightblack9911.pcrCore.utils.StringUtils;
import net.brightblack9911.pcrCore.utils.Utils;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class GUIMenuManager
{
	private static GUIMenuManager instance;

	private final ItemManager IM = ItemManager.getInstance();
	private final UserManager UM = UserManager.getInstance();

	public final Inventory OWNERS_GUI = Bukkit.createInventory(null, 27, Utils.formatColor(" &8► &6ADMINISTRACJA:"));
	public final Inventory GENERATOR_GUI = Bukkit.createInventory(null, 27, Utils.formatColor(" &8► &6WYBIERZ GENERATOR:"));

	/** public final Inventory craft_inv = Bukkit.createInventory(null, 9,
	 * Utils.formatColor(" &8&l► &a&o&lCRAFTINGI:")); public final Inventory
	 * ender_craft = Bukkit.createInventory(null, 54, Utils.formatColor(" &8&l►
	 * &a&o&lCRAFTINGI:")); public final Inventory stone_craft =
	 * Bukkit.createInventory(null, 54, Utils.formatColor(" &8&l►
	 * &a&o&lCRAFTINGI:")); public final Inventory boy_craft =
	 * Bukkit.createInventory(null, 54, Utils.formatColor(" &8&l►
	 * &a&o&lCRAFTINGI:")); public final Inventory rockt_craft =
	 * Bukkit.createInventory(null, 54, Utils.formatColor(" &8&l►
	 * &a&o&lCRAFTINGI:")); **/

	// ## Kits GUI ##
	public void openKitsGUI(Player p) {
		User u = UM.getUser(p);
		final Inventory KITS_GUI = getInventory(InventoryCat.KITS_GUI, p);
		KITS_GUI.clear();

		for (Kit k : KitManager.getInstance().getKits()) {
			if (u.getKits().containsKey(k.getName())) {
				KITS_GUI.addItem(
						new ItemBuilder(k.getIconItem()).setName(k.getName()).addLore("Zawartosc: ").addLore(ItemUtils.getListOfItems(k.getItems())).addLore(" ")
								.addLore((Utils.canUse(u.getKits().get(k.getName()), k.getTime() * 1000) ? "Dostepny: tak"
										: "Dostepny za: " + StringUtils.getDurationBreakdown((k.getTime() * 1000) - (System.currentTimeMillis() - u.getKits().get(k.getName())))))
								.build());
			} else {
				KITS_GUI.addItem(new ItemBuilder(k.getIconItem()).setName(k.getName()).addLore("Zawartosc: ").addLore(ItemUtils.getListOfItems(k.getItems()))
						.addLore(" Dostepny: tak").build());
			}
		}
		p.openInventory(KITS_GUI);
	}
	// ##

	// ## GENERATOR GUI ##
	public void openGeneratorGUI(Player p) {
		GENERATOR_GUI.clear();

		// setItemRange(GENERATOR_GUI, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build(), 0, 8);
		// setItemRange(GENERATOR_GUI, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build(), 9, 10);
		// setItemRange(GENERATOR_GUI, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build(), 12, 14);
		// setItemRange(GENERATOR_GUI, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build(), 18, 26);

		new FunnyFillSlot(GENERATOR_GUI, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 }).start();

		GENERATOR_GUI.setItem(11, new ItemBuilder(new ItemStack(Material.STONE, 1)).setName("&a&oSTONE").build());
		GENERATOR_GUI.setItem(15, new ItemBuilder(new ItemStack(Material.OBSIDIAN, 1)).setName("&a&oOBSIDIAN").build());

		p.openInventory(GENERATOR_GUI);
	}
	// ##

	// ## OWNERS GUI ##
	public void openOwnersGUI(Player p) {
		OWNERS_GUI.clear();
		PermissionGroup pg = PermissionsEx.getPermissionManager().getGroup("Wlasciciel");
		for (PermissionUser pu : pg.getUsers()) {
			OWNERS_GUI.addItem(Bukkit.getPlayer(pu.getName()) != null
					? new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).setName(pu.getName()).addEnchant(Enchantment.THORNS, 1).addLore("&fStatus: &aONLINE&f.")
							.addLore("").addLore("&fCzas online: &7" + StringUtils.getDurationBreakdown(System.currentTimeMillis() - UM.getUser(p).getOnlineTime()) + "&f.").build()
					: new ItemBuilder(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3)).setName(pu.getName()).addLore("&fStatus: &cOFFLINEf6.").build());
		}
		p.openInventory(OWNERS_GUI);
	}
	// ##

	// ## SHOP GUI ##
	public void openShopGUI(Player p) {
		final Inventory SHOP_GUI = getInventory(InventoryCat.SHOP_GUI, p);
		SHOP_GUI.clear();

		new FunnyFillSlot(SHOP_GUI, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 }).start();

		int index = 10;
		for (Offer o : OfferManager.getInstance().getOffers()) {
			SHOP_GUI.setItem(index, new ItemBuilder(o.getIconItem()).setName("&a&o" + o.getName()).addLore(" ").addLore("&fCena: &7" + o.getPrice() + "&f.").build());
			switch (index) {
				case 16:
					index += 3;
					break;
				case 25:
					index += 3;
					break;
				default:
					index++;
					break;
			}

		}
		// setItemRange(SHOP_GUI, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build(), OfferManager.getInstance().getOffers().size(), 53);
		
		SHOP_GUI.setItem(43, new ItemBuilder(new ItemStack(Material.EMERALD_BLOCK, 1)).setName("&f&lSTAN KONTA").addLore("&fStan konta: &7" + UM.getUser(p).getCoins() +"&f.").build());
		p.openInventory(SHOP_GUI);
	}
	// ##

	// ## EXCHANGECOINS GUI ##
	public void openExchangeCoinsGUI(Player p) {
		final Inventory EXCHANGECOINS_GUI = getInventory(InventoryCat.EXCHANGECOIN_GUI, p);
		EXCHANGECOINS_GUI.clear();

		setItemRange(EXCHANGECOINS_GUI, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName(" ").addLore("&7Przeciagnij z ekwipunku")
				.addLore("&7przedmioty ktore chcesz").addLore("&7wymienic na COINS.").addLore(" ").addLore("&fPrzedmioty mozliwe do wymiany:").build(), 36, 44);
		int i = 45;
		for (ItemExChange itemExChange : ExChangeCoinManager.getInstance().getItems()) {
			EXCHANGECOINS_GUI.setItem(i,
					new ItemBuilder(new ItemStack(itemExChange.getItem(), 1)).addLore("&fOtrzymasz: &7" + itemExChange.getPrice() + " COINS za: 1x&f.").build());
			i++;
		}
		p.openInventory(EXCHANGECOINS_GUI);
	}
	// ##

	// ## DROP GUI ##
	public void openDropGUI(Player p) {
		final Inventory DROP_GUI = getInventory(InventoryCat.DROP_GUI, p);
		DROP_GUI.clear();

		for (DropItem d : DropManager.getInstance().getDrops()) {
			final String NAME = d.getName();
			final double CHANCE = d.getChance();
			final boolean FORTUNE = d.getFortuneStatus();

			final Material DROPMATERIAL = d.getMaterial();
			// final Material DropFromBlock = drop.getDropFrom();

			final boolean TOOLS_STATUS = d.getToolsStatus();
			boolean DROP_STATUS = d.isDisabled(p.getUniqueId());
			final List<Material> TOOLS_LIST = d.getTools();
			final boolean HEIGHT_STATUS = d.getHeightStatus();
			final int HEIGHT_MIN = d.getMinHeight();

			final ItemStack is = new ItemStack(DROPMATERIAL);
			final ItemMeta im = is.getItemMeta();

			final List<String> lore = Lists.newArrayList();

			lore.clear();
			lore.add(Utils.formatColor("&fSzansa: &7" + CHANCE + "&7."));
			lore.add(Utils.formatColor("&fFortuna: &7" + (FORTUNE ? "tak" : "nie") + "&f."));
			lore.add(Utils.formatColor("&fAktywny: &7" + (DROP_STATUS ? "nie" : "tak") + "&f."));

			if (HEIGHT_STATUS) lore.add(Utils.formatColor("&fOd poziomu: &7< " + HEIGHT_MIN + "&f."));

			// if (toolsTurnOn) {
			// String str = "";
			// for (final Material m : toolsList) {
			// str = String.valueOf(str) + materialToString(m) +
			// Utils.formatColor("&8,&3 ");
			// }
			// lore.add(Utils.formatColor("&7Kilofy: &3" + str.substring(0,
			// str.length() - 6) + "&7."));
			// }

			im.setDisplayName(Utils.formatColor("&a&o" + NAME));
			im.setLore(lore);
			is.setItemMeta(im);
			DROP_GUI.addItem(is);
		}
		setItemRange(DROP_GUI, new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName(" ").build(), DropManager.getInstance().getDrops().size(), 26);
		p.openInventory(DROP_GUI);
	}

	// ## ACHIEVEMENT GUI ##
	public void openAchievementGUI(Player p) {
		final Inventory ACHIEVEMENT_GUI = getInventory(InventoryCat.ACHIEVEMENT_GUI, p);
		ACHIEVEMENT_GUI.clear();

		// setItemRange(ACHIEVEMENT_GUI, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build(), 0, 8);
		// setItemRange(ACHIEVEMENT_GUI, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build(), 18, 26);
		// ACHIEVEMENT_GUI.setItem(9, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build());
		// ACHIEVEMENT_GUI.setItem(17, new ItemBuilder(new
		// ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName("
		// ").build());

		new FunnyFillSlot(ACHIEVEMENT_GUI, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 }).start();

		int index = 10;

		for (Achievement achievement : Achievement.values()) {
			ACHIEVEMENT_GUI.setItem(index, new ItemBuilder(achievement.getIcon()).setName("&a&o" + achievement.getName())
					.addLore("&fOsiagnieto: &7" + (UM.getUser(p).getAchievements().containsKey(achievement) ? "tak" : "nie") + "&f.").build());
			index++;
		}

		p.openInventory(ACHIEVEMENT_GUI);
	}
	// ##

	private void setItemRange(Inventory inv, ItemStack is, int iStart, int iEnd) {
		for (int i = iStart; i <= iEnd; i++) {
			inv.setItem(i, is);
		}
	}

	private Inventory getInventory(InventoryCat ic, Player p) {
		Inventory inv;
		User u = UserManager.getInstance().getUser(p);
		if (!u.getInventorys().containsKey(ic)) {
			inv = Bukkit.createInventory(p, ic.getSize(), ic.getName());
			u.getInventorys().put(ic, inv);
		}
		inv = u.getInventorys().get(ic);
		return inv;
	}

	public static GUIMenuManager getInstance() {
		if (instance == null) instance = new GUIMenuManager();
		return instance;
	}
}
