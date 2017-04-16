package net.brightblack9911.pcrCore.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import net.brightblack9911.pcrCore.managers.RandomTpManager;

public class LocationUtils
{
	public static Location deserializationLocation(String s) {
		World world = Bukkit.getWorld(s.split(";")[0]);
		double x = Double.valueOf(s.split(";")[1]);
		double y = Double.valueOf(s.split(";")[2]);
		double z = Double.valueOf(s.split(";")[3]);
		float pitch = Float.valueOf(s.split(";")[5]);
		float yaw = Float.valueOf(s.split(";")[4]);
		return new Location(world, x, y, z, yaw, pitch);
	}

	public static String serializationLocation(Location loc) {
		return loc.getWorld().getName() + ";" + loc.getBlockX() + ";" + loc.getBlockY() + ";" + loc.getBlockZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
	}

	public static boolean isRandomTpBlock(Material material, Block clicked) {
		Location block = clicked.getLocation().add(1.0, 0.0, 0.0);
		Location block2 = clicked.getLocation().add(-1.0, 0.0, 0.0);
		Location block3 = clicked.getLocation().add(0.0, 0.0, 1.0);
		Location block4 = clicked.getLocation().add(0.0, 0.0, -1.0);
		if (block.getBlock().getType() == material || block2.getBlock().getType() == material || block3.getBlock().getType() == material
				|| block4.getBlock().getType() == material) {
			if (RandomTpManager.getInstance().getLocations().contains(block) || RandomTpManager.getInstance().getLocations().contains(block2)
					|| RandomTpManager.getInstance().getLocations().contains(block3) || RandomTpManager.getInstance().getLocations().contains(block4)) {

				return true;
			}
		}
		return false;
	}
}
