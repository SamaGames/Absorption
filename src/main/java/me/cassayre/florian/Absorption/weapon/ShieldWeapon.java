package me.cassayre.florian.Absorption.weapon;

import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.weapon.core.SpecialPlayerWeapon;

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
public class ShieldWeapon extends SpecialPlayerWeapon {

	public ShieldWeapon() {
		super("Bouclier", "", new ItemStack(Material.WOOD_DOOR));
	}

	@Override
	public void onUse(GamePlayer player) {
	}

}
