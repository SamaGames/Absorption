package me.cassayre.florian.Absorption.weapon;

import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.utils.ParticleEffect;
import me.cassayre.florian.Absorption.weapon.core.MainPlayerWeapon;
import me.cassayre.florian.Absorption.weapon.core.WeaponType;
import me.cassayre.florian.Absorption.weapon.core.amo.AbstractGunAmo;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
