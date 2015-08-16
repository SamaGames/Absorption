package me.cassayre.florian.Absorption.listeners;

import me.cassayre.florian.Absorption.Absorption;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionsListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Absorption.get().getGame().addPlayer(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		if(!Absorption.get().getGame().hasStarted())
			Absorption.get().getGame().removePlayer(e.getPlayer());
	}
}
