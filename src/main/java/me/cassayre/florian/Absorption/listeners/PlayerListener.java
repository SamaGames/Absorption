package me.cassayre.florian.Absorption.listeners;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerPickupItemEvent;

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
public class PlayerListener implements Listener {
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		e.setCancelled(true);
		
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			GamePlayer damager = Absorption.getPlayer((Player) e.getDamager());
			GamePlayer player = Absorption.getPlayer((Player) e.getEntity());
			
			if(damager == null || player == null) return;
			
			player.hitByPlayer(damager);
		}
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent e) {
		e.setCancelled(true);
	}

	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof ArmorStand || e.getEntity() instanceof Item)
			e.setCancelled(true);
		
		if(e.getEntity() instanceof Player) {
			e.setCancelled(true);
			if(e.getCause() == DamageCause.FALL) {
				GamePlayer player = Absorption.getPlayer((Player) e.getEntity());
				
				if(player == null) return;
				player.damage((int) e.getDamage()); 
			}
		}
	}
}
