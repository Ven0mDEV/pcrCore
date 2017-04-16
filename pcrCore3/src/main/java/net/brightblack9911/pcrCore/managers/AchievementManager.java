package net.brightblack9911.pcrCore.managers;

import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.enums.Achievement;

public class AchievementManager
{
	private static AchievementManager instance;

	public void getAchievement(Player player, Achievement achievement, int progress) {
		// try (Jedis jedis =
		// JedisManager.getInstance().getJedisPool().getResource()) {
		// jedis.hset(player.getUniqueId().toString(), achievement.getName(),
		// Integer.toString(progress));
		// }

		MySQLManager.getInstance().openConnection();
		MySQLManager.getInstance().executeUpdate("INSERT INTO `achievements` VALUES (null, '" + player.getUniqueId() + "', '" + achievement.getName() + "', '" + progress + "')");
		MySQLManager.getInstance().closeConnection();

		UserManager.getInstance().getUser(player).getAchievement(achievement, progress);
	}

	public Achievement getAchievementByName(String name) {
		for (Achievement achievemnt : Achievement.values()) {
			if (achievemnt.getName().equalsIgnoreCase(name)) {
				return achievemnt;
			}
		}
		return null;
	}

	public static AchievementManager getInstance() {
		if (instance == null) instance = new AchievementManager();
		return instance;
	}
}
