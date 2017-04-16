package net.brightblack9911.pcrCore.basic;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.google.common.collect.Maps;

import net.brightblack9911.pcrCore.enums.Achievement;
import net.brightblack9911.pcrCore.enums.InventoryCat;
import net.brightblack9911.pcrCore.managers.CuboidManager;
import net.brightblack9911.pcrCore.managers.DropManager;
import net.brightblack9911.pcrCore.managers.MySQLManager;

public class User
{
	private final MySQLManager MYSQL = MySQLManager.getInstance();
	private final DropManager DROP = DropManager.getInstance();

	private UUID uuid;
	private String name;
	private Cuboid cuboid;
	private int coins;
	private long turboCoinsStart;
	private long turboCoinsTime;
	private Location lastLocation;
	private String lastMessageSender;
	private String lastRequestTeleport;
	private Long lastRequestTeleportTime;
	private String homeLocation;
	private boolean isFreeze;
	private Long onlineTime;
	private boolean hasOpenInventory;

	private HashMap<DropItem, Integer> drop_stat;

	private final Map<InventoryCat, Inventory> INVENTORYES = Maps.newHashMap();

	private final Map<String, Long> KITS = Maps.newHashMap();

	private final Map<String, Location> GENERATOR = Maps.newHashMap();

	private final Map<Achievement, Integer> ACHIEVEMENTS = Maps.newHashMap();

	public User(Player p) {
		this.uuid = p.getUniqueId();
		this.name = p.getName();
		this.coins = 200;
		this.drop_stat = Maps.newHashMap();
		for (DropItem d : DROP.getDrops()) {
			drop_stat.put(d, 0);
		}
	}

	public String getName() {
		return this.name;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public int getCoins() {
		return this.coins;
	}

	public void setCoins(int coins, boolean update) {
		this.coins = coins;
		if (update) save();
	}

	public long getTurboCoinsStart() {
		return this.turboCoinsStart;
	}

	public void setTurboCoinsStart(long turboDropStart) {
		this.turboCoinsStart = turboDropStart;
		save();
	}

	public long getTurboCoinsTime() {
		return this.turboCoinsTime;
	}

	public void setTurboCoinsTime(long turboDropTime) {
		this.turboCoinsTime = turboDropTime;
		save();
	}

	public Location getLastLocation() {
		return this.lastLocation;
	}

	public void setLastLocation(Location loc) {
		this.lastLocation = loc;
	}

	public String getLastMessageSender() {
		return this.lastMessageSender;
	}

	public void setLastMessageSender(String s) {
		this.lastMessageSender = s;
	}

	public long getLastRequestTeleportTime() {
		return this.lastRequestTeleportTime;
	}

	public void setLastRequestTeleportTime(long lastRequestTeleportTime) {
		this.lastRequestTeleportTime = lastRequestTeleportTime;
	}

	public String getLastRequestTeleport() {
		return lastRequestTeleport;
	}

	public void setLastRequestTeleport(String lastRequestTeleport) {
		this.lastRequestTeleport = lastRequestTeleport;
	}

	public String getHomeLocation() {
		return this.homeLocation;
	}

	public void setHomeLocation(String homeLocation, boolean update) {
		this.homeLocation = homeLocation;
		if (update) save();
	}

	public boolean isFreeze() {
		return isFreeze;
	}

	public void setFreeze(boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public Long getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}

	public boolean isHasOpenInventory() {
		return hasOpenInventory;
	}

	public void setHasOpenInventory(boolean hasOpenInventory) {
		this.hasOpenInventory = hasOpenInventory;
	}

	public Map<InventoryCat, Inventory> getInventorys() {
		return this.INVENTORYES;
	}

	public Map<String, Long> getKits() {
		return this.KITS;
	}

	public HashMap<DropItem, Integer> getDropStat() {
		return this.drop_stat;
	}

	public Map<String, Location> getGenerator() {
		return GENERATOR;
	}

	public Cuboid getCuboid() {
		return cuboid;
	}

	public void setCuboid(Cuboid cuboid) {
		this.cuboid = cuboid;
	}

	public boolean hasCuboid() {
		return cuboid != null;
	}

	public void getAchievement(Achievement achievement, int progress) {
		getAchievements().put(achievement, progress);
	}

	public void save() {
		try {
			ResultSet rs = MySQLManager.getInstance().executeQuery("SELECT * FROM `users` WHERE `uuid`= '" + this.uuid.toString() + "'");
			while (rs.next()) {
				MySQLManager.getInstance().executeUpdate("UPDATE `users` SET `coins` = '" + this.coins + "', `home` = '" + this.homeLocation + "', `turbocoinsstart` = '"
						+ this.turboCoinsStart + "', `turbocoinstime` = '" + this.turboCoinsTime + "' WHERE `uuid` = '" + this.uuid + "'");
				return;
			}
			MySQLManager.getInstance().executeUpdate("INSERT INTO `users` VALUES (null, '" + this.uuid + "', '" + this.name + "', '" + this.coins + "', '" + this.homeLocation
					+ "', '" + this.turboCoinsStart + "', '" + this.turboCoinsTime + "')");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void saveDropStat() {
	// try {
	// if (!MYSQL.querySql("SELECT * FROM `drop_stat` WHERE `uuid`= '" +
	// this.uuid.toString() + "'").existRecord()) {
	// for (DropItem d : DROP.getDrops()) {
	// MYSQL.updateSql("INSERT INTO `drop_stat` VALUES (null, '" + this.uuid +
	// "', '" + d.getName() + "', '" + this.getDropStat().get(d) + "')");
	// }
	// }
	// for (DropItem d : DROP.getDrops()) {
	// MYSQL.updateSql("UPDATE `drop_stat` SET `number` = '" +
	// this.getDropStat().get(d) + "' WHERE `uuid` = '" + this.uuid + "' AND
	// `name` = '" + d.getName() + "'");
	// }
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public Map<Achievement, Integer> getAchievements() {
		return ACHIEVEMENTS;
	}

}
