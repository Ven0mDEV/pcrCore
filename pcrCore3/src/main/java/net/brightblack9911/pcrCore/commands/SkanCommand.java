package net.brightblack9911.pcrCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.brightblack9911.pcrCore.utils.Utils;

public class SkanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg, String[] args) {
		if(cs.hasPermission("core.skan")){
			World w = Bukkit.getWorld("world");
			for (Chunk c : w.getLoadedChunks()) {
				int cx = c.getX() << 4;
				int cz = c.getZ() << 4;
				for (int x = cx; x < cx + 16; x++) {
					for (int z = cz; z < cz + 16; z++) {
						for (int y = 0; y < 128; y++) {
							Block blokloc = w.getBlockAt(x, y, z);
							if (blokloc.getType() == Material.CHEST || blokloc.getType() == Material.TRAPPED_CHEST) {
								Utils.sendMsg(cs, " &cTyp: &f" + blokloc.getType() + " &cLocation: &fx="+ blokloc.getLocation().getX() + " y=" + blokloc.getLocation().getY() + " z=" + blokloc.getLocation().getZ());
							}
						}
					}
				}
			}
		    /*World w = Bukkit.getWorld("world");
		    for (Chunk c : w.getLoadedChunks()) {
		      for (BlockState block : c.getTileEntities()) {
		        if ((block instanceof Chest))
		        {
		          Chest chest = (Chest)block;
		          Utils.sendMsg(cs, "Block name:" + chest.getType() + "Location: " + chest.getLocation());
		        }
		      }
		    }*/
		} else {
			Utils.sendMsg(cs, " &cNie posiadasz uprawnien!");
			return true;
		}
		return false;
	}

}
