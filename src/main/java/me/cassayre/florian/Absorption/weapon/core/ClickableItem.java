package me.cassayre.florian.Absorption.weapon.core;

import me.cassayre.florian.Absorption.game.GamePlayer;

public interface ClickableItem {

	/**
	 * Executes when the player right-clicks on the item.
	 */
	public abstract void onRightClick(GamePlayer player);
	
	/**
	 * Executes when the player left-clicks on the item.
	 */
	public abstract void onLeftClick(GamePlayer player);
	
}
