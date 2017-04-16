package net.brightblack9911.pcrCore.managers;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import net.brightblack9911.pcrCore.basic.ItemBuilder;
import net.brightblack9911.pcrCore.basic.Offer;

public class OfferManager
{
	private static OfferManager instance;
	
	private final List<Offer> OFFERS = Lists.newArrayList();

	public OfferManager() {
		loadShopOffers();
	}

	private void loadShopOffers() {
		registerOffer("DIEMENTOWY MIECZ 6/2", new ItemStack(Material.DIAMOND_SWORD, 1), new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD, 1)).addEnchant(Enchantment.DAMAGE_ALL, 6).addEnchant(Enchantment.FIRE_ASPECT, 2).build(), 25000);
		registerOffer("1 PERLA", new ItemStack(Material.ENDER_PEARL, 1), new ItemStack(Material.ENDER_PEARL, 1), 1000);
		registerOffer("DIAMENTOWY KILOF 6/3/3", new ItemStack(Material.DIAMOND_PICKAXE, 1), new ItemBuilder(new ItemStack(Material.DIAMOND_PICKAXE, 1)).addEnchant(Enchantment.DIG_SPEED, 6).addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3).addEnchant(Enchantment.DURABILITY, 3).build(), 20000);
		registerOffer("3 KOXOW", new ItemStack(Material.GOLDEN_APPLE, 3, (byte) 1), new ItemStack(Material.GOLDEN_APPLE, 3, (byte) 1), 2000);
		registerOffer("5 REFILI", new ItemStack(Material.GOLDEN_APPLE, 5), new ItemStack(Material.GOLDEN_APPLE, 5), 2000);
		registerOffer("16 BOYFARMEROW", new ItemStack(Material.ENDER_PORTAL_FRAME, 16), new ItemBuilder(new ItemStack(Material.ENDER_PORTAL_FRAME, 16)).setName("&f&lBOYFARMER").addLore("&aPostaw na ziemii by uzyc.").addEnchant(Enchantment.DURABILITY, 1).build(), 2400);
		registerOffer("MIKSTURA: SILA I, 8 MIN", new ItemStack(Material.POTION, 1, (byte) 8265), new ItemStack(Material.POTION, 1, (byte) 8265), 2000);
		//registerOffer("BEACON", new ItemStack(Material.BEACON, 1), new ItemStack(Material.BEACON, 1), 12000);
		registerOffer("5 BRODAWEK", new ItemStack(Material.NETHER_STALK, 5), new ItemStack(Material.NETHER_STALK, 5), 2000);
		registerOffer("Gildia", new ItemStack(Material.PAPER, 1), new ItemBuilder(new ItemStack(Material.PAPER)).setName("&cGildia").addEnchant(Enchantment.THORNS, 3).build(), 50000);
		registerOffer("LUK PUNCH", new ItemStack(Material.BOW, 1), new ItemBuilder(new ItemStack(Material.BOW, 1)).addEnchant(Enchantment.ARROW_KNOCKBACK, 2).build(), 2500);
		//registerOffer("KREATOR CUBOIDA", new ItemStack(Material.SPONGE, 1), new ItemBuilder(new ItemStack(Material.SPONGE, 1)).setName("&cKreator cuboida").addEnchant(Enchantment.THORNS, 1).build(), 15000);
	}

	public void registerOffer(String name, ItemStack icon, ItemStack buyed_item, int price) {
		OFFERS.add(new Offer(name, icon, buyed_item, price));
	}

	public List<Offer> getOffers() {
		return OFFERS;
	}

	public Offer getOfferByName(String name) {
		for (Offer o : OFFERS) {
			if (o.getName().equals(name)) return o;
		}
		return null;
	}

	public static OfferManager getInstance() {
		if (instance == null) instance = new OfferManager();
		return instance;
	}
}
