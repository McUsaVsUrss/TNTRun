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

package tntrun.arena.status;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import tntrun.arena.Arena;
import tntrun.messages.Messages;

public class StatusManager {

    private Arena arena;
    private boolean enabled = false;
    private boolean starting = false;
    private boolean running = false;
    private boolean regenerating = false;
    public StatusManager(Arena arena) {
        this.arena = arena;
    }

    public boolean isArenaEnabled() {
        return enabled;
    }

    public boolean enableArena() {
        if (arena.getStructureManager().isArenaConfigured()) {
            enabled = true;
            arena.getGameHandler().startArenaAntiLeaveHandler();
            arena.plugin.signEditor.modifySigns(arena.getArenaName());
            return true;
        }
        return false;
    }

    public void disableArena() {
        enabled = false;
        // drop players
        for (Player player : arena.getPlayersManager().getPlayersCopy()) {
            arena.getPlayerHandler().leavePlayer(player, Messages.arenadisabling, "");
        }
        // stop arena
        if (arena.getStatusManager().isArenaRunning()) {
            System.out.println("Asking from line 57 StatusManager");
            arena.getGameHandler().stopArena();
        }
        // stop countdown
        if (arena.getStatusManager().isArenaStarting()) {
            arena.getGameHandler().stopArenaCountdown();
        }
        // stop antileave handler
        arena.getGameHandler().stopArenaAntiLeaveHandler();
        // regen gamezone
        System.out.println(ChatColor.RED + "" + ChatColor.BOLD + "RESETTING IN DISABLEARENA METHOD. SAY TIS TO CARRA");
        arena.getStructureManager().getGameZone().regenNow(arena.plugin, arena.getArenaName());
        // modify signs
        arena.plugin.signEditor.modifySigns(arena.getArenaName());
    }

    public boolean isArenaStarting() {
        return starting;
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
    }

    public boolean isArenaRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isArenaRegenerating() {
        return regenerating;
    }

    public void setRegenerating(boolean regenerating) {
        this.regenerating = regenerating;
    }

}
