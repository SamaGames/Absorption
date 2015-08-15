package me.cassayre.florian.Absorption.block;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import me.cassayre.florian.Absorption.team.TeamColor;
import me.cassayre.florian.Absorption.utils.ParticleEffect;

public class ArenaBlock {

	private final World WORLD = Bukkit.getWorlds().get(0);
	private final int X;
	private final int Y;
	private final int Z;
	private final Block BLOCK;
	
	private final Material BASE_MATERIAL;
	private final int BASE_DATA;
	
	private TeamColor color = null;
	
	public ArenaBlock(int x, int y, int z) {
		X = x;
		Y = y;
		Z = z;
		
		BLOCK = WORLD.getBlockAt(X, Y, Z);
		
		BASE_MATERIAL = BLOCK.getType();
		BASE_DATA = BLOCK.getData();
	}
	
	public boolean isColored() {
		return color != null;
	}
	
	public TeamColor getColor() {
		return color;
	}
	
	public void colorBlock(TeamColor color) {
		this.color = color;
		if(color == null) {
			restoreBlock();
			return;
		}
		BLOCK.setType(color.getMaterial());
		BLOCK.setData((byte) color.getData());
		
		// TODO Improvements to do in effects
		ParticleEffect.WATER_WAKE.display(new Vector(0, Math.random(), 0), 1, BLOCK.getLocation().add(0, 1, 0), 1);
		ParticleEffect.CLOUD.display(new ParticleEffect.OrdinaryColor(255, 0, 255), BLOCK.getLocation().add(0, 1, 0), 1);
	}
	
	public void restoreBlock() {
		this.color = null;
		BLOCK.setType(BASE_MATERIAL);
		BLOCK.setData((byte) BASE_DATA);
	}
}
