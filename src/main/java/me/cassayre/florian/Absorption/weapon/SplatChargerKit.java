package me.cassayre.florian.Absorption.weapon;

import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.utils.ParticleEffect;
import me.cassayre.florian.Absorption.weapon.core.MainPlayerWeapon;
import me.cassayre.florian.Absorption.weapon.core.WeaponType;
import me.cassayre.florian.Absorption.weapon.core.amo.AbstractGunAmo;

import org.bukkit.Location;
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
public class SplatChargerKit extends MainPlayerWeapon {

	public SplatChargerKit() {
		super("Concentrateur", "", new ItemStack(Material.WOOD_HOE), WeaponType.SNIPER, 10, 85, 5, 25);
		
		secondary = new SplashBombWeapon();
		special = new ShieldWeapon();
	}

	@Override
	public void onUse(final GamePlayer player) {
		new AbstractGunAmo(RANGE, DAMAGES, player.getPlayer().getLocation().getDirection(), player, WeaponType.SHOOTER) {

			@Override
			public void play(Location location) {
				
				
				for(int i = 0; i < 2; i++)
					ParticleEffect.REDSTONE.display(player.getTeamColor().getParticleColor(), location, 25);
				
				if(Math.random() >= 0.75)
				ParticleEffect.SNOW_SHOVEL.display(0, 0, 0, 0.01F, 1, location, 25);
				
				ParticleEffect.SNOWBALL.display(0, 0, 0, 0.01F, 1, location, 25);
			}
			
		};
	}

}
