/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package tntrun.messages;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import tntrun.FormattingCodesParser;
import tntrun.TNTRun;

import java.io.File;
import java.io.IOException;

public class Messages {

    public static String nopermission = "&7[&6TNTRun&7] &cYou don't have permission to do this";

    public static String teleporttolobby = "&7[&6TNTRun&7] Teleported to lobby";

    public static String availablearenas = "&7[&6TNTRun&7] Available arenas:&r ";
    public static String arenawolrdna = "&7[&6TNTRun&7] Arena world is not loaded";
    public static String arenadisabled = "&7[&6TNTRun&7] Arena is disabled";
    public static String arenarunning = "&7[&6TNTRun&7] Arena already running";
    public static String arenaregenerating = "&7[&6TNTRun&7] Arena is regenerating";
    public static String arenavehicle = "&7[&6TNTRun&7] You can't join the game while sitting inside vehicle";
    public static String arenadisabling = "&7[&6TNTRun&7] &6Arena is disabling";
    public static String arenastarting = "&7[&6TNTRun&7] &6Arena already starting";

    public static String playerscountinarena = "&7[&6TNTRun&7] &6Current number of players in arena: {COUNT} players";
    public static String limitreached = "&7[&6TNTRun&7] Arena is full.";
    public static String playerjoinedtoplayer = "&7[&6TNTRun&7] You joined the arena";
    public static String playerjoinedtoothers = "&7[&6TNTRun&7] Player &6{PLAYER} &7joined the arena";
    public static String playerlefttoplayer = "&7[&6TNTRun&7] You left the arena";
    public static String playerlefttoothers = "&7[&6TNTRun&7] Player &6{PLAYER} &7left the game";
    public static String playervotedforstart = "&7[&6TNTRun&7] You voted for game start";
    public static String playeralreadyvotedforstart = "&7[&6TNTRun&7] You already voted";
    public static String arenastarted = "&7[&6TNTRun&7] Arena started. Time limit is {TIMELIMIT} seconds";
    public static String arenacountdown = "&7[&6TNTRun&7] Arena starts in {COUNTDOWN} seconds";
    public static String arenatimeout = "&7[&6TNTRun&7] Time is out. Ending game";
    public static String playerwontoplayer = "&7[&6TNTRun&7] You won the game!";
    public static String playerlosttoplayer = "&7[&6TNTRun&7] You lost the game";
    public static String playerlosttoothers = "&7[&6TNTRun&7] Player &6{PLAYER} &7lost the game";
    public static String playerwonbroadcast = "&7[&6TNTRun&7] &6{PLAYER}&7 won the game on arena &c{ARENA}";
    public static String playerrewardmessage = "&7[&6TNTRun&7] You have been rewarded: &6{REWARD}";
    public static String playerboughtitem = "&7[&6TNTRun&7] You have bought item &6{ITEM} &7for &6{MONEY} &7coins";
    public static String playerboughtwait = "&7[&6TNTRun&7] You will get your items when game starts";
    public static String notenoughtmoney = "&7[&6TNTRun&7] &cYou need {MONEY} coins to buy this item";
    public static String alreadyboughtitem = "&7[&6TNTRun&7] &cYou already bought item";
    public static String getdoublejumpsaction = "&7&lYour double jumps: &6&l{DB}";

    public static void sendMessage(Player player, String message) {
        if (!message.equals("")) {
            player.sendMessage(FormattingCodesParser.parseFormattingCodes(message));
        }
    }

    public static void broadcastMessage(String message) {
        if (!message.equals("")) {
            Bukkit.broadcastMessage(FormattingCodesParser.parseFormattingCodes(message));
        }
    }

}
