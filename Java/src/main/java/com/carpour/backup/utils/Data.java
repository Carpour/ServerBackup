package com.carpour.backup.utils;

import com.carpour.backup.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;


/**
 * The {@code Data} class manages and provides access to configuration data
 * required for the server backup functionality.
 */
public class Data {

    // File configuration instance obtained from the main plugin class
    private static final FileConfiguration config = Main.getInstance().getConfig();

    // Server port to listen for backup initiation commands
    public static int serverPort;

    // List of paths to include in the backup
    public static List<String> pathToBackup;

    /**
     * Initializes the configuration data.
     * Called on startup to set values for server port and backup paths.
     */
    public static void initializer() {
        initializeListOfStrings();
        initializeIntegers();
    }

    /**
     * Initializes the integer configuration values.
     * Sets the server port for listening to backup initiation commands.
     */
    private static void initializeIntegers() {
        serverPort = config.getInt("server_open_port");
    }

    /**
     * Initializes the list of strings configuration values.
     * Sets the paths to include in the backup.
     */
    private static void initializeListOfStrings() {
        pathToBackup = config.getStringList("paths");
    }
}
