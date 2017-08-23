package me.cassayre.florian.Absorption.weapon.core.amo;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.team.TeamColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

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
public abstract class AbstractBomb {

	private final int RADIUS;
	private final int TIME;
	private final int RANGE;
	private final int DAMAGES;
	
	protected final TeamColor COLOR;
	protected final GamePlayer OWNER;
	
	private final BukkitTask task;
	
	private final Item item;
	
	public AbstractBomb(int range, int damages, int radius, int time, ItemStack itemStack, GamePlayer owner) {
		RANGE = range;
		TIME = time;
		RADIUS = radius;
		DAMAGES = damages;
		COLOR = owner.getTeamColor();
		OWNER = owner;
		
		item = OWNER.getPlayer().getWorld().dropItem(OWNER.getPlayer().getEyeLocation(), itemStack);
		item.setVelocity(OWNER.getPlayer().getLocation().getDirection().multiply(RANGE / 10));
		
		task = Bukkit.getScheduler().runTaskTimer(Absorption.get(), new Runnable() {
			private int i = 0;
			@Override
			public void run() {
				i++;
				
				if(i % 4 == 0)
					play(item.getLocation());
				
				if(i >= TIME * 20)
					remove();
			}
			
		}, 1, 1);
	}
	
	public void remove() {
		task.cancel();
		
		for(GamePlayer player : Absorption.get().getGame().getPlayers()) {
			if(!player.isPlaying() || player.getPlayer().getLocation().distance(item.getLocation()) > RADIUS) continue;
			player.damage(DAMAGES, OWNER, COLOR);
		}
		
		Absorption.get().getGame().paintSphere(item.getLocation(), OWNER, COLOR, RADIUS, true);
		
		explode(item.getLocation(), RADIUS);
		item.remove();
	}
	
	public abstract void play(Location location);
	
	public abstract void explode(Location location, int radius);
}
