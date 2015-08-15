package me.cassayre.florian.Absorption.weapon.core;

import java.util.List;

import me.cassayre.florian.Absorption.game.GamePlayer;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.inventory.ItemStack;

public abstract class MainPlayerWeapon extends BasicItem {

	protected final WeaponType TYPE;
	protected final int PRICE;
	protected final int RANGE; // blocks
	protected final int DAMAGES; // half hearts
	protected final int FREQUENCY; // server ticks
	
	public MainPlayerWeapon(String name, String description, ItemStack icon, WeaponType type, int price, int range, int damages, int frequency) {
		super(name, description, icon);
		
		TYPE = type;
		PRICE = price;
		RANGE = range;
		DAMAGES = damages;
		FREQUENCY = frequency;
	}
	
	public abstract SecondaryPlayerWeapon getSecondaryWeapon();
	
	public abstract SpecialPlayerWeapon getSpecialWeapon();
	
	@Override
	public void onLeftClick(GamePlayer player) {}
	
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
		description.add(ChatColor.GOLD + "Dommages : " + ChatColor.RED + DAMAGES);
		description.add(ChatColor.GOLD + "Fréquence : " + ChatColor.RED + FREQUENCY * 5);
		
		return description;
	}
}
