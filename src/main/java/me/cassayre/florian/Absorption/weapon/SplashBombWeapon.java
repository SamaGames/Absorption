package me.cassayre.florian.Absorption.weapon;

import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.utils.ParticleEffect;
import me.cassayre.florian.Absorption.weapon.core.SecondaryPlayerWeapon;
import me.cassayre.florian.Absorption.weapon.core.amo.AbstractBomb;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

public class SplashBombWeapon extends SecondaryPlayerWeapon {

	private final int RANGE = 10;
	private final int DAMAGES = 4;
	private final int RADIUS = 3;
	private final int TIME = 5;
	
	public SplashBombWeapon() {
		super("Bombe Splash", "", new ItemStack(Material.CLAY_BALL), 7);
	}

	@Override
	public void onUse(GamePlayer player) {
		new AbstractBomb(RANGE, DAMAGES, RADIUS, TIME, ICON, player) {

			@Override
			public void play(Location location) {
				ParticleEffect.SMOKE_NORMAL.display(0, 0, 0, 1, 4, location, 25);
				ParticleEffect.SPELL_MOB.display(COLOR.getParticleColor(), location, 25);
				location.getWorld().playSound(location, Sound.FUSE, 0.5F, 2);
			}

			@Override
			public void explode(Location location, int radius) {
				ParticleEffect.SMOKE_LARGE.display((float) Math.random() * 2 - 1, (float) Math.random() * 2 - 1, (float) Math.random() * 2 - 1, 1, 20, location, 25);
				ParticleEffect.SMOKE_NORMAL.display((float) Math.random() * 2 - 1, (float) Math.random() * 2 - 1, (float) Math.random() * 2 - 1, 0.01F, 20, location, 25);
				for(int i = 0; i < 20; i++)
					ParticleEffect.REDSTONE.display(COLOR.getParticleColor(), location.add(Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1), 25);
				
				location.getWorld().playSound(location, Sound.EXPLODE, 1, 1);
			}
			
		};
	}

}
