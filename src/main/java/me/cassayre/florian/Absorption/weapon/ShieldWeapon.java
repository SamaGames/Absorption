package me.cassayre.florian.Absorption.weapon;

import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.weapon.core.SpecialPlayerWeapon;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShieldWeapon extends SpecialPlayerWeapon {

	public ShieldWeapon() {
		super("Bouclier", "", new ItemStack(Material.WOOD_DOOR));
	}

	@Override
	public void onUse(GamePlayer player) {
	}

}
