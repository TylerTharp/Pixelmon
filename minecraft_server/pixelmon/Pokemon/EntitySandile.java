package pixelmon.Pokemon;

import net.minecraft.src.*;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import pixelmon.entities.PixelmonEntityList.ClassType;

public class EntitySandile extends EntityGroundPixelmon
{
	
	public EntitySandile(World world)
	{
		super(world);
		texture = "/pixelmon/image/sandile.png";
		init();
	}

	public void init()
	{
		name = "Sandile";
		isImmuneToFire = false;
		super.init();
	}
	public void evolve() 
	{
		BaseEntityPixelmon entity = new EntityKrokorok(worldObj);
		helper.evolve(entity.helper);
	}
	
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
	    return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.sand.blockID;
	}
}