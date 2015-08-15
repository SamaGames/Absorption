package me.cassayre.florian.Absorption.game;

import java.util.UUID;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.team.TeamColor;
import me.cassayre.florian.Absorption.weapon.core.MainPlayerWeapon;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GamePlayer {

	private final UUID id;
	
	private boolean playing = false;
	
	private final int MAX_INK = 10;
	private int ink = 0;
	
	private int life = 20;
	
	private MainPlayerWeapon kit = null;
	
	// Stats
	private int blocksPainted = 0;
	private int deaths = 0;
	private int kills = 0;
	private int inkUsed = 0;
	
	/**
	 * Creates a wrapper of a bukkit player.
	 * @param id
	 */
	public GamePlayer(UUID id) {
		this.id = id;
		
		onPlayerJoin();
	}
	
	/**
	 * Gets the wrapped bukkit player.
	 * @return
	 */
	public Player getPlayer() {
		return Bukkit.getPlayer(id);
	}
	
	/**
	 * Should be executed when the player joins the server.
	 */
	protected void onPlayerJoin() {
		
	}
	
	/**
	 * Checks if the player is currently playing.
	 * @return
	 */
	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	/**
	 * Gets the current team of the player.
	 * @return
	 */
	public TeamColor getTeamColor() {
		return TeamColor.whereIs(this);
	}

	public void addInk() {
		if(ink < MAX_INK)
			ink++;
	}
	
	public int getTotalInk() {
		return ink;
	}
	
	/**
	 * Removes ink if player has enough ink to buy
	 * @param amount
	 * @return true if purchase successed, false if not enough ink
	 */
	public boolean buyWithInk(int amount) {
		if(ink >= amount) {
			ink -= amount;
			inkUsed += amount;
			return true;
		}
		return false;
	}

	public MainPlayerWeapon getKit() {
		return kit;
	}

	public void setKit(MainPlayerWeapon kit) {
		this.kit = kit;
	}
	
	public void paintBlock(Block block) {
		if(Absorption.get().getGame().paintBlock(block, getTeamColor()))
			blocksPainted++;
	}
	
	public void damage(int damage) {
		getPlayer().damage(0);
		if(life - damage <= 0) {
			onPlayerDeath();
		} else {
			life -= damage;
			getPlayer().setHealth(life);
		}
	}
	
	public void onPlayerDeath() {
		
	}
	
	// Stats
	
	public int getBlocksPainted() {
		return blocksPainted;
	}
}
