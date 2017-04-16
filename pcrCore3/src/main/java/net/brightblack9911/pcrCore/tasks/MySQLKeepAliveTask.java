package net.brightblack9911.pcrCore.tasks;

import net.brightblack9911.pcrCore.managers.MySQLManager;

public class MySQLKeepAliveTask implements Runnable
{
	@Override
	public void run() {
		try {
			MySQLManager.getInstance().executeUpdate("DO 1");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
