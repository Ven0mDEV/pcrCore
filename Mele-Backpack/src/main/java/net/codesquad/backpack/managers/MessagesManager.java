/**
 * 
 */
package net.codesquad.backpack.managers;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.codesquad.backpack.BackPackPlugin;

/**
 * @author nano3.
 *
 */
public class MessagesManager
{
	private static MessagesManager instance;
	
	private final Map<String, String> MESSAGES = Maps.newHashMap();
	
	public MessagesManager() {
		load();
	}
	
	public void load() {
		getMessages().clear();
		for (String path : BackPackPlugin.getInstance().getConfig().getConfigurationSection("messages").getKeys(false)) {
			getMessages().put(path, BackPackPlugin.getInstance().getConfig().getString("messages." + path));
			
		}
	}
	
	public String getMessage(String path) { 
		for (Entry<String, String> message : getMessages().entrySet()) {
			if (message.getKey().equals(path)) return message.getValue(); 
		}
		
		return path; 
	}
	
	private Map<String, String> getMessages() {
		return MESSAGES;
	}
	
	public static MessagesManager getInstance() {
		if (instance == null) instance = new MessagesManager();
		return instance;
	}
}
