package me.cassayre.florian.Absorption.team;

import java.util.ArrayList;
import java.util.List;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;

public enum TeamColor {
	PURPLE("Violet", ChatColor.DARK_PURPLE, Material.WOOL, DyeColor.PURPLE.getData()),
	ORANGE("Orange", ChatColor.GOLD, Material.WOOL, DyeColor.ORANGE.getData()),
	GREEN("Vert", ChatColor.GREEN, Material.WOOL, DyeColor.GREEN.getData()),
	BLUE("Bleu", ChatColor.DARK_BLUE, Material.WOOL, DyeColor.BLUE.getData());
	
	private final String NAME;
	private final ChatColor COLOR;
	private final Material MATERIAL;
	private final int DATA;
	
	private List<GamePlayer> members = new ArrayList<>();
	
	private TeamColor(String name, ChatColor color, Material material, int data) {
		NAME = name;
		COLOR = color;
		MATERIAL = material;
		DATA = data;
	}

	public String getName() {
		return NAME;
	}

	public ChatColor getColor() {
		return COLOR;
	}

	public Material getMaterial() {
		return MATERIAL;
	}

	public int getData() {
		return DATA;
	}
	
	public boolean addMember(GamePlayer player) {
		if(members.size() < Math.ceil(Absorption.get().getGame().getMaxPlayers() / 4)) {
			members.add(player);
			return true;
		}
		return false;
	}
	
	public void removeMember(GamePlayer player) {
		members.remove(player);
	}
	
	public boolean contains(GamePlayer player) {
		return members.contains(player);
	}
	
	public static TeamColor whereIs(GamePlayer player) {
		TeamColor team = null;
		if(PURPLE.contains(player))
			team = PURPLE;
		if(ORANGE.contains(player))
			team = ORANGE;
		if(GREEN.contains(player))
			team = GREEN;
		if(BLUE.contains(player))
			team = BLUE;
		return team;
	}
}
