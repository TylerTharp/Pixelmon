package pixelmon;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.mod_Pixelmon;

public class WorldGenWaterStoneOre extends WorldGenerator{
	
	public boolean generate(World world, Random rand, int x, int y, int z){
		if(world.getBlockId(x, y, z) != Block.waterStill.blockID && !(world.getBlockId(x, y - 1, z) == Block.waterStill.blockID)){
			return false;
		}
		world.setBlockWithNotify(x, y, z, mod_Pixelmon.waterStoneOre.blockID);
		return true;
	}

}
