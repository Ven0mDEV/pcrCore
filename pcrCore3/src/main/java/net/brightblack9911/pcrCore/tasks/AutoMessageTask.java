package net.brightblack9911.pcrCore.tasks;

import net.brightblack9911.pcrCore.managers.MessagesManager;
import net.brightblack9911.pcrCore.utils.Utils;

public class AutoMessageTask implements Runnable
{
	private final MessagesManager mm = MessagesManager.getInstance();
	private int next = 0;

	@Override
	public void run() {
		if (mm.autoMessage == null || (next == mm.autoMessage.size())) next = 0;
		Utils.bcMessage(mm.autoMessagePrefix + mm.autoMessage.get(next));
		next++;
	}

}
