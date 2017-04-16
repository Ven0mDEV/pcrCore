package net.brightblack9911.pcrCore.basic;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import net.brightblack9911.pcrCore.commands.AchievementCommand;
import net.brightblack9911.pcrCore.commands.AddCoinsCommand;
import net.brightblack9911.pcrCore.commands.BackCommand;
import net.brightblack9911.pcrCore.commands.BroadCastCommand;
import net.brightblack9911.pcrCore.commands.ChatCommand;
import net.brightblack9911.pcrCore.commands.CoreReloadCommand;
import net.brightblack9911.pcrCore.commands.CuboidCommand;
import net.brightblack9911.pcrCore.commands.DropCommand;
import net.brightblack9911.pcrCore.commands.EnderChestCommand;
import net.brightblack9911.pcrCore.commands.ExChangeCoinCommand;
import net.brightblack9911.pcrCore.commands.FeedCommand;
import net.brightblack9911.pcrCore.commands.FlyCommand;
import net.brightblack9911.pcrCore.commands.FreezeCommand;
import net.brightblack9911.pcrCore.commands.HatCommand;
import net.brightblack9911.pcrCore.commands.HeadCommand;
import net.brightblack9911.pcrCore.commands.HelpCommand;
import net.brightblack9911.pcrCore.commands.HelpOpCommand;
import net.brightblack9911.pcrCore.commands.HomeCommand;
import net.brightblack9911.pcrCore.commands.InvSeeCommand;
import net.brightblack9911.pcrCore.commands.KitCommand;
import net.brightblack9911.pcrCore.commands.MieskoCommand;
import net.brightblack9911.pcrCore.commands.OwnersCommand;
import net.brightblack9911.pcrCore.commands.PayCommand;
import net.brightblack9911.pcrCore.commands.RePlayCommand;
import net.brightblack9911.pcrCore.commands.RepairCommand;
import net.brightblack9911.pcrCore.commands.SafeCommand;
import net.brightblack9911.pcrCore.commands.SetHomeCommand;
import net.brightblack9911.pcrCore.commands.SetRandomTpCommand;
import net.brightblack9911.pcrCore.commands.SetSlotsCommand;
import net.brightblack9911.pcrCore.commands.SetZoneCommand;
import net.brightblack9911.pcrCore.commands.ShopCommand;
import net.brightblack9911.pcrCore.commands.SkanCommand;
import net.brightblack9911.pcrCore.commands.SpawnCommand;
import net.brightblack9911.pcrCore.commands.TellCommand;
import net.brightblack9911.pcrCore.commands.TpAcceptCommand;
import net.brightblack9911.pcrCore.commands.TpDenyCommand;
import net.brightblack9911.pcrCore.commands.TpHereCommand;
import net.brightblack9911.pcrCore.commands.TpaCommand;
import net.brightblack9911.pcrCore.commands.VIPCommand;
import net.brightblack9911.pcrCore.commands.WorkBenchCommand;
import net.brightblack9911.pcrCore.listeners.AsyncPlayerChatListener;
import net.brightblack9911.pcrCore.listeners.BlockBreakListener;
import net.brightblack9911.pcrCore.listeners.BlockPlaceListener;
import net.brightblack9911.pcrCore.listeners.EntityDamageListener;
import net.brightblack9911.pcrCore.listeners.InventoryClickListener;
import net.brightblack9911.pcrCore.listeners.InventoryCloseListener;
import net.brightblack9911.pcrCore.listeners.PlayerCommandPreprocessListener;
import net.brightblack9911.pcrCore.listeners.PlayerDeathListener;
import net.brightblack9911.pcrCore.listeners.PlayerDropItemListener;
import net.brightblack9911.pcrCore.listeners.PlayerInteractListener;
import net.brightblack9911.pcrCore.listeners.PlayerItemConsumeListener;
import net.brightblack9911.pcrCore.listeners.PlayerJoinListener;
import net.brightblack9911.pcrCore.listeners.PlayerLoginListener;
import net.brightblack9911.pcrCore.listeners.PlayerMoveListener;
import net.brightblack9911.pcrCore.listeners.PlayerQuitListener;
import net.brightblack9911.pcrCore.listeners.PlayerRespawnListener;
import net.brightblack9911.pcrCore.listeners.PrepareItemCraftListener;
import net.brightblack9911.pcrCore.listeners.ServerListPingListener;
import net.brightblack9911.pcrCore.listeners.SignChangeListener;
import net.brightblack9911.pcrCore.listeners.WeatherChangeListener;
import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.managers.RandomTpManager;
import net.brightblack9911.pcrCore.managers.Settings;
import net.brightblack9911.pcrCore.tasks.AutoMessageTask;
import net.brightblack9911.pcrCore.tasks.MySQLKeepAliveTask;

public class CorePlugin extends JavaPlugin
{
	private static CorePlugin instance;
	private Random random;

	@Override
	public void onEnable() {
		instance = this;
		random = new Random();

		saveDefaultConfig();
		Settings.getInstance().loadConfiguration();

		registerListeners();

		registerCommands();

		//MySQLManager.getInstance();
		
		RandomTpManager.getInstance();

		MessagesManager.getInstance().loadMessages();

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new MySQLKeepAliveTask(), 0L, 20 * 100);
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AutoMessageTask(), 0L, 20 * Settings.getInstance().autoMsgInvertal);

		setupWorldEdit();
	}
	

	// ## register listeners ##
	private void registerListeners() {
		PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new AsyncPlayerChatListener(), this);
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new BlockPlaceListener(), this);
		pm.registerEvents(new EntityDamageListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new InventoryCloseListener(), this);
		pm.registerEvents(new PlayerCommandPreprocessListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new PlayerDropItemListener(), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new PlayerItemConsumeListener(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerLoginListener(), this);
		pm.registerEvents(new PlayerMoveListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new ServerListPingListener(), this);
		pm.registerEvents(new SignChangeListener(), this);
		pm.registerEvents(new WeatherChangeListener(), this);
		pm.registerEvents(new PlayerRespawnListener(), this);
		pm.registerEvents(new PrepareItemCraftListener(), this);
	}
	// ##

	// ## register commands ##
	private void registerCommands() {
		getCommand("achievement").setExecutor(new AchievementCommand());
		getCommand("back").setExecutor(new BackCommand());
		getCommand("broadcast").setExecutor(new BroadCastCommand());
		getCommand("chat").setExecutor(new ChatCommand());
		getCommand("corereload").setExecutor(new CoreReloadCommand());
		getCommand("cuboid").setExecutor(new CuboidCommand());
		getCommand("drop").setExecutor(new DropCommand());
		getCommand("enderchest").setExecutor(new EnderChestCommand());
		getCommand("exchangecoin").setExecutor(new ExChangeCoinCommand());
		getCommand("feed").setExecutor(new FeedCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("freeze").setExecutor(new FreezeCommand());
		getCommand("hat").setExecutor(new HatCommand());
		getCommand("head").setExecutor(new HeadCommand());
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("helpop").setExecutor(new HelpOpCommand());
		getCommand("home").setExecutor(new HomeCommand());
		getCommand("invsee").setExecutor(new InvSeeCommand());
		getCommand("kit").setExecutor(new KitCommand());
		getCommand("owners").setExecutor(new OwnersCommand());
		getCommand("pay").setExecutor(new PayCommand());
		getCommand("repair").setExecutor(new RepairCommand());
		getCommand("reply").setExecutor(new RePlayCommand());
		getCommand("sethome").setExecutor(new SetHomeCommand());
		getCommand("setrandomtp").setExecutor(new SetRandomTpCommand());
		getCommand("setslots").setExecutor(new SetSlotsCommand());
		getCommand("setzone").setExecutor(new SetZoneCommand());
		getCommand("shop").setExecutor(new ShopCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("tell").setExecutor(new TellCommand());
		getCommand("tpaccept").setExecutor(new TpAcceptCommand());
		getCommand("tpa").setExecutor(new TpaCommand());
		getCommand("tpdeny").setExecutor(new TpDenyCommand());
		getCommand("tphere").setExecutor(new TpHereCommand());
		getCommand("vip").setExecutor(new VIPCommand());
		getCommand("workbench").setExecutor(new WorkBenchCommand());
		getCommand("addcoins").setExecutor(new AddCoinsCommand());
		getCommand("miesko").setExecutor(new MieskoCommand());
		getCommand("skanuj").setExecutor(new SkanCommand());
		getCommand("safe").setExecutor(new SafeCommand());
	}
	// ##

	private WorldEditPlugin setupWorldEdit() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldEdit");
		if (plugin == null || !(plugin instanceof WorldEditPlugin)) return null;
		return (WorldEditPlugin) plugin;
	}

	public static CorePlugin getInstance() {
		return instance;
	}

	public Random getRandom() {
		return random;
	}
}
