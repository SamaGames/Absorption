package me.cassayre.florian.Absorption.weapon;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.cassayre.florian.Absorption.game.GamePlayer;
import me.cassayre.florian.Absorption.weapon.core.MainPlayerWeapon;
import me.cassayre.florian.Absorption.weapon.core.SecondaryPlayerWeapon;
import me.cassayre.florian.Absorption.weapon.core.SpecialPlayerWeapon;
import me.cassayre.florian.Absorption.weapon.core.WeaponType;

public class SplattershotJrKit extends MainPlayerWeapon {

	public SplattershotJrKit() {
		super("Liquidateur Jr", "", new ItemStack(Material.WOOD_PICKAXE), WeaponType.THROWER, 0, 10, 6, 14);
	}

	@Override
	public void onRightClick(GamePlayer player) {
	}

	@Override
	public SecondaryPlayerWeapon getSecondaryWeapon() {
		return null;
	}

	@Override
	public SpecialPlayerWeapon getSpecialWeapon() {
		return null;
	}
}
