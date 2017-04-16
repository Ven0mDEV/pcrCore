package net.brightblack9911.pcrCore.managers;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import net.brightblack9911.pcrCore.utils.LocationUtils;
import redis.clients.jedis.Jedis;

public class RandomTpManager
{
	private static RandomTpManager instance;

	private final List<Location> LOCATIONS = Lists.newArrayList();

	public RandomTpManager() {
		loadLocations();
	}

	private void loadLocations() {
		try (Jedis jedis = JedisManager.getInstance().getJedisPool().getResource()) {
			for (String loc : jedis.lrange("randomtp", 0, -1)) {
				LOCATIONS.add(LocationUtils.deserializationLocation(loc));
			}
		}

	}

	public void addLocation(Location loc) {
		LOCATIONS.add(loc);

		try (Jedis jedis = JedisManager.getInstance().getJedisPool().getResource()) {
			jedis.lpush("randomtp", LocationUtils.serializationLocation(loc));
		}

	}

	public List<Location> getLocations() {
		return LOCATIONS;
	}

	public void randomTeleport(Player p, Location center, int min, int max) {
		double xmax = center.getX() - max;
		double xmin = center.getX() + max;
		double zmax = center.getZ() - max;
		double zmin = center.getZ() + max;

		int randomX = (int) ((Math.random() * (xmax - xmin + 1.0D)) + xmin);
		int randomZ = (int) ((Math.random() * (zmax - zmin + 1.0D)) + zmin);

		Location loc = new Location(center.getWorld(), randomX, center.getWorld().getHighestBlockYAt(randomX, randomZ), randomZ);
		
		p.teleport(loc);
	}
	
	public static RandomTpManager getInstance() {
		if (instance == null) instance = new RandomTpManager();
		return instance;
	}

}
