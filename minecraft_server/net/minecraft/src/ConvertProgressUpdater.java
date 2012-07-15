package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class ConvertProgressUpdater implements IProgressUpdate
{
    /** lastTimeMillis */
    private long lastTimeMillis;

    /** Reference to the MinecraftServer object. */
    final MinecraftServer mcServer;

    public ConvertProgressUpdater(MinecraftServer par1MinecraftServer)
    {
        this.mcServer = par1MinecraftServer;
        this.lastTimeMillis = System.currentTimeMillis();
    }

    /**
     * Shows the 'Saving level' string.
     */
    public void displaySavingString(String par1Str) {}

    /**
     * Updates the progress bar on the loading screen to the specified amount. Args: loadProgress
     */
    public void setLoadingProgress(int par1)
    {
        if (System.currentTimeMillis() - this.lastTimeMillis >= 1000L)
        {
            this.lastTimeMillis = System.currentTimeMillis();
            MinecraftServer.logger.info("Converting... " + par1 + "%");
        }
    }

    /**
     * Displays a string on the loading screen supposed to indicate what is being done currently.
     */
    public void displayLoadingString(String par1Str) {}
}
