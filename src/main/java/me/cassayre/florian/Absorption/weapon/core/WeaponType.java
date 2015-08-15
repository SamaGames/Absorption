package me.cassayre.florian.Absorption.weapon.core;

public enum WeaponType {
	THROWER("Lanceur"),
	ROLLER("Rouleau"),
	GUN("Fusil");
	
	private final String NAME;
	
	private WeaponType(String name) {
		NAME = name;
	}
	
	public String getName() {
		return NAME;
	}
}
