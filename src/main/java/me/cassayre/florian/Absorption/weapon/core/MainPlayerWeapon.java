package me.cassayre.florian.Absorption.weapon.core;

import java.util.List;

import me.cassayre.florian.Absorption.Absorption;
import me.cassayre.florian.Absorption.game.GamePlayer;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public abstract class MainPlayerWeapon extends BasicItem {

	protected final WeaponType TYPE;
	protected final int PRICE;
	protected final int RANGE; // blocks
	protected final int DAMAGES; // half hearts
	protected final int FREQUENCY; // server ticks
	
	protected SecondaryPlayerWeapon secondary = null;
	protected SpecialPlayerWeapon special = null;
	
	private boolean canUse = true;
	
	public MainPlayerWeapon(String name, String description, ItemStack icon, WeaponType type, int price, int range, int damages, int frequency) {
		super(name, description, icon);
		
		TYPE = type;
		PRICE = price;
		RANGE = range;
		DAMAGES = damages;
		FREQUENCY = frequency;
	}
	
	public SecondaryPlayerWeapon getSecondaryWeapon() {
		return secondary;
	}
	
	public SpecialPlayerWeapon getSpecialWeapon() {
		return special;
	}
	
	@Override
	public void onRightClick(GamePlayer player) {
		if(canUse) {
			
			canUse = false;
			Bukkit.getScheduler().runTaskLater(Absorption.get(), new Runnable() {
				@Override
				public void run() {
					canUse = true;
				}
			}, FREQUENCY);
			
			onUse(player);
		}
	}
	
	public abstract void onUse(GamePlayer player);
	
	@Override
	public void onLeftClick(GamePlayer player) {}
	
	/**
	 * The formatted name of this item.
	 */
	@Override
	public String getName() {
		return ChatColor.GOLD + "" + ChatColor.BOLD + NAME;
	}
	
	@Override
	public List<String> getDescription() {
		List<String> description = super.getDescription();
		
		description.add("");
		description.add(ChatColor.GOLD + "Type : " + ChatColor.RED + TYPE.getName());
		description.add("");
		description.add(ChatColor.GOLD + "Portée : " + ChatColor.RED + RANGE);
		description.add(ChatColor.GOLD + "Dommages : " + ChatColor.RED + DAMAGES * 5);
		description.add(ChatColor.GOLD + "Fréquence : " + ChatColor.RED + FREQUENCY * 5);
		
		return description;
	}

	public WeaponType getType() {
		return TYPE;
	}
}
