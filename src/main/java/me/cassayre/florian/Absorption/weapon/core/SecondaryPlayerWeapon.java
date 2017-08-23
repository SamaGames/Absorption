package me.cassayre.florian.Absorption.weapon.core;

import me.cassayre.florian.Absorption.game.GamePlayer;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.inventory.ItemStack;

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
