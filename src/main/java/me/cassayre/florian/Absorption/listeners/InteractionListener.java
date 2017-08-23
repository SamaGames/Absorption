package me.cassayre.florian.Absorption.listeners;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.game.PlayerState;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
public class InteractionListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE)
			e.setCancelled(true);
		
		GamePlayer player = Absorption.getPlayer(e.getPlayer());
		if(player == null) return;
		
		if(e.getPlayer().getItemInHand().getType() == Material.AIR) return;
		
		if(player.getState() == PlayerState.WAITING) {
			
		} else if(player.getState() == PlayerState.PLAYING) {
			int slot = e.getPlayer().getInventory().getHeldItemSlot();
			if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
				if(slot == 0) {
					player.getKit().onLeftClick(player);
				} else if(slot == 1) {
					player.getKit().getSecondaryWeapon().onLeftClick(player);
				} else if(slot == 2) {
					player.getKit().getSpecialWeapon().onLeftClick(player);
				}
			} else if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(slot == 0) {
					player.getKit().onRightClick(player);
				} else if(slot == 1) {
					player.getKit().getSecondaryWeapon().onRightClick(player);
				} else if(slot == 2) {
					player.getKit().getSpecialWeapon().onRightClick(player);
				}
			}
		} else if(player.getState() == PlayerState.RESPAWNING) {
			
		} else if(player.getState() == PlayerState.END) {
			// Nothing
		}
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
}
