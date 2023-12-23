# Minecraft Server Backup System

![Version](https://img.shields.io/github/v/release/Carpour/ServerBackup)
![Downloads](https://img.shields.io/github/downloads/Carpour/ServerBackup/total)
![Issues](https://img.shields.io/github/issues/Carpour/ServerBackup/issues)
[![Discord](https://img.shields.io/discord/850407951629287424)](https://discord.gg/MfR5mcpVfX)
[![Open Source](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://opensource.org/)
[![Discord](https://img.shields.io/discord/850407951629287424?color=%235865F2&label=join%20our%20discord)](https://discord.gg/MfR5mcpVfX)
[![](https://img.shields.io/github/license/Carpour/ServerBackup.svg)](https://github.com/ExceptedPrism3/Logger/blob/master/LICENSE "License")

Backing a server has never been easier than now. Backup your server with 1 command line from your Discord server, rather than logging into the panel.

## Features

- **Simplified Backup:** Perform server backups seamlessly with a single command in your Discord server.
- **Convenient:** No need to log into a separate panel; initiate backups directly from Discord.
- **Customization:** Tailor backup settings to suit your server's specific needs.
- **Notification System:** Receive notifications upon completion of backups or in case of errors.

## Integration with Minecraft Server

This project includes a Java plugin for your Minecraft server, allowing seamless communication between the Discord bot and the server for backup initiation.

## Usage

1. Create and Invite a bot to your Discord server.
2. Use the following application command to initiate a backup:
    ```
    /backup
    ```

## Getting Started

### Prerequisites

- Python 3.x
- Discord Bot Token
- Java 8 or later
- Bukkit/Spigot server for Minecraft

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Carpour/ServerBackup.git
   ```

2. Install Python dependencies:
   ```bash
   pip install -r requirements.txt
   ```

3. Create a **.env** file where your discord bot is hosted, specifically among the **main.py** directory using the following template and configure it.
   ```dotenv
   DISCORD_TOKEN=YOUR_BOT_TOKEN
   SERVER_IP=YOUR_SERVER_IP
   SERVER_PORT=INPUT_OPEN_PORT_OF_YOUR_SERVER
   ```

4. Configure your Minecraft server and ensure the Java plugin is installed.

5. Run the bot:

    ```bash
    python main.py
    ```

## Configuration

Adjust the plugin's settings by modifying the configuration file (`config.yml`) on your minecraft server.

## Report a Bug

If you find a bug, please report it to the issue tracker.

## Contributing

Any Contributions are welcome!

## License

This project is licensed under the [MIT License](LICENSE).
