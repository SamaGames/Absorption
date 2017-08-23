package me.cassayre.florian.Absorption.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.block.ArenaBlock;
import me.cassayre.florian.Absorption.team.TeamColor;
import me.cassayre.florian.Absorption.utils.Utils;
import me.cassayre.florian.Absorption.weapon.SplatChargerKit;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

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
public class Game {

	private final Map<UUID, GamePlayer> players = new HashMap<>();
	
	private final World WORLD = Bukkit.getWorlds().get(0);
	
	private final Block BLOCK1 = new Location(WORLD, 10, 64, 56).getBlock();
	private final Block BLOCK2 = new Location(WORLD, 35, 70, 42).getBlock();
	
	private final Map<Block, ArenaBlock> blocks = new HashMap<>();
	
	private final List<Material> PAINTABLE_BLOCKS = Arrays.asList(Material.WOOL);
	
	private final Location PURPLE_SPAWN = new Location(WORLD, 32, 65, 50);
	private final Location ORANGE_SPAWN = new Location(WORLD, 32, 65, 50);
	private final Location GREEN_SPAWN = new Location(WORLD, 32, 65, 50);
	private final Location BLUE_SPAWN = new Location(WORLD, 32, 65, 50);
	
	private boolean started = false;
	
	public Game() {
		Bukkit.getLogger().info("Loading blocks in main thread. Players shouldn't join now. Parsing " + Utils.countBlocks(BLOCK1, BLOCK2) + " objects...");
		long time = System.currentTimeMillis();
		
		for(int x = Math.min(BLOCK1.getX(), BLOCK2.getX()); x <= Math.max(BLOCK1.getX(), BLOCK2.getX()); x++) {
			for(int y = Math.min(BLOCK1.getY(), BLOCK2.getY()); y <= Math.max(BLOCK1.getY(), BLOCK2.getY()); y++) {
				for(int z = Math.min(BLOCK1.getZ(), BLOCK2.getZ()); z <= Math.max(BLOCK1.getZ(), BLOCK2.getZ()); z++) {
					Block block = BLOCK1.getWorld().getBlockAt(x, y, z);
					if(PAINTABLE_BLOCKS.contains(block.getType())) {
						blocks.put(block, new ArenaBlock(x, y, z));
						if(block.getType() == Material.WOOL)
							block.setData((byte) 0);
					}
				}
			}
		}
		
		Bukkit.getLogger().info(blocks.values().size() + " blocks loaded out of " + Utils.countBlocks(BLOCK1, BLOCK2) + " in " + (System.currentTimeMillis() - time) + "ms");
		
		Bukkit.getScheduler().runTaskTimer(Absorption.get(), new RegenerationLoop(), 5 * 20, 5 * 20);
	}
	
	/**
	 * Adds a game player.
	 * Should be executed just after connection.
	 * @param player
	 * @return
	 */
	public GamePlayer addPlayer(Player player) {
		GamePlayer gamePlayer = new GamePlayer(player.getUniqueId());
		players.put(player.getUniqueId(), gamePlayer);
		return gamePlayer;
	}
	
	/**
	 * Removes a game player.
	 * Should be executed at end of the game, or at disconnection if game hasn't begun yet.
	 * @param player
	 */
	public void removePlayer(Player player) {
		players.remove(player.getUniqueId());
	}
	
	/**
	 * Gets the current game player object associated with this player.
	 * Can't be null.
	 * @param player
	 * @return
	 */
	public GamePlayer getPlayer(Player player) {
		return players.get(player.getUniqueId());
	}
	
	/**
	 * Returns all the game players.
	 * Spectating moderators included.
	 * @return
	 */
	public List<GamePlayer> getPlayers() {
		List<GamePlayer> list = new ArrayList<>();
		for(GamePlayer player : players.values()) {
			list.add(player);
		}
		return list;
	}
	
	/**
	 * Should be called to start the game.
	 */
	public void start() {
		started = true;
		
		Bukkit.broadcastMessage(ChatColor.GREEN + "A vos rouleaux, prêts, peignez !");
		
		Collections.shuffle(getPlayers());
		
		for(GamePlayer player : getPlayers()) {
			if(player.getTeamColor() != null) continue;
			TeamColor.addSomewhere(player);
			player.getPlayer().sendMessage(ChatColor.GREEN + "Vous avez été placé dans l'équipe " + player.getTeamColor().getColor() + "" + ChatColor.BOLD + player.getTeamColor().getName());
		}
		
		for(GamePlayer player : getPlayers()) {
			switch(player.getTeamColor()) {
			case PURPLE :
				player.getPlayer().teleport(PURPLE_SPAWN);
				break;
			case ORANGE :
				player.getPlayer().teleport(ORANGE_SPAWN);
				break;
			case GREEN :
				player.getPlayer().teleport(GREEN_SPAWN);
				break;
			case BLUE :
				player.getPlayer().teleport(BLUE_SPAWN);
				break;
			}
			
			player.setState(PlayerState.PLAYING);
			
			// TODO random kit (or kit that is in minority)
			if(player.getKit() == null) {
				player.setKit(new SplatChargerKit());
				player.getPlayer().sendMessage(ChatColor.GREEN + "Nous vous avons attribué l'arme " + player.getKit().getName());
			}
			
			player.fillPlayingInventory();
		}
		
	}
	
	/**
	 * Paints the block using a team color.
	 * If TeamColor is null, the block will come back to its original state.
	 * Unknown blocks safe.
	 * @param block
	 * @param color
	 * @return true if the color has changed, false if not.
	 */
	public boolean paintBlock(Block block, TeamColor color) {
		if(isArenaBlock(block)) {
			if(color.getColor().equals(blocks.get(block).getColor()))
				return false;
			blocks.get(block).colorBlock(color);
			return true;
		}
		return false;
	}
	
	public void paintSphere(Location center, GamePlayer player, TeamColor color, double radius, boolean jitter) {
		if(jitter)
			radius += 0.75;
		
		for(int x = (int) -Math.ceil(radius); x <= (int) Math.ceil(radius); x++) {
			for(int y = (int) -Math.ceil(radius); y <= (int) Math.ceil(radius); y++) {
				for(int z = (int) -Math.ceil(radius); z <= (int) Math.ceil(radius); z++) {
					if(!jitter) {
						if(center.clone().add(x, y, z).distance(center) < radius) {
							System.out.println(center.clone().add(x, y, z).distance(center) + " " + radius);
							if(Absorption.get().getGame().paintBlock(center.clone().add(x, y, z).getBlock(), color))
								player.increaseBlocksPainted();
						}
					} else {
						if(center.clone().add(x, y, z).distance(center) < radius - 0.75 || (center.clone().add(x, y, z).distance(center) < radius && Math.random() >= 0.75)) {
							if(Absorption.get().getGame().paintBlock(center.clone().add(x, y, z).getBlock(), color))
								player.increaseBlocksPainted();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Checks if the block is in the arena.
	 * @param block
	 * @return
	 */
	public boolean isInArena(Block block) {
		return Utils.isInside(block.getLocation(), BLOCK1.getLocation(), BLOCK2.getLocation());
	}
	
	/**
	 * Checks if the entered block is an arena block than can be painted.
	 * @param block
	 * @return
	 */
	public boolean isArenaBlock(Block block) {
		return isInArena(block) && blocks.get(block) != null;
	}
	
	/**
	 * Teleports the player to his team spawn.
	 * @param player
	 */
	public void teleportPlayerTeamSpawn(GamePlayer player) {
		TeamColor team = player.getTeamColor();
		if(team == null)
			throw new IllegalStateException("Player " + player.getPlayer().getName() + " has no team !");
		
		Location spawn = null;
		
		switch(team) {
		case PURPLE :
			spawn = PURPLE_SPAWN;
			break;
		case ORANGE :
			spawn = ORANGE_SPAWN;
			break;
		case GREEN :
			spawn = GREEN_SPAWN;
			break;
		case BLUE :
			spawn = BLUE_SPAWN;
			break;
		}
		
		player.getPlayer().teleport(spawn);
	}
	
	public int getMaxPlayers() {
		return 16;
	}
	
	public boolean hasStarted() {
		return started;
	}
}