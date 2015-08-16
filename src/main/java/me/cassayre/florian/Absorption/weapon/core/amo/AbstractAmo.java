package me.cassayre.florian.Absorption.weapon.core.amo;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.team.TeamColor;
import me.cassayre.florian.Absorption.utils.Utils;
import me.cassayre.florian.Absorption.weapon.core.WeaponType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public abstract class AbstractAmo {

	private final double RADIUS = 1.5;
	
	private final int RANGE;
	private final int DAMAGES;
	protected final TeamColor COLOR;
	
	private final GamePlayer OWNER;
	
	private final BukkitTask task;
	
	protected final ArmorStand armorStand;
	
	private Location lastLocation = null;
	
	public AbstractAmo(int range, int damages, Vector direction, GamePlayer owner, WeaponType type) {
		RANGE = range;
		DAMAGES = damages;
		COLOR = owner.getTeamColor();
		OWNER = owner;
		
		armorStand = (ArmorStand) owner.getPlayer().getWorld().spawnEntity(owner.getPlayer().getEyeLocation(), EntityType.ARMOR_STAND);
		armorStand.setVisible(false);
		int r = 20;
		if(type == WeaponType.SHOOTER) r = 4;
		if(type == WeaponType.SNIPER) r = 2;
		
		double rng = (double) RANGE / r;
		
		if(rng > 5) rng = 5;
		
		armorStand.setVelocity(direction.multiply(rng));
		
		lastLocation = armorStand.getLocation();
		
		onFire(owner.getPlayer().getLocation());
		
		task = Bukkit.getScheduler().runTaskTimer(Absorption.get(), new Runnable() {
			@Override
			public void run() {
				check();
				play(armorStand.getLocation());
			}
		}, 2, 1);
	}
	
	public void check() {
		if(armorStand.getLocation().add(0, -0.1, 0).getBlock().getType() != Material.AIR)
			splash();
		
		for(GamePlayer player : Absorption.get().getGame().getPlayers()) {
			Location loc = armorStand.getLocation();
			loc.setY(player.getPlayer().getLocation().getY());
			
			// Amo hit player
			if(!player.equals(OWNER) && player.isPlaying() && player.getPlayer().getLocation().distance(loc) < 0.8 && Utils.isBetween(armorStand.getLocation().getY(), player.getPlayer().getLocation().getY(), player.getPlayer().getLocation().getY() + 2)) {
				if(player.damage(DAMAGES, OWNER, COLOR))
					splash();
			}
		}
		
		if(armorStand.getLocation().getY() < 0) {
			armorStand.remove();
			task.cancel();
		}
		
		Vector vector = new Vector(armorStand.getLocation().getX() - lastLocation.getX(), armorStand.getLocation().getY() - lastLocation.getY(), armorStand.getLocation().getZ() - lastLocation.getZ());
		
		for(int i = 0; i < vector.clone().multiply(5).length(); i++) {
			lastLocation = lastLocation.add(vector.clone().multiply(0.2).multiply(1 / vector.length()));
			play(lastLocation);
		}
		
		lastLocation = armorStand.getLocation();
	}
	
	public void splash() {
		Absorption.get().getGame().paintSphere(armorStand.getLocation(), OWNER, COLOR, RADIUS, true);
		armorStand.getWorld().playSound(armorStand.getLocation(), Sound.SPLASH, 1F, 2);
		
		armorStand.remove();
		
		task.cancel();
	}
	
	public void onFire(Location location) {
		location.getWorld().playSound(location, Sound.CHICKEN_EGG_POP, 1, 0.5F);
	}
	
	public abstract void play(Location location);
	
	public void playerHit() {
		
	}
	
}
