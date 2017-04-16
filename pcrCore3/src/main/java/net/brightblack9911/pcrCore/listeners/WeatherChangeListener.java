package net.brightblack9911.pcrCore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener
{
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent ev) {
		if (ev.toWeatherState()) {
			ev.setCancelled(true);
		}
	}
}
