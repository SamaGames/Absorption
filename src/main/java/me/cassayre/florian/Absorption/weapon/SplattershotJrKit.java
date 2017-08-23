package me.cassayre.florian.Absorption.weapon;

import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.utils.ParticleEffect;
import me.cassayre.florian.Absorption.weapon.core.MainPlayerWeapon;
import me.cassayre.florian.Absorption.weapon.core.WeaponType;
import me.cassayre.florian.Absorption.weapon.core.amo.AbstractAmo;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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
public class SplattershotJrKit extends MainPlayerWeapon {
	
	public SplattershotJrKit() {
		super("Liquidateur Jr", "", new ItemStack(Material.WOOD_PICKAXE), WeaponType.SHOOTER, 0, 10, 6, 14);
		
		secondary = new SplashBombWeapon();
		special = new ShieldWeapon();
	}

	@Override
	public void onUse(final GamePlayer player) {
		new AbstractAmo(RANGE, DAMAGES, player.getPlayer().getLocation().getDirection(), player, WeaponType.SHOOTER) {

			@Override
			public void play(Location location) {
				
				for(int i = 0; i < 2; i++)
					ParticleEffect.REDSTONE.display(player.getTeamColor().getParticleColor(), location, 25);
				
				if(Math.random() >= 0.75)
				ParticleEffect.SNOW_SHOVEL.display(0, 0, 0, 0.01F, 1, location, 25);
				
				if(Math.random() >= 0.9)
				ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0.01F, 1, location, 25);
			}
			
			@Override
			public void onFire(Location location) {
				location.getWorld().playSound(location, Sound.ENDERMAN_TELEPORT, 1, 2F);
			}
			
		};
	}
}
