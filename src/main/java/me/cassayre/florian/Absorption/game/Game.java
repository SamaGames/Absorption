package me.cassayre.florian.Absorption.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import me.cassayre.florian.Absorption.block.ArenaBlock;
import me.cassayre.florian.Absorption.team.TeamColor;
import me.cassayre.florian.Absorption.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Game {

	private final Map<UUID, GamePlayer> players = new HashMap<>();
	
	private final Block BLOCK1 = null;
	private final Block BLOCK2 = null;
	private final Map<Block, ArenaBlock> blocks = new HashMap<>();
	
	private final List<Material> PAINTABLE_BLOCKS = Arrays.asList(Material.WOOL);
	
	private final Location PURPLE_SPAWN = null;
	private final Location ORANGE_SPAWN = null;
	private final Location GREEN_SPAWN = null;
	private final Location BLUE_SPAWN = null;
	
	public Game() {
		Bukkit.getLogger().info("Loading blocks in main thread. Players shouldn't join now. Parsing " + Utils.countBlocks(BLOCK1, BLOCK2) + " objects...");
		long time = System.currentTimeMillis();
		
		for(int x = Math.min(BLOCK1.getX(), BLOCK2.getX()); x <= Math.max(BLOCK1.getX(), BLOCK2.getX()); x++) {
			for(int y = Math.min(BLOCK1.getY(), BLOCK2.getY()); y <= Math.max(BLOCK1.getY(), BLOCK2.getY()); y++) {
				for(int z = Math.min(BLOCK1.getZ(), BLOCK2.getZ()); z <= Math.max(BLOCK1.getZ(), BLOCK2.getZ()); z++) {
					Block block = BLOCK1.getWorld().getBlockAt(x, y, z);
					if(PAINTABLE_BLOCKS.contains(block.getType())) {
						blocks.put(block, new ArenaBlock(x, y, z));
					}
				}
			}
		}
		
		Bukkit.getLogger().info(blocks.values().size() + " blocks loaded out of " + Utils.countBlocks(BLOCK1, BLOCK2) + " in " + (System.currentTimeMillis() - time) + "ms");
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
}