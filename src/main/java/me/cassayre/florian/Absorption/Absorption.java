package me.cassayre.florian.Absorption;

import me.cassayre.florian.Absorption.game.Game;
import me.cassayre.florian.Absorption.game.GamePlayer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Absorption extends JavaPlugin {

	private static Absorption instance = null;
	
	private Game game;
	
	@Override
	public void onEnable() {
		instance = this;
		
		game = new Game();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Absorption get() {
		return instance;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public static GamePlayer getPlayer(Player player) {
		return Absorption.get().getGame().getPlayer(player);
	}
}
