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

package tntrun;

import com.commander.nolag.Main;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tntrun.arena.Arena;
import tntrun.commands.ConsoleCommands;
import tntrun.commands.GameCommands;
import tntrun.commands.setup.SetupCommandsHandler;
import tntrun.datahandler.ArenasManager;
import tntrun.datahandler.PlayerDataStore;
import tntrun.eventhandler.PlayerLeaveArenaChecker;
import tntrun.eventhandler.PlayerStatusHandler;
import tntrun.eventhandler.RestrictionHandler;
import tntrun.lobby.GlobalLobby;
import tntrun.messages.Messages;
import tntrun.signs.SignHandler;
import tntrun.signs.editor.SignEditor;
import tntrun.utils.Bars;
import tntrun.utils.Shop;
import tntrun.utils.TitleMsg;

import java.io.File;
import java.util.logging.Logger;

public class TNTRun extends JavaPlugin {

    public PlayerDataStore pdata;
    public ArenasManager amanager;
    public GlobalLobby globallobby;
    public SignEditor signEditor;
    private Logger log;
    private TNTRun instance = null;

    @Override
    public void onEnable() {
        log = getLogger();
        instance = this;
        signEditor = new SignEditor(this);
        globallobby = new GlobalLobby(this);
        Bars.loadBars(this);
        TitleMsg.loadTitles(this);
        pdata = new PlayerDataStore();
        amanager = new ArenasManager();
        getCommand("tntrunsetup").setExecutor(new SetupCommandsHandler(this));
        getCommand("tntrun").setExecutor(new GameCommands(this));
        getCommand("tntrunconsole").setExecutor(new ConsoleCommands(this));
        getServer().getPluginManager().registerEvents(new PlayerStatusHandler(this), this);
        getServer().getPluginManager().registerEvents(new RestrictionHandler(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveArenaChecker(this), this);
        getServer().getPluginManager().registerEvents(new SignHandler(this), this);
        getServer().getPluginManager().registerEvents(new Shop(this), this);
        // config
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        // load arenas
        final File arenasfolder = new File(getDataFolder() + File.separator + "arenas");
        arenasfolder.mkdirs();
        final TNTRun instance = this;
        getServer().getScheduler().scheduleSyncDelayedTask(
                this,
                new Runnable() {
                    @Override
                    public void run() {
                        // load globallobyy
                        globallobby.loadFromConfig();
                        // load arenas
                        for (String file : arenasfolder.list()) {
                            Arena arena = new Arena(file.substring(0, file.length() - 4), instance);
                            arena.getStructureManager().loadFromConfig();
                            arena.getStatusManager().enableArena();
                            amanager.registerArena(arena);
                        }
                        // load signs
                        signEditor.loadConfiguration();
                    }
                },
                20
        );

    }

    @Override
    public void onDisable() {
        // save arenas
        for (Arena arena : amanager.getArenas()) {
            arena.getStatusManager().disableArena();
            arena.getStructureManager().saveToConfig();
        }
        // save lobby
        globallobby.saveToConfig();
        globallobby = null;
        // save signs
        signEditor.saveConfiguration();
        signEditor = null;
        // unload other things
        pdata = null;
        amanager = null;
        log = null;
    }

    public Main getCC() {
        if (instance == null) return null;
        Plugin plugin = instance.getServer().getPluginManager().getPlugin("ccommander");
        if (plugin == null || !(plugin instanceof Main)) {
            return null;
        }

        return (Main) plugin;
    }

    public void logSevere(String message) {
        log.severe(message);
    }

}
