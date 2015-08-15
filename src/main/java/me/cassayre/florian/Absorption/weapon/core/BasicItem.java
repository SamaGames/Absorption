package me.cassayre.florian.Absorption.weapon.core;

import java.util.List;

import me.cassayre.florian.Absorption.utils.Utils;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class BasicItem implements ClickableItem {

	protected final String NAME;
	protected final String DESCRIPTION;
	protected final ItemStack ICON;
	
	public BasicItem(String name, String description, ItemStack icon) {
		NAME = name;
		DESCRIPTION = description;
		ICON = icon;
	}

	/**
	 * The name of this item, not formatted.
	 * @return
	 */
	public String getName() {
		return NAME;
	}
	
	/**
	 * The item stack containing the name and the description.
	 * @return
	 */
	public ItemStack getIcon() {
		ItemMeta meta = ICON.getItemMeta();
		meta.setDisplayName(getName());
		meta.setLore(getDescription());
		ICON.setItemMeta(meta);
		return ICON;
	}
	
	/**
	 * The formatted description of this item.
	 * @return
	 */
	public List<String> getDescription() {
		List<String> list = Utils.getToolDescription(DESCRIPTION);
		list.add(0, ChatColor.GOLD + "Description :");
		
		return list;
	}
}
