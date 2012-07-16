package pixelmon.render;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelVillager;
import net.minecraft.src.RenderLiving;

public class RenderTrainer extends RenderLiving {
	public RenderTrainer(ModelBase par1ModelBase, float par2) { // par2 =
		// shadow
		// size
		super(par1ModelBase, par2);
	}

	public void doRenderLiving(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving(entityLiving, d, d1, d2, f, f1);
	}
}
