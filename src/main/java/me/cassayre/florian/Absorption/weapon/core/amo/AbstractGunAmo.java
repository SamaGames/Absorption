package me.cassayre.florian.Absorption.weapon.core.amo;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.weapon.core.WeaponType;

import org.bukkit.util.Vector;

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
