package net.brightblack9911.pcrCore.managers;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import net.brightblack9911.pcrCore.basic.CorePlugin;
import net.brightblack9911.pcrCore.basic.Cuboid;
import net.brightblack9911.pcrCore.utils.LocationUtils;
import net.brightblack9911.pcrCore.utils.StringUtils;

public class CuboidManager
{
	private static CuboidManager instance;

	private final Set<Cuboid> CUBOIDS = Sets.newHashSet();

	private final Set<String> INVITES = Sets.newHashSet();

	private final Settings settings = Settings.getInstance();

	public CuboidManager() {
		loadCuboids();
	}

	public void addCuboid(Cuboid cuboid) {
		CUBOIDS.add(cuboid);
		System.out.println("dodawanie cuba");
		MySQLManager.getInstance().openConnection();
		MySQLManager.getInstance().executeUpdate("INSERT INTO `cuboids` VALUES (null, '" + cuboid.getName() + "', '" + cuboid.getOwner().toString() + "', '"
				+ LocationUtils.serializationLocation(cuboid.getCenter()) + "', '')");
		MySQLManager.getInstance().closeConnection();
	}

	public void updateMembers(Cuboid cuboid) {
		List<String> members = Lists.newArrayList();
		for (UUID uuid : cuboid.getMembers()) {
			members.add(uuid.toString());
		}

		MySQLManager.getInstance().openConnection();
		MySQLManager.getInstance().executeUpdate("UPDATE `cuboids` SET `members` = '" + StringUtils.toString(members, false) + "' WHERE `name` = '" + cuboid.getName() + "'");
		MySQLManager.getInstance().closeConnection();
	}

	public void addInvite(String name) {
		INVITES.add(name);
	}

	public Set<Cuboid> getCuboids() {
		return CUBOIDS;
	}

	public Set<String> getInvites() {
		return INVITES;
	}

	public void loadCuboids() {
		MySQLManager.getInstance().openConnection();
		ResultSet rs = MySQLManager.getInstance().executeQuery("SELECT * FROM `cuboids`");
		System.out.println("sdfd ladowanie qwsdfsd");
		try {
			int i = 1;
			while (rs.next()) {
				System.out.println("Numer: " + i + " - ladowanie cubow");
				Cuboid cuboid = new Cuboid(rs.getString("name"), UUID.fromString(rs.getString("owner")));
				for (String name : StringUtils.fromString(rs.getString("members"))) {
					cuboid.addMember(UUID.fromString(name));
				}
				// hcuboid.setMember(StringUtils.fromString(rs.getString("members")));
				cuboid.setCenter(LocationUtils.deserializationLocation(rs.getString("center")));
				CUBOIDS.add(cuboid);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isInsideCuboid(Player p, Location location) {
		for (Cuboid cuboid : CUBOIDS) {
			if (location.getWorld().getName().equals(cuboid.getCenter().getWorld().getName())) {
				int bX = cuboid.getCenter().getBlockX();
				int bZ = cuboid.getCenter().getBlockZ();
				int bY = cuboid.getCenter().getBlockZ();
				int minX = bX-6; int maxX = bX+6; //IKSY
				int minZ = bZ-6; int maxZ = bZ+6; //ZETKI
				int minY = bY-7; int maxY = 256;
				int BlockX = location.getBlockX();
				int BlockZ = location.getBlockZ();
				int BlockY = location.getBlockZ();
				if( ((minX<BlockX) && (BlockX<maxX)) && ((minZ<BlockZ) && (BlockZ<maxZ)) && ((minY<BlockY) && (BlockY<maxY)) ){
						System.out.println(p.getPlayer().getName() + " " + cuboid.getName());
						return true;
				}
				
			}
			return false;
		}
		return false;

	}

	public boolean contains(Location loc) {
		if (loc == null) {
			System.out.println("loc null");
			return false;
		}
		Location w1p1 = LocationUtils.deserializationLocation(settings.wall1point1);
		Location w1p2 = LocationUtils.deserializationLocation(settings.wall1point2);

		int w1x1 = Math.min(w1p1.getBlockX(), w1p2.getBlockX());
		int w1x2 = Math.max(w1p1.getBlockX(), w1p2.getBlockX());

		int w1z1 = Math.min(w1p1.getBlockZ(), w1p2.getBlockZ());
		int w1z2 = Math.max(w1p1.getBlockZ(), w1p2.getBlockZ());

		// wall 2
		Location w2p1 = LocationUtils.deserializationLocation(settings.wall2point1);
		Location w2p2 = LocationUtils.deserializationLocation(settings.wall2point2);

		int w2x1 = Math.min(w2p1.getBlockX(), w2p2.getBlockX());
		int w2x2 = Math.max(w2p1.getBlockX(), w2p2.getBlockX());

		int w2z1 = Math.min(w2p1.getBlockZ(), w2p2.getBlockZ());
		int w2z2 = Math.max(w2p1.getBlockZ(), w2p2.getBlockZ());
		// System.out.println("1: " + (loc.getBlockX() < w1x1 && loc.getBlockX()
		// > w2x1));
		// System.out.println("2: " + (loc.getBlockZ() > w1z2 && loc.getBlockZ()
		// < w2z2));
		//
		// System.out.println("3: " + (loc.getBlockX() > w1x2 && loc.getBlockX()
		// < w2x2));
		// System.out.println("4: " + (loc.getBlockZ() > w1z1 && loc.getBlockZ()
		// < w2z1));

		if ((loc.getBlockX() > w2x1 && loc.getBlockX() < w2x2) && (loc.getBlockZ() > w2z1 && loc.getBlockZ() < w2z2)) {
			if (!((loc.getBlockX() >= w1x1 && loc.getBlockX() <= w1x2) && (loc.getBlockZ() >= w1z1 && loc.getBlockZ() <= w1z2))) {
				return true;
			}
		}
		return false;
	}

	public void paste(String schematicName, Location pasteLoc) {
		try {
			File dir = new File(CorePlugin.getInstance().getDataFolder(), "/schematics/" + schematicName);

			EditSession editSession = new EditSession(new BukkitWorld(pasteLoc.getWorld()), 999999999);
			editSession.enableQueue();

			SchematicFormat schematic = SchematicFormat.getFormat(dir);
			CuboidClipboard clipboard = schematic.load(dir);

			clipboard.paste(editSession, BukkitUtil.toVector(pasteLoc), true);
			editSession.flushQueue();
		}
		catch (DataException | IOException ex) {
			ex.printStackTrace();
		}
		catch (MaxChangedBlocksException ex) {
			ex.printStackTrace();
		}
	}

	public static CuboidManager getInstance() {
		if (instance == null) instance = new CuboidManager();
		return instance;
	}

}
