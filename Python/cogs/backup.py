import socket
import discord
from discord.app_commands import command
from discord.ext import commands
from decouple import config


class BackupCog(commands.Cog):
    def __init__(self, bot):
        self.bot = bot  # Store the bot instance

    @command(name="backup", description="Start a backup of the server")
    async def backup(self, interaction: discord.Interaction):
        # Notify the plugin to start the backup
        message = '!start_backup_discord'
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
            s.connect((config('SERVER_IP'), config('SERVER_PORT', cast=int)))
            s.sendall(message.encode('utf-8'))

        await interaction.response.send_message('Backup command sent to the plugin!')


async def setup(bot):
    await bot.add_cog(BackupCog(bot))
