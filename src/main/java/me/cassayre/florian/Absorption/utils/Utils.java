package me.cassayre.florian.Absorption.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;

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
public class Utils {

	private Utils() {
	}

	public static List<String> getToolDescription(String desc) {
		List<String> lines = new ArrayList<>();
		String[] words = desc.split(" ");
		int line = 0;
		lines.add(line, "");

		for (String word : words) {
			int chars = (lines.get(line) + " " + word).length() - countColors(lines.get(line) + " " + word);

			if (chars >= 45) {
				line++;
				lines.add(line, ChatColor.GRAY + word);
			}

			else {
				if (lines.get(line).equals("")) {
					lines.set(line, ChatColor.GRAY + word);
				} else {
					lines.set(line, lines.get(line) + " " + word);
				}
			}
		}

		for (int k = 0; k < lines.size(); k++) {
			lines.set(k, lines.get(k).trim());
		}

		return lines;
	}

	private static int countColors(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '§') {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Checks if a number is equals or between two numbers.
	 * The order of x1 and x2 has no influence.
	 * @param x the number to check
	 * @param x1 the first number
	 * @param x2 the second number
	 * @return true if inside, false if not
	 */
	public static boolean isBetween(double x, double x1, double x2) {
		return x >= Math.min(x1, x2) && x <= Math.max(x1, x2);
	}
	
	/**
	 * Checks if a location is inside a cuboid.
	 * The order of loc1 and loc2 has no influence.
	 * @param location the location to check
	 * @param loc1 the first corner
	 * @param loc2 the second corner
	 * @return true if inside, false if not
	 */
	public static boolean isInside(Location location, Location loc1, Location loc2) {
		return Utils.isBetween(location.getX(), loc1.getX(), loc2.getX()) && Utils.isBetween(location.getY(), loc1.getY(), loc2.getY()) && Utils.isBetween(location.getZ(), loc1.getZ(), loc2.getZ());
	}
	
	public static int countBlocks(Block block1, Block block2) {
		return (Math.max(block1.getX(), block2.getX()) - Math.min(block1.getX(), block2.getX()) + 1) * (Math.max(block1.getY(), block2.getY()) - Math.min(block1.getY(), block2.getY()) + 1) * (Math.max(block1.getZ(), block2.getZ()) - Math.min(block1.getZ(), block2.getZ()) + 1);
	}
}
