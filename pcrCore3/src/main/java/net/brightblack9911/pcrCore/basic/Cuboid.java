package net.brightblack9911.pcrCore.basic;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;

import com.google.common.collect.Sets;

public class Cuboid
{
	private String name;
	private UUID owner;
	private Location center;
	
	private Set<UUID> members = Sets.newHashSet();

	public Cuboid(String name, UUID owner) {
		this.setName(name);
		this.setOwner(owner);
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public Set<UUID> getMembers() {
		return members;
	}

	public void addMember(UUID uuid) {
		members.add(uuid);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getCenter() {
		return center;
	}

	public void setCenter(Location center) {
		this.center = center;
	}

//	public setMember(List<String> fromString) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
