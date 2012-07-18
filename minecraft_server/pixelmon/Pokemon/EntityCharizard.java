package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityCharizard extends EntityGroundPixelmon
{
	
	public EntityCharizard(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Charizard";
		isImmuneToFire = true;
		super.init();
		this.litUp = true;
		this.litLevel = 50;
	}
	
	public void evolve() 
	{
		BaseEntityPixelmon entity = new EntityCharizard(worldObj);
		helper.evolve(entity.helper);
	}

}
