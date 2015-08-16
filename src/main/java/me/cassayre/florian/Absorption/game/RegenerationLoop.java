package me.cassayre.florian.Absorption.game;

import me.cassayre.florian.Absorption.Absorption;

public class RegenerationLoop implements Runnable {

	@Override
	public void run() {
		for(GamePlayer player : Absorption.get().getGame().getPlayers()) {
			if(!player.isPlaying()) continue;
			
			player.regenerate(1);
		}
	}

}
