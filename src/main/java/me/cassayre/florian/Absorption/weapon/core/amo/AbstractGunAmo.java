package me.cassayre.florian.Absorption.weapon.core.amo;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.weapon.core.WeaponType;

import org.bukkit.util.Vector;

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
public abstract class AbstractGunAmo extends AbstractAmo {

	public AbstractGunAmo(int range, int damages, Vector direction, GamePlayer owner, WeaponType type) {
		super(range, damages, direction, owner, type);
	}
	
	@Override
	public void check() {
		super.check();
		
		for(int y = 0; y <= 4; y++) {
			for(int x = 0; x <= 1; x++) {
				for(int z = 0; z <= 1; z++) {
					Vector vector = armorStand.getVelocity().setY(0).normalize();
					for(int i = 0; i <= 3; i++) {
						Absorption.get().getGame().paintBlock(armorStand.getLocation().add(1 - x / 2 - vector.clone().multiply(i).getX(), -y, 1 - z / 2 - vector.clone().multiply(i).getZ()).getBlock(), COLOR);
					}
				}
			}
		}
	}
	
	

}
