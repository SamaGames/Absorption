package me.cassayre.florian.Absorption.team;

import java.util.ArrayList;
import java.util.List;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.utils.ParticleEffect;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;

public enum TeamColor {
	PURPLE("Violet", ChatColor.DARK_PURPLE, Material.WOOL, DyeColor.PURPLE.getData(), new ParticleEffect.OrdinaryColor(100, 5, 200)),
	ORANGE("Orange", ChatColor.GOLD, Material.WOOL, DyeColor.ORANGE.getData(), new ParticleEffect.OrdinaryColor(255, 115, 0)),
	GREEN("Vert", ChatColor.GREEN, Material.WOOL, DyeColor.GREEN.getData(), new ParticleEffect.OrdinaryColor(15, 200, 30)),
	BLUE("Bleu", ChatColor.DARK_BLUE, Material.WOOL, DyeColor.BLUE.getData(), new ParticleEffect.OrdinaryColor(0, 100, 255));
	
	private final String NAME;
	private final ChatColor COLOR;
	private final Material MATERIAL;
	private final int DATA;
	private final ParticleEffect.ParticleColor PARTICLE_COLOR;
	
	private List<GamePlayer> members = new ArrayList<>();
	
	private TeamColor(String name, ChatColor color, Material material, int data, ParticleEffect.ParticleColor particleColor) {
		NAME = name;
		COLOR = color;
		MATERIAL = material;
		DATA = data;
		PARTICLE_COLOR = particleColor;
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
	
	public int getSize() {
		return members.size();
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
	
	public static void addSomewhere(GamePlayer player) {
		TeamColor best = TeamColor.PURPLE;
		if(TeamColor.ORANGE.getSize() < best.getSize()) best = TeamColor.ORANGE;
		if(TeamColor.GREEN.getSize() < best.getSize()) best = TeamColor.GREEN;
		if(TeamColor.BLUE.getSize() < best.getSize()) best = TeamColor.BLUE;
		
		best.addMember(player);
	}
	
	public ParticleEffect.ParticleColor getParticleColor() {
		return PARTICLE_COLOR;
	}
}
