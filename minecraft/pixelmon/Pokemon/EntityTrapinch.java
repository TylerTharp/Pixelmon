package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.Block;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityTrapinch extends EntityGroundPixelmon
{
	
	public EntityTrapinch(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Trapinch";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{	
		BaseEntityPixelmon entity = new EntityVibrava(worldObj);
		helper.evolve(entity.helper);
		
	}
	
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
	    return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.sand.blockID;
	}
}
