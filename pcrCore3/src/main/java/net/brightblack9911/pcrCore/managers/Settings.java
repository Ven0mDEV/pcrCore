package net.brightblack9911.pcrCore.managers;

import net.brightblack9911.pcrCore.basic.CorePlugin;

public class Settings
{
	private static Settings instance;

	private final CorePlugin C = CorePlugin.getInstance();

	public String sql_host;
	public int sql_port;
	public String sql_base;
	public String sql_user;
	public String sql_pass;
	public int autoMsgInvertal;
	//public String checkRoom;
	public boolean allowKits;
	public boolean allowShop;
	public int slots;
	public String motd;
	public String wall1point1;
	public String wall1point2;

	public String wall2point1;
	public String wall2point2;
	// public Boolean allowSpectator;
	// public String spectatorSpawn;
	// public String pandoraLocation;
	// public String arena_spawn1;
	// public String arena_spawn2;

	public void loadConfiguration() {
		sql_host = C.getConfig().getString("config.mysql.host");
		sql_port = C.getConfig().getInt("config.mysql.port");
		sql_base = C.getConfig().getString("config.mysql.database");
		sql_user = C.getConfig().getString("config.mysql.user");
		sql_pass = C.getConfig().getString("config.mysql.pass");
		autoMsgInvertal = C.getConfig().getInt("config.autoMsgInterval");
		//checkRoom = c.getConfig().getString("config.checkRoom");
		allowKits = C.getConfig().getBoolean("config.allowKits");
		allowShop = C.getConfig().getBoolean("config.allowShop");
		slots = C.getConfig().getInt("config.slots");
		motd = C.getConfig().getString("config.motd");
		wall1point1 = C.getConfig().getString("config.zone.wall1.point1");
		wall1point2 = C.getConfig().getString("config.zone.wall1.point2");
		
		wall2point1 = C.getConfig().getString("config.zone.wall2.point1");
		wall2point2 = C.getConfig().getString("config.zone.wall2.point2");
		// allowSpectator = c.getConfig().getBoolean("config.allowSpectator");
		// spectatorSpawn = c.getConfig().getString("config.spectatorSpawn");
		// pandoraLocation = c.getConfig().getString("config.pandoraLocation");
		// arena_spawn1 = c.getConfig().getString("config.arena.spawn1");
		// arena_spawn2 = c.getConfig().getString("config.arena.spawn2");
	}

	public static Settings getInstance() {
		if (instance == null) instance = new Settings();
		return instance;
	}
}
