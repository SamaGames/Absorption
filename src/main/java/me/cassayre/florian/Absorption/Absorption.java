package me.cassayre.florian.Absorption;

import me.cassayre.florian.Absorption.commands.CommandListener;
import me.cassayre.florian.Absorption.game.Game;
import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.listeners.ConnectionsListener;
import me.cassayre.florian.Absorption.listeners.InteractionListener;
import me.cassayre.florian.Absorption.listeners.PlayerListener;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Absorption extends JavaPlugin {

	private static Absorption instance = null;
	
	private Game game;
	
	@Override
	public void onEnable() {
		instance = this;
		
		game = new Game();
		
		this.getServer().getPluginManager().registerEvents(new ConnectionsListener(), this);
		this.getServer().getPluginManager().registerEvents(new InteractionListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		
		this.getCommand("start").setExecutor(new CommandListener());
		
		for(Player player : getServer().getOnlinePlayers()) {
			new ConnectionsListener().onPlayerJoin(new PlayerJoinEvent(player, null));;
		}
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
