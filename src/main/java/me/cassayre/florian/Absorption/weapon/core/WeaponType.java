package me.cassayre.florian.Absorption.weapon.core;

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
