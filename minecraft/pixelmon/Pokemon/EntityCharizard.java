package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityCharizard extends EntityGroundPixelmon
{
	
	public EntityCharizard(World world)
	{
		super(world);
		texture = "/pixelmon/image/charizard.png";
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
