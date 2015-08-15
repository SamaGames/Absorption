package me.cassayre.florian.Absorption.weapon.core;

import me.cassayre.florian.Absorption.game.GamePlayer;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class SpecialPlayerWeapon extends BasicItem {

	private boolean available = true;
	
	public SpecialPlayerWeapon(String name, String description, ItemStack icon) {
		super(name, description, icon);
	}
	
	@Override
	public void onRightClick(GamePlayer player) {
		if(available) {
			available = false;
			player.getPlayer().getInventory().setItem(2, new ItemStack(Material.AIR));
			player.getPlayer().sendMessage(ChatColor.AQUA + "Vous utilisez " + ChatColor.GOLD + NAME + ChatColor.AQUA + " !");
			onUse(player);
		}
	}

	@Override
	public String getName() {
		return ChatColor.DARK_RED + "" + ChatColor.BOLD + NAME;
	}
	
	@Override
	public void onLeftClick(GamePlayer player) {
		onRightClick(player);
	}
	
	public abstract void onUse(GamePlayer player);

}
