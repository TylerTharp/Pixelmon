package pixelmon;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.mod_Pixelmon;

public class WorldGenWaterStoneOre extends WorldGenerator{
	
	public boolean generate(World world, Random rand, int x, int y, int z){
		if((world.getBlockId(x, y, z) == Block.waterStill.blockID || world.getBlockId(x, y, z) == Block.waterMoving.blockID)
				&& (world.getBlockId(x, y - 1, z) != Block.waterStill.blockID && 
				world.getBlockId(x, y, z) != Block.waterMoving.blockID)){
			int pos = 0;
			while(pos > 5){
				pos++;
				if(world.getBlockId(x, y + pos, z) == Block.waterStill.blockID || world.getBlockId(x, y + pos, z) == Block.waterMoving.blockID){
					
				}
				else{
					return false;
				}
				
			}
			world.setBlockWithNotify(x, y, z, mod_Pixelmon.waterStoneOre.blockID);
			return true;
		}
		return false;
	}

}
