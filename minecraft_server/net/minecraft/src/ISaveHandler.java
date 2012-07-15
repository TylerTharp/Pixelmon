package net.minecraft.src;

import java.io.File;
import java.util.List;

public interface ISaveHandler
{
    /**
     * Loads and returns the world info
     */
    WorldInfo loadWorldInfo();

    /**
     * Checks the session lock to prevent save collisions
     */
    void checkSessionLock();

    /**
     * initializes and returns the chunk loader for the specified world provider
     */
    IChunkLoader getChunkLoader(WorldProvider var1);

    /**
     * saves level.dat and backs up the existing one to level.dat_old
     */
    void saveWorldInfoAndPlayer(WorldInfo var1, List var2);

    /**
     * used to update level.dat from old format to MCRegion format
     */
    void saveWorldInfo(WorldInfo var1);

    IPlayerFileData getPlayerNBTManager();

    void func_22093_e();

    /**
     * Gets the file location of the given map
     */
    File getMapFileFromName(String var1);
}
