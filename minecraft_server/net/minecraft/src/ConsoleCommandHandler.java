package net.minecraft.src;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.forge.DimensionManager;
import net.minecraft.src.forge.ForgeHooks;

public class ConsoleCommandHandler
{
    private static Logger minecraftLogger = Logger.getLogger("Minecraft");

    /** Stores a reference to the Server */
    private MinecraftServer minecraftServer;

    public ConsoleCommandHandler(MinecraftServer par1MinecraftServer)
    {
        this.minecraftServer = par1MinecraftServer;
    }

    /**
     * handles the command that was issued by an Op/Console
     */
    public synchronized void handleCommand(ServerCommand par1ServerCommand)
    {
        String var2 = par1ServerCommand.command;
        String[] var3 = var2.split(" ");
        String var4 = var3[0];
        String var5 = var2.substring(var4.length()).trim();
        ICommandListener var6 = par1ServerCommand.commandListener;
        String var7 = var6.getUsername();
        ServerConfigurationManager var8 = this.minecraftServer.configManager;

        if (!var4.equalsIgnoreCase("help") && !var4.equalsIgnoreCase("?"))
        {
            if (var4.equalsIgnoreCase("list"))
            {
                var6.log("Connected players: " + var8.getPlayerList());
            }
            else if (var4.equalsIgnoreCase("stop"))
            {
                this.sendNoticeToOps(var7, "Stopping the server..");
                this.minecraftServer.initiateShutdown();
            }
            else
            {
                int var9;
                WorldServer var10;

                if (var4.equalsIgnoreCase("save-all"))
                {
                    this.sendNoticeToOps(var7, "Forcing save..");

                    if (var8 != null)
                    {
                        var8.savePlayerStates();
                    }

                    for (World world : DimensionManager.getWorlds())
                    {
                        var10 = (WorldServer)world;
                        boolean var11 = var10.levelSaving;
                        var10.levelSaving = false;
                        var10.saveWorld(true, (IProgressUpdate)null);
                        var10.levelSaving = var11;
                    }

                    this.sendNoticeToOps(var7, "Save complete.");
                }
                else if (var4.equalsIgnoreCase("save-off"))
                {
                    this.sendNoticeToOps(var7, "Disabling level saving..");

                    for (World world : DimensionManager.getWorlds())
                    {
                        ((WorldServer)world).levelSaving = true;
                    }
                }
                else if (var4.equalsIgnoreCase("save-on"))
                {
                    this.sendNoticeToOps(var7, "Enabling level saving..");

                    for (World world : DimensionManager.getWorlds())
                    {
                        ((WorldServer)world).levelSaving = false;
                    }
                }
                else if (var4.equalsIgnoreCase("op"))
                {
                    var8.addOp(var5);
                    this.sendNoticeToOps(var7, "Opping " + var5);
                    var8.sendChatMessageToPlayer(var5, "\u00a7eYou are now op!");
                }
                else if (var4.equalsIgnoreCase("deop"))
                {
                    var8.removeOp(var5);
                    var8.sendChatMessageToPlayer(var5, "\u00a7eYou are no longer op!");
                    this.sendNoticeToOps(var7, "De-opping " + var5);
                }
                else if (var4.equalsIgnoreCase("ban-ip"))
                {
                    var8.banIP(var5);
                    this.sendNoticeToOps(var7, "Banning ip " + var5);
                }
                else if (var4.equalsIgnoreCase("pardon-ip"))
                {
                    var8.pardonIP(var5);
                    this.sendNoticeToOps(var7, "Pardoning ip " + var5);
                }
                else
                {
                    EntityPlayerMP var18;

                    if (var4.equalsIgnoreCase("ban"))
                    {
                        var8.banPlayer(var5);
                        this.sendNoticeToOps(var7, "Banning " + var5);
                        var18 = var8.getPlayerEntity(var5);

                        if (var18 != null)
                        {
                            var18.playerNetServerHandler.kickPlayer("Banned by admin");
                        }
                    }
                    else if (var4.equalsIgnoreCase("pardon"))
                    {
                        var8.pardonPlayer(var5);
                        this.sendNoticeToOps(var7, "Pardoning " + var5);
                    }
                    else
                    {
                        String var19;
                        int var21;

                        if (var4.equalsIgnoreCase("kick"))
                        {
                            var19 = var5;
                            var18 = null;

                            for (var21 = 0; var21 < var8.playerEntities.size(); ++var21)
                            {
                                EntityPlayerMP var12 = (EntityPlayerMP)var8.playerEntities.get(var21);

                                if (var12.username.equalsIgnoreCase(var19))
                                {
                                    var18 = var12;
                                }
                            }

                            if (var18 != null)
                            {
                                var18.playerNetServerHandler.kickPlayer("Kicked by admin");
                                this.sendNoticeToOps(var7, "Kicking " + var18.username);
                            }
                            else
                            {
                                var6.log("Can\'t find user " + var19 + ". No kick.");
                            }
                        }
                        else if (var4.equalsIgnoreCase("tp"))
                        {
                            if (var3.length == 3)
                            {
                                EntityPlayerMP var20 = var8.getPlayerEntity(var3[1]);
                                var18 = var8.getPlayerEntity(var3[2]);

                                if (var20 == null)
                                {
                                    var6.log("Can\'t find user " + var3[1] + ". No tp.");
                                }
                                else if (var18 == null)
                                {
                                    var6.log("Can\'t find user " + var3[2] + ". No tp.");
                                }
                                else if (var20.dimension != var18.dimension)
                                {
                                    var6.log("User " + var3[1] + " and " + var3[2] + " are in different dimensions. No tp.");
                                }
                                else
                                {
                                    var20.playerNetServerHandler.teleportTo(var18.posX, var18.posY, var18.posZ, var18.rotationYaw, var18.rotationPitch);
                                    this.sendNoticeToOps(var7, "Teleporting " + var3[1] + " to " + var3[2] + ".");
                                }
                            }
                            else
                            {
                                var6.log("Syntax error, please provide a source and a target.");
                            }
                        }
                        else if (var4.equalsIgnoreCase("give"))
                        {
                            if (var3.length != 3 && var3.length != 4 && var3.length != 5)
                            {
                                return;
                            }

                            var19 = var3[1];
                            var18 = var8.getPlayerEntity(var19);

                            if (var18 != null)
                            {
                                try
                                {
                                    var21 = Integer.parseInt(var3[2]);

                                    if (Item.itemsList[var21] != null)
                                    {
                                        this.sendNoticeToOps(var7, "Giving " + var18.username + " some " + var21);
                                        int var22 = 1;
                                        int var13 = 0;

                                        if (var3.length > 3)
                                        {
                                            var22 = this.tryParse(var3[3], 1);
                                        }

                                        if (var3.length > 4)
                                        {
                                            var13 = this.tryParse(var3[4], 1);
                                        }

                                        if (var22 < 1)
                                        {
                                            var22 = 1;
                                        }

                                        if (var22 > 64)
                                        {
                                            var22 = 64;
                                        }

                                        var18.dropPlayerItem(new ItemStack(var21, var22, var13));
                                    }
                                    else
                                    {
                                        var6.log("There\'s no item with id " + var21);
                                    }
                                }
                                catch (NumberFormatException var16)
                                {
                                    var6.log("There\'s no item with id " + var3[2]);
                                }
                            }
                            else
                            {
                                var6.log("Can\'t find user " + var19);
                            }
                        }
                        else if (var4.equalsIgnoreCase("xp"))
                        {
                            if (var3.length != 3)
                            {
                                return;
                            }

                            var19 = var3[1];
                            var18 = var8.getPlayerEntity(var19);

                            if (var18 != null)
                            {
                                try
                                {
                                    var21 = Integer.parseInt(var3[2]);
                                    var21 = var21 > 5000 ? 5000 : var21;
                                    this.sendNoticeToOps(var7, "Giving " + var21 + " orbs to " + var18.username);
                                    var18.addExperience(var21);
                                }
                                catch (NumberFormatException var15)
                                {
                                    var6.log("Invalid orb count: " + var3[2]);
                                }
                            }
                            else
                            {
                                var6.log("Can\'t find user " + var19);
                            }
                        }
                        else if (var4.equalsIgnoreCase("gamemode"))
                        {
                            if (var3.length != 3)
                            {
                                return;
                            }

                            var19 = var3[1];
                            var18 = var8.getPlayerEntity(var19);

                            if (var18 != null)
                            {
                                try
                                {
                                    var21 = Integer.parseInt(var3[2]);
                                    var21 = WorldSettings.validGameType(var21);

                                    if (var18.itemInWorldManager.getGameType() != var21)
                                    {
                                        this.sendNoticeToOps(var7, "Setting " + var18.username + " to game mode " + var21);
                                        var18.itemInWorldManager.toggleGameType(var21);
                                        var18.playerNetServerHandler.sendPacket(new Packet70Bed(3, var21));
                                    }
                                    else
                                    {
                                        this.sendNoticeToOps(var7, var18.username + " already has game mode " + var21);
                                    }
                                }
                                catch (NumberFormatException var14)
                                {
                                    var6.log("There\'s no game mode with id " + var3[2]);
                                }
                            }
                            else
                            {
                                var6.log("Can\'t find user " + var19);
                            }
                        }
                        else if (var4.equalsIgnoreCase("time"))
                        {
                            if (var3.length != 3)
                            {
                                return;
                            }

                            var19 = var3[1];

                            try
                            {
                                int var23 = Integer.parseInt(var3[2]);
                                WorldServer var24;

                                if ("add".equalsIgnoreCase(var19))
                                {
                                    for (World world : DimensionManager.getWorlds())
                                    {
                                        world.advanceTime(world.getWorldTime() + (long)var23);
                                    }

                                    this.sendNoticeToOps(var7, "Added " + var23 + " to time");
                                }
                                else if ("set".equalsIgnoreCase(var19))
                                {
                                    for (World world : DimensionManager.getWorlds())
                                    {
                                        world.advanceTime((long)var23);
                                    }

                                    this.sendNoticeToOps(var7, "Set time to " + var23);
                                }
                                else
                                {
                                    var6.log("Unknown method, use either \"add\" or \"set\"");
                                }
                            }
                            catch (NumberFormatException var17)
                            {
                                var6.log("Unable to convert time value, " + var3[2]);
                            }
                        }
                        else if (var4.equalsIgnoreCase("say") && var5.length() > 0)
                        {
                            var5 = ForgeHooks.onServerCommandSay(var6, var6.getUsername(), var5);
                            if (var5 != null)
                            {
                                minecraftLogger.info("[" + var7 + "] " + var5);
                                var8.sendPacketToAllPlayers(new Packet3Chat("\u00a7d[Server] " + var5));
                            }
                        }
                        else if (var4.equalsIgnoreCase("tell"))
                        {
                            if (var3.length >= 3)
                            {
                                var2 = var2.substring(var2.indexOf(" ")).trim();
                                var2 = var2.substring(var2.indexOf(" ")).trim();
                                minecraftLogger.info("[" + var7 + "->" + var3[1] + "] " + var2);
                                var2 = "\u00a77" + var7 + " whispers " + var2;
                                minecraftLogger.info(var2);

                                if (!var8.sendPacketToPlayer(var3[1], new Packet3Chat(var2)))
                                {
                                    var6.log("There\'s no player by that name online.");
                                }
                            }
                        }
                        else if (var4.equalsIgnoreCase("whitelist"))
                        {
                            this.handleWhitelist(var7, var2, var6);
                        }
                        else if (var4.equalsIgnoreCase("toggledownfall"))
                        {
                            ((WorldServer)DimensionManager.getWorld(0)).commandToggleDownfall();
                            var6.log("Toggling rain and snow, hold on...");
                        }
                        else if (var4.equalsIgnoreCase("banlist"))
                        {
                            if (var3.length == 2)
                            {
                                if (var3[1].equals("ips"))
                                {
                                    var6.log("IP Ban list:" + this.joinStrings(this.minecraftServer.getBannedIPsList(), ", "));
                                }
                            }
                            else
                            {
                                var6.log("Ban list:" + this.joinStrings(this.minecraftServer.getBannedPlayersList(), ", "));
                            }
                        }
                        else if (FMLServerHandler.instance().handleServerCommand(var2, var6.getUsername(), var6))
                        {
                            
                        }
                        else if (ForgeHooks.onServerCommand(var6, var6.getUsername(), var2))
                        {
                            //Nom Nom Nom, Do we need to log anything, Let the command handle it themselves.
                        }
                        else
                        {
                            minecraftLogger.info("Unknown console command. Type \"help\" for help.");
                        }
                    }
                }
            }
        }
        else
        {
            this.printHelp(var6);
        }
    }

    /**
     * Handles the whitelist command
     */
    private void handleWhitelist(String par1Str, String par2Str, ICommandListener par3ICommandListener)
    {
        String[] var4 = par2Str.split(" ");

        if (var4.length >= 2)
        {
            String var5 = var4[1].toLowerCase();

            if ("on".equals(var5))
            {
                this.sendNoticeToOps(par1Str, "Turned on white-listing");
                this.minecraftServer.propertyManagerObj.setProperty("white-list", true);
            }
            else if ("off".equals(var5))
            {
                this.sendNoticeToOps(par1Str, "Turned off white-listing");
                this.minecraftServer.propertyManagerObj.setProperty("white-list", false);
            }
            else if ("list".equals(var5))
            {
                Set var6 = this.minecraftServer.configManager.getWhiteListedIPs();
                String var7 = "";
                String var9;

                for (Iterator var8 = var6.iterator(); var8.hasNext(); var7 = var7 + var9 + " ")
                {
                    var9 = (String)var8.next();
                }

                par3ICommandListener.log("White-listed players: " + var7);
            }
            else
            {
                String var10;

                if ("add".equals(var5) && var4.length == 3)
                {
                    var10 = var4[2].toLowerCase();
                    this.minecraftServer.configManager.addToWhiteList(var10);
                    this.sendNoticeToOps(par1Str, "Added " + var10 + " to white-list");
                }
                else if ("remove".equals(var5) && var4.length == 3)
                {
                    var10 = var4[2].toLowerCase();
                    this.minecraftServer.configManager.removeFromWhiteList(var10);
                    this.sendNoticeToOps(par1Str, "Removed " + var10 + " from white-list");
                }
                else if ("reload".equals(var5))
                {
                    this.minecraftServer.configManager.reloadWhiteList();
                    this.sendNoticeToOps(par1Str, "Reloaded white-list from file");
                }
            }
        }
    }

    /**
     * Print help on server commands
     */
    private void printHelp(ICommandListener par1ICommandListener)
    {
        par1ICommandListener.log("To run the server without a gui, start it like this:");
        par1ICommandListener.log("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
        par1ICommandListener.log("Console commands:");
        par1ICommandListener.log("   help  or  ?               shows this message");
        par1ICommandListener.log("   kick <player>             removes a player from the server");
        par1ICommandListener.log("   ban <player>              bans a player from the server");
        par1ICommandListener.log("   pardon <player>           pardons a banned player so that they can connect again");
        par1ICommandListener.log("   ban-ip <ip>               bans an IP address from the server");
        par1ICommandListener.log("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
        par1ICommandListener.log("   op <player>               turns a player into an op");
        par1ICommandListener.log("   deop <player>             removes op status from a player");
        par1ICommandListener.log("   tp <player1> <player2>    moves one player to the same location as another player");
        par1ICommandListener.log("   give <player> <id> [num]  gives a player a resource");
        par1ICommandListener.log("   tell <player> <message>   sends a private message to a player");
        par1ICommandListener.log("   stop                      gracefully stops the server");
        par1ICommandListener.log("   save-all                  forces a server-wide level save");
        par1ICommandListener.log("   save-off                  disables terrain saving (useful for backup scripts)");
        par1ICommandListener.log("   save-on                   re-enables terrain saving");
        par1ICommandListener.log("   list                      lists all currently connected players");
        par1ICommandListener.log("   say <message>             broadcasts a message to all players");
        par1ICommandListener.log("   time <add|set> <amount>   adds to or sets the world time (0-24000)");
        par1ICommandListener.log("   gamemode <player> <mode>  sets player\'s game mode (0 or 1)");
        par1ICommandListener.log("   toggledownfall            toggles rain on or off");
        par1ICommandListener.log("   xp <player> <amount>      gives the player the amount of xp (0-5000)");
    }

    /**
     * sends a notice to all online ops.
     */
    private void sendNoticeToOps(String par1Str, String par2Str)
    {
        String var3 = par1Str + ": " + par2Str;
        this.minecraftServer.configManager.sendChatMessageToAllOps("\u00a77(" + var3 + ")");
        minecraftLogger.info(var3);
    }

    /**
     * Parses First argument if possible; if not returns second argument
     */
    private int tryParse(String par1Str, int par2)
    {
        try
        {
            return Integer.parseInt(par1Str);
        }
        catch (NumberFormatException var4)
        {
            return par2;
        }
    }

    /**
     * Joins array elements with the delimiter String.
     */
    private String joinStrings(String[] par1ArrayOfStr, String par2Str)
    {
        int var3 = par1ArrayOfStr.length;

        if (0 == var3)
        {
            return "";
        }
        else
        {
            StringBuilder var4 = new StringBuilder();
            var4.append(par1ArrayOfStr[0]);

            for (int var5 = 1; var5 < var3; ++var5)
            {
                var4.append(par2Str).append(par1ArrayOfStr[var5]);
            }

            return var4.toString();
        }
    }
}
