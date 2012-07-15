package pixelmon;

import net.minecraft.src.Entity;
import net.minecraft.src.Item;
import net.minecraft.src.RenderSnowball;
import net.minecraft.src.Tessellator;
import net.minecraft.src.mod_Pixelmon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.entities.EntityEmptyPokeBall;


public class RenderEmptyPokeball extends RenderSnowball
{

	private int itemIconIndex;
	
	public RenderEmptyPokeball() 
	{
		super(0);
		itemIconIndex = 0;
	}
	
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		EntityEmptyPokeBall entity = (EntityEmptyPokeBall)par1Entity;
		//itemIconIndex = entity.ballBonus;
		Item item = null;
		if(entity.ballBonus == 1) item = mod_Pixelmon.pokeBall;
		if(entity.ballBonus == 1.5) item = mod_Pixelmon.greatBall;
		if(entity.ballBonus == 2) item = mod_Pixelmon.ultraBall;
		if(entity.ballBonus == 255) item = mod_Pixelmon.masterBall;
		itemIconIndex = item.getIconFromDamage(0);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        loadTexture("/gui/items.png");
        Tessellator tessellator = Tessellator.instance;
        func_40265_a(tessellator, par1Entity.rotationPitch, itemIconIndex);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
	}
	
    private void func_40265_a(Tessellator par1Tessellator, float angle, int par2)
    {
        float f = (float)((par2 % 16) * 16 + 0) / 256F;
        float f1 = (float)((par2 % 16) * 16 + 16) / 256F;
        float f2 = (float)((par2 / 16) * 16 + 0) / 256F;
        float f3 = (float)((par2 / 16) * 16 + 16) / 256F;
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(angle, 0, 0, 1);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
        par1Tessellator.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
        par1Tessellator.addVertexWithUV(f4 - f5, f4 - f6, 0.0D, f1, f2);
        par1Tessellator.addVertexWithUV(0.0F - f5, f4 - f6, 0.0D, f, f2);
        par1Tessellator.draw();
    }
	
	
	
}