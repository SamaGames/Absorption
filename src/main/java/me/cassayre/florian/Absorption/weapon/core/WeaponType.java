package me.cassayre.florian.Absorption.weapon.core;

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
public enum WeaponType {
	SHOOTER("Lanceur", 3),
	ROLLER("Rouleau", 5),
	SNIPER("Chargeur", 2);
	
	private final String NAME;
	private final int PVP_DAMAGES;
	
	private WeaponType(String name, int pvpDamages) {
		NAME = name;
		PVP_DAMAGES = pvpDamages;
	}
	
	public String getName() {
		return NAME;
	}
	
	public int getPVPDamages() {
		return PVP_DAMAGES;
	}
}
