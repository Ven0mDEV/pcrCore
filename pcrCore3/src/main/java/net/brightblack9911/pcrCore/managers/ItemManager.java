package net.brightblack9911.pcrCore.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import net.brightblack9911.pcrCore.basic.ItemBuilder;

public class ItemManager
{
	private static ItemManager instance;

	public ItemStack separator;

	public ItemStack drop_reference;

	public ItemStack shop_reference;

	public ItemStack generator;

	public ItemStack boyFarmer;
	
	public ItemStack sandFarmer;

	public ItemStack steak;
	
	public ItemStack cased;
	// public ItemStack rocket;

	public ItemManager() {
		loadCustomItems();
		loadRecipes();
	}

	private void loadCustomItems() {
		separator = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 9)).setName(" ").build();

		drop_reference = new ItemBuilder(new ItemStack(Material.STONE, 1)).setName("&f&lLISTA DROPU").addLore("&8> &7Kliknij LPM...").build();

		shop_reference = new ItemBuilder(new ItemStack(Material.GOLD_BLOCK, 1)).setName("&f&lSKLEP ZA COINS").addLore("&8> &7Kupno za COINS.").addLore("&8> &7Kliknij LPM...").build();

		generator = new ItemBuilder(new ItemStack(Material.ENDER_STONE, 1)).setName("&f&lGENERATOR").addLore("&7Postaw na ziemii by uzyc.").addEnchant(Enchantment.THORNS, 1)
				.build();
		
		boyFarmer = new ItemBuilder(new ItemStack(Material.ENDER_PORTAL_FRAME, 1)).setName("&f&lBOYFARMER").addLore("&7Postaw na ziemii by uzyc.").addEnchant(Enchantment.THORNS, 1).build();

		sandFarmer = new ItemBuilder(new ItemStack(Material.NOTE_BLOCK, 1)).setName("&f&lSANDFARMER").addLore("&7Postaw na ziemii by uzyc.").addEnchant(Enchantment.THORNS, 1).build();
		
		steak = new ItemBuilder(new ItemStack(Material.GRILLED_PORK, 1)).setName("&f&lSTEAK").addLore("&7Nieskonczone miesko.").addEnchant(Enchantment.THORNS, 1).build();
		
		cased = new ItemBuilder(new ItemStack(Material.GRILLED_PORK, 1)).setName("&f&lCASE").addLore("&7Skrzynia z super itemami.").addEnchant(Enchantment.THORNS, 1).build();
		// ItemMeta im7 = boy.getItemMeta();
		// im7.addEnchant(Enchantment.THORNS, 1, true);
		// ArrayList<String> lores7 = new ArrayList<String>();
		// lores7.add(Util.formatColor("&aPostaw na ziemii by uzyc."));
		// im7.setDisplayName(Util.formatColor("&f&lBOYFARMER"));
		// im7.setLore(lores7);
		// boy.setItemMeta(im7);

		// rocket = new ItemBuilder(new ItemStack(Material.FIREWORK,
		// 1)).setName("&f&lRAKIETA").addEnchant(Enchantment.THORNS, 1).build();

	}

	@SuppressWarnings("deprecation")
	private void loadRecipes() {
		ShapedRecipe e = new ShapedRecipe(new ItemStack(Material.ENDER_CHEST, 1));
		e.shape("GGG", "GSG", "GGG");
		e.setIngredient('G', Material.OBSIDIAN);
		e.setIngredient('S', Material.ENDER_PEARL);
		Bukkit.addRecipe(e);

		ShapedRecipe s = new ShapedRecipe(generator);
		s.shape("GGG", "SPS", "GGG");
		s.setIngredient('G', Material.STONE);
		s.setIngredient('S', Material.REDSTONE);
		s.setIngredient('P', Material.PISTON_BASE);
		Bukkit.addRecipe(s);
		
		ShapedRecipe boyFarmerRecipe = new ShapedRecipe(boyFarmer);
		boyFarmerRecipe.shape("OOO", "OGO", "OOO");
		boyFarmerRecipe.setIngredient('O', Material.OBSIDIAN);
		boyFarmerRecipe.setIngredient('G', Material.GOLDEN_APPLE);
		Bukkit.addRecipe(boyFarmerRecipe);
		
		ShapedRecipe sandFarmerRecipe = new ShapedRecipe(sandFarmer);
		sandFarmerRecipe.shape("SSS", "SGS", "SSS");
		sandFarmerRecipe.setIngredient('S', Material.SAND);
		sandFarmerRecipe.setIngredient('G', Material.GOLDEN_APPLE);
		Bukkit.addRecipe(sandFarmerRecipe);
		
		ShapedRecipe steakRecipe = new ShapedRecipe(steak);
		steakRecipe.shape("GGG", "GPG", "GGG");
		steakRecipe.setIngredient('P', Material.GRILLED_PORK);
		steakRecipe.setIngredient('G', Material.GOLD_INGOT);
		Bukkit.addRecipe(steakRecipe);

		// ShapedRecipe r = new ShapedRecipe(rocket);
		// r.shape("SSS", "SAS", "GDG");
		// r.setIngredient('G', Material.PISTON_BASE);
		// r.setIngredient('S', Material.ANVIL);
		// r.setIngredient('D', Material.FLINT_AND_STEEL);
		// r.setIngredient('A', Material.GOLDEN_APPLE, (byte) 1);
		// Bukkit.addRecipe(r);
	}

	public static ItemManager getInstance() {
		if (instance == null) instance = new ItemManager();
		return instance;
	}
}
