package com.carpour.backup.commands;

import com.carpour.backup.utils.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.carpour.backup.utils.Data.pathToBackup;
import static com.carpour.backup.utils.Data.serverPort;


/**
 * The {@code Backup} class handles server backup requests from Discord.
 * It communicates with a Discord bot to initiate the backup process
 * and performs the actual backup using a Java plugin on the Minecraft server.
 */
public class Backup {

    /**
     * Constructs a new {@code Backup} instance and starts the server socket thread.
     */
    public Backup() { this.startServer(); }

    /**
     * Starts a server socket thread to listen for incoming connections from the Discord bot.
     */
    private void startServer() {

        new Thread(() -> {

            Thread.currentThread().setName("Backup-Discord-Server-Thread");

            try (final ServerSocket serverSocket = new ServerSocket(serverPort)) {
                while (true) {
                    final Socket clientSocket = serverSocket.accept();
                    this.handleClient(clientSocket);
                }
            } catch (final IOException e) { Log.severe("Error in server socket thread", e.getMessage()); }
        }).start();
    }

    /**
     * Handles an incoming connection from the Discord bot.
     * Reads the command and initiates the backup if the command is valid.
     *
     * @param clientSocket The socket representing the connection to the Discord bot.
     */
    private void handleClient(final Socket clientSocket) {

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // Read the command from the Discord bot
            final String command = reader.readLine();

            // Process the command
            if ("!start_backup_discord".equals(command.trim()))
                this.performBackup(pathToBackup);

        } catch (final IOException e) { Log.severe("Error handling client", e.getMessage()); }
    }

    /**
     * Initiates the server backup process using a Java plugin on the Minecraft server.
     *
     * @param backupFiles The list of files to include in the backup.
     */
    private void performBackup(final List<String> backupFiles) {

        if (backupFiles.isEmpty()) {
            Log.severe("No files to back up.");
            return;
        }

        try {

            // Generate a file name based on the current time
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            final String fileName = "backup_" + dateFormat.format(new Date()) + ".tar.gz";

            // Create the tar command with the dynamically generated file name
            final ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("tar", "-czf", fileName);

            // Add all files individually to the command
            processBuilder.command().addAll(backupFiles);

            // Set the working directory
            processBuilder.directory(new File(System.getProperty("user.dir")));

            // Redirect error and output streams to the console
            processBuilder.redirectErrorStream(true);
            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            final Process process = processBuilder.start();
            final int exitCode = process.waitFor();

            if (exitCode == 0) {
                Log.info("Backup completed successfully! File: " + fileName);
            } else {
                Log.severe("Error during backup. Exit code: " + exitCode);
            }

        } catch (final IOException | InterruptedException e) {
            Log.severe("Error during backup: ", e.getMessage());
        }
    }
}
