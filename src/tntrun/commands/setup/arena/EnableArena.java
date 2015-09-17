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

package tntrun.commands.setup.arena;

import org.bukkit.entity.Player;
import tntrun.TNTRun;
import tntrun.arena.Arena;
import tntrun.commands.setup.CommandHandlerInterface;

public class EnableArena implements CommandHandlerInterface {

    private TNTRun plugin;

    public EnableArena(TNTRun plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean handleCommand(Player player, String[] args) {
        Arena arena = plugin.amanager.getArenaByName(args[0]);
        if (arena != null) {
            if (arena.getStatusManager().isArenaEnabled()) {
                player.sendMessage("§7[§6TNTRun§7] §cArena §6" + args[0] + "§c already enabled");
            } else {
                if (arena.getStatusManager().enableArena()) {
                    player.sendMessage("§7[§6TNTRun§7] §cArena §6" + args[0] + "§c enabled");
                } else {
                    player.sendMessage("§7[§6TNTRun§7] §cArena §6" + args[0] + "§c is't configured. Reason: " + arena.getStructureManager().isArenaConfigured());
                }
            }
        } else {
            player.sendMessage("§7[§6TNTRun§7] §cArena §6" + args[0] + "§c doesn't exist");
        }
        return true;
    }

    @Override
    public int getMinArgsLength() {
        return 1;
    }

}
