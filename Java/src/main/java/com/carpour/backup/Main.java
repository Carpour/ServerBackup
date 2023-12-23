package com.carpour.backup;

import com.carpour.backup.commands.Backup;
import com.carpour.backup.utils.Data;
import com.carpour.backup.utils.Log;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        Data.initializer();

        Log.setup(this.getLogger());

        new Backup();

        Log.info("Backup Handler enabled!");
    }

    @Override
    public void onDisable() { Log.info("Backup Handler disabled!"); }

    public static Main getInstance() { return JavaPlugin.getPlugin(Main.class); }
}
