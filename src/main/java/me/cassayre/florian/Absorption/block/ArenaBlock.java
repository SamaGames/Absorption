package me.cassayre.florian.Absorption.block;

import me.cassayre.florian.Absorption.team.TeamColor;
import me.cassayre.florian.Absorption.utils.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

/*
 * This file is part of Absorption.
 *
 * Absorption is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Absorption is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Absorption.  If not, see <http://www.gnu.org/licenses/>.
 */
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
		
		// TODO Improvements to do in effects (ae: effects on block sides)
		BLOCK.getWorld().playEffect(BLOCK.getLocation().add(Math.random(), 1, Math.random()), Effect.SPLASH, 0);
		for(int i = 0; i < 5; i++)
			ParticleEffect.REDSTONE.display(color.getParticleColor(), BLOCK.getLocation().add(Math.random(), 1, Math.random()), 20);
	}
	
	public void restoreBlock() {
		this.color = null;
		BLOCK.setType(BASE_MATERIAL);
		BLOCK.setData((byte) BASE_DATA);
	}
}
