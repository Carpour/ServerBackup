import discord
from discord.ext import commands
from decouple import config
import os

intents = discord.Intents.default()
intents.message_content = True  # Required for slash commands
bot = commands.Bot(command_prefix="^^", intents=intents)


async def load_cogs():
    for filename in os.listdir('./cogs'):
        if filename.endswith('_cog.py'):
            await bot.load_extension(f'cogs.{filename[:-3]}')  # Await the load_extension call


@bot.event
async def on_ready():
    print(f'Logged in as {bot.user.name}')
    await load_cogs()
    await bot.tree.sync()


def main():
    bot.run(config('DISCORD_TOKEN'))


if __name__ == "__main__":
    main()
