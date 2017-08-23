package me.cassayre.florian.Absorption.weapon.core;

import me.cassayre.florian.Absorption.game.GamePlayer;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
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
