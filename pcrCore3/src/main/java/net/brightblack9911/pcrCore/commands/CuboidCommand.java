package net.brightblack9911.pcrCore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.brightblack9911.pcrCore.basic.Cuboid;
import net.brightblack9911.pcrCore.basic.User;
import net.brightblack9911.pcrCore.managers.CuboidManager;
import net.brightblack9911.pcrCore.managers.UserManager;

public class CuboidCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player) cs;
		User u = UserManager.getInstance().getUser(p);
		Cuboid c = u.getCuboid();
		
		for (Cuboid cuboid : CuboidManager.getInstance().getCuboids()) {
			System.out.println(cuboid.getName());
		}
		
		if (!u.hasCuboid()) {
			//TODO: Add message
			return true;
		}
		
		switch (args[0]) {
			case "zarzadzaj":
				
				break;
			case "zapros":
				if (c.getMembers().size() >= 3) {
					//TODO: Add message
					return true;
				}
			case "wyrzuc":
				//if (!c.getMembers().contains()) {
				//	return true;
				//}
				
				//c.removeMember();
				return true;
				//break;
		}
		
		return false;
	}

}
