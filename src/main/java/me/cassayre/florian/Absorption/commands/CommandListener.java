package me.cassayre.florian.Absorption.commands;

import me.cassayre.florian.Absorption.Absorption;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equals("start")) {
			Absorption.get().getGame().start();
			return true;
		}
		return false;
	}

}
