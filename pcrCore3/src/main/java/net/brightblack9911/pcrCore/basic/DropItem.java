package net.brightblack9911.pcrCore.basic;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.brightblack9911.pcrCore.managers.DropManager;

public class DropItem
{
	private String name;

	private Material material;
	private Material dropFrom;

	private final List<Material> tools = Lists.newArrayList();

	private final Set<UUID> disabled = Sets.newHashSet();

	private boolean fortune;
	private boolean heightStatus;
	private boolean toolsStatus;
	private boolean drop_with_name;

	private double chance;

	private int minHeight;
	private int maxHeight;
	private int amountMin;
	private int amountMax;

	public DropItem(String name, Material m, double chance, boolean fortune, Material dropFrom, boolean heightStatus, int minHeight, int maxHeight, boolean toolsStatus, int amountMin,
			int amountMax, boolean drop_with_name, Material... tools) {
		for (Material mat : tools) {
			this.tools.add(mat);
		}

		this.name = name;
		this.material = m;
		this.dropFrom = dropFrom;
		this.fortune = fortune;
		this.heightStatus = heightStatus;
		this.toolsStatus = toolsStatus;
		this.chance = chance;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.amountMin = amountMin;
		this.amountMax = amountMax;
		this.drop_with_name = drop_with_name;
	}

	public String getName() {
		return this.name;
	}

	public double getChance() {
		return this.chance;
	}

	public Material getMaterial() {
		return this.material;
	}

	public boolean getFortuneStatus() {
		return this.fortune;
	}

	public Material getDropFrom() {
		return this.dropFrom;
	}

	public int getMinHeight() {
		return this.minHeight;
	}

	public int getMaxHeight() {
		return this.maxHeight;
	}

	public boolean getHeightStatus() {
		return this.heightStatus;
	}

	public boolean getToolsStatus() {
		return this.toolsStatus;
	}

	public int getAmountMin() {
		return this.amountMin;
	}

	public int getAmountMax() {
		return this.amountMax;
	}

	public boolean getDropWithName() {
		return this.drop_with_name;
	}

	public List<Material> getTools() {
		return tools;
	}

	public Set<UUID> getDisabled() {
		return this.disabled;
	}

	public void changeStatus(UUID uuid) {
		if (this.disabled.contains(uuid)) {
			this.disabled.remove(uuid);
		} else {
			this.disabled.add(uuid);
		}
	}

	public boolean isDisabled(UUID uuid) {
		return this.disabled.contains(uuid);
	}

	public static DropItem getDropByName(String name) {
		for (DropItem d : DropManager.getInstance().getDrops()) {
			if (d.getName().equalsIgnoreCase(name)) return d;
		}
		return null;
	}
}
