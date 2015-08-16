package me.cassayre.florian.Absorption.weapon.core;

import me.cassayre.florian.Absorption.game.GamePlayer;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.inventory.ItemStack;

public abstract class SecondaryPlayerWeapon extends BasicItem {
	
	private final int PRICE;
	
	public SecondaryPlayerWeapon(String name, String description, ItemStack icon, int price) {
		super(name, description, icon);
		
		PRICE = price;
	}
	
	@Override
	public void onRightClick(GamePlayer player) {
		if(player.consumeInk(PRICE)) {
			player.getPlayer().sendMessage(ChatColor.AQUA + "Vous dépensez " + ChatColor.GOLD + PRICE + ChatColor.AQUA + " encre" + (PRICE > 1 ? "s" : "") + " pour utiliser " + ChatColor.RED + NAME + ChatColor.AQUA + " !");
			onUse(player);
		}
	}
	
	@Override
	public String getName() {
		return ChatColor.RED + "" + ChatColor.BOLD + NAME;
	}
	
	@Override
	public void onLeftClick(GamePlayer player) {
		onRightClick(player);
	}

	
	public abstract void onUse(GamePlayer player);

}
