package me.cassayre.florian.Absorption.game;

import java.util.UUID;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.team.TeamColor;
import me.cassayre.florian.Absorption.weapon.core.MainPlayerWeapon;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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
public class GamePlayer {

	private final UUID id;
	
	private PlayerState state = PlayerState.WAITING;
	
	private final int MAX_INK = 10;
	private int ink = 10;
	
	private int life = 20;
	
	private MainPlayerWeapon kit = null;
	
	// Stats
	private int blocksPainted = 0;
	private int damagesInflicted = 0;
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
		// Resets
		Player player = getPlayer();
		player.setLevel(0);
		player.setExp(0);
		player.setFoodLevel(20);
		player.setSaturation(20);
		player.setHealth(20);
		
		// TODO coherence
		getPlayer().sendMessage(ChatColor.GOLD + "Bienvenue en Apsorbtion !");
	}
	
	/**
	 * Checks if the player is currently playing.
	 * @return
	 */
	public boolean isPlaying() {
		return state == PlayerState.PLAYING || state == PlayerState.RESPAWNING;
	}

	/**
	 * Gets the current state of the player.
	 * @param state
	 */
	public PlayerState getState() {
		return state;
	}	
	
	/**
	 * Sets the current state of the player.
	 * @param state
	 */
	public void setState(PlayerState state) {
		this.state = state;
	}
	
	/**
	 * Gets the current team of the player.
	 * @return
	 */
	public TeamColor getTeamColor() {
		return TeamColor.whereIs(this);
	}

	public void addInk() {
		if(ink < MAX_INK) {
			ink++;
			getPlayer().setLevel(ink);
		} else {
			ink = MAX_INK;
		}
	}
	
	public int getTotalInk() {
		return ink;
	}
	
	/**
	 * Removes ink if player has enough ink to use weapon.
	 * @param amount
	 * @return true if purchase successes, false if not enough ink
	 */
	public boolean consumeInk(int amount) {
		if(ink >= amount) {
			ink -= amount;
			inkUsed += amount;
			getPlayer().setLevel(ink);
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
	
	/**
	 * Damages a player with a damager.
	 * (ae: player quit team)
	 * @param damage
	 * @param damager
	 * @param color
	 * @return
	 */
	public boolean damage(int damage, GamePlayer damager, TeamColor color) {
		damager.increaseDamagesInflicted(damage);
		return damage(damage, color);
	}
	
	/**
	 * Damages a player with a damager.
	 * (ae: pvp)
	 * @param damage
	 * @param damager
	 * @return
	 */
	public boolean damage(int damage, GamePlayer damager) {
		damager.increaseDamagesInflicted(damage);
		return damage(damage, damager.getTeamColor());
	}
	
	/**
	 * Damages a player with a team color.
	 * (ae: team bomb)
	 * @param damage
	 * @param color
	 * @return
	 */
	public boolean damage(int damage, TeamColor color) {
		// Can't damage team mates
		if(color.equals(getTeamColor()))
			return false;
		
		damage(damage);
		return true;
	}
	
	/**
	 * Damages a player where the damager is not a player.
	 * (ae: fall damages)
	 * @param damage
	 */
	public void damage(int damage) {
		getPlayer().damage(0);
		if(life - damage <= 0) {
			onPlayerDeath();
		} else {
			life -= damage;
			getPlayer().setHealth(life);
		}
	}
	
	/**
	 * When a player starts pvp.
	 * @param player
	 */
	public void hitByPlayer(GamePlayer player) {
		if(getPlayer().getInventory().getHeldItemSlot() == 0) {
			damage(getKit().getType().getPVPDamages(), player);
		} else {
			damage(1, player);
		}
		
		Vector vector = new Vector(getPlayer().getLocation().getX() - player.getPlayer().getLocation().getX(), 0, getPlayer().getLocation().getZ() - player.getPlayer().getLocation().getZ());
		getPlayer().setVelocity(vector.normalize().setY(0.2).multiply(0.4).add(getPlayer().getVelocity()));
	}
	
	public void regenerate(int health) {
		if(life + health <= 20) {
			life += health;
		} else {
			life = 20;
		}
		getPlayer().setHealth(life);
	}
	
	// Inventory
	
	public void onPlayerDeath() {
		
	}
	
	
	public void fillWaitingInventory() {
		
	}
	
	public void fillPlayingInventory() {
		getPlayer().getInventory().clear();
		
		getPlayer().getInventory().setItem(0, kit.getIcon());
		getPlayer().getInventory().setItem(1, kit.getSecondaryWeapon().getIcon());
		getPlayer().getInventory().setItem(2, kit.getSpecialWeapon().getIcon());
	}
	
	public void fillRespawningInventory() {
		
	}
	
	// Stats
	
	public int getBlocksPainted() {
		return blocksPainted;
	}
	
	public void increaseDamagesInflicted(int i) {
		damagesInflicted += i;
	}
	
	public void increaseBlocksPainted() {
		blocksPainted++;
	}
}
