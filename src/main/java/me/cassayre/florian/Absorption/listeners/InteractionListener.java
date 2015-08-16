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
