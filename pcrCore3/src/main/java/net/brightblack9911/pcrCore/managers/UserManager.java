package net.brightblack9911.pcrCore.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import net.brightblack9911.pcrCore.basic.DropItem;
import net.brightblack9911.pcrCore.basic.Kit;
import net.brightblack9911.pcrCore.basic.Teleport;
import net.brightblack9911.pcrCore.basic.User;
import redis.clients.jedis.Jedis;

public class UserManager
{
	private static UserManager instance;

	private final MySQLManager MYSQL = MySQLManager.getInstance();

	private final List<User> USERS = Lists.newArrayList();

	public void loadUser(Player p) {
		if (getUser(p) == null) {
			USERS.add(new User(p));
		}
		User u = getUser(p);
		try {
			MySQLManager.getInstance().openConnection();
			ResultSet rs = MySQLManager.getInstance().executeQuery("SELECT * FROM `users` WHERE `uuid`= '" + p.getUniqueId() + "'");
			if (rs.next()) {
				System.out.println("> Loading player: " + p.getName());
				u.setCoins(rs.getInt("coins"), false);
				u.setHomeLocation(rs.getString("home"), false);
				u.setTurboCoinsStart(rs.getLong("turbocoinstart"));
				u.setTurboCoinsTime(rs.getLong("turbocoinstime"));
			}
			MySQLManager.getInstance().closeConnection();
			// mysql.closeResources(rs, null);
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		try {
			ResultSet rs2 = MYSQL.querySql("SELECT * FROM `drop_stat` WHERE `uuid`= '" + p.getUniqueId() + "'").getResult();
			while (rs2.next()) {
				u.getDropStat().put(DropItem.getDropByName(rs2.getString("name")), rs2.getInt("number"));
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		for (Kit k : KitManager.getInstance().getKits()) {
			try (Jedis j = JedisManager.getInstance().getJedisPool().getResource()) {
				if (j.hget(p.getUniqueId().toString(), k.getName()) == null) continue;
				u.getKits().put(k.getName(), Long.parseLong(j.hget(p.getUniqueId().toString(), k.getName())));
			}
		}
		MySQLManager.getInstance().openConnection();
		ResultSet rs = MySQLManager.getInstance().executeQuery("SELECT * FROM `achievements` WHERE `uuid`= '" + p.getUniqueId() + "'");

		try {
			while (rs.next()) {
				u.getAchievement(AchievementManager.getInstance().getAchievementByName(rs.getString("name")), rs.getInt("progress"));

			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clearMemory(Player p) {
		USERS.remove(getUser(p));
		Teleport.getTeleportsPlayer().remove(p.getName());
	}

	public User getUser(Player p) {
		for (User u : USERS) {
			if (u.getUUID().equals(p.getUniqueId())) return u;
		}
		return null;
	}

	public static UserManager getInstance() {
		if (instance == null) instance = new UserManager();
		return instance;
	}
}
