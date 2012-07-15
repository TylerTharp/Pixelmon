package pixelmon;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ModelBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.Tessellator;
import net.minecraft.src.mod_Pixelmon;

import org.lwjgl.opengl.GL11;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityWaterPixelmon;
import pixelmon.entities.IHaveHelper;

public class RenderFreeWaterPixelmon extends RenderLiving{
	public RenderFreeWaterPixelmon(ModelBase par1ModelBase, float par2) { //par2 = shadow size
		super(par1ModelBase, par2);
	}

	public void doRenderLiving(EntityLiving entityLiving, double d, double d1,
			double d2, float f, float f1) {
		super.doRenderLiving(entityLiving, d, d1, d2, f, f1);
		 float var10 = entityLiving.getDistanceToEntity(this.renderManager.livingPlayer);

	        if (var10 <= (float)8)
	        {
	        	drawHealthBar(entityLiving, d, d1, d2, f, f1);
				if (entityLiving instanceof EntityWaterPixelmon)
					if (mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).isIn(((IHaveHelper)entityLiving).getHelper()))
						drawExpBar(entityLiving, d, d1, d2, f, f1);
				drawNameTag(entityLiving, d, d1, d2);
	        }
	}

	public void drawNameTag(EntityLiving entityliving, double par2,
			double par4, double par6) {
		if (Minecraft.isGuiEnabled()
				&& (entityliving instanceof EntityWaterPixelmon)) {
			EntityWaterPixelmon entitypixelmon = (EntityWaterPixelmon) entityliving;
			NBTTagCompound nbt = mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getNBT(entitypixelmon.getHelper().getPokemonId());
			boolean flag;
			if(nbt == null){
				flag = true;
			}
			else{
				flag = MathHelper.stringNullOrLengthZero(nbt.getString("Nickname"));
			}
			String s = " Lv: " + entitypixelmon.helper.getLvl().getLevel() + " ";
			s += (flag ? entitypixelmon.name : nbt.getString("Nickname"));
			if (mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).isIn(entitypixelmon.helper)) {
				s += " (" + ModLoader.getMinecraftInstance().thePlayer.username + ")";
			} else {
				s += " (Wild)";
			}
			if (!entitypixelmon.isSneaking()) {
				renderLivingLabel(entitypixelmon, s, par2, par4, par6, 64);
			}
		}
	}


	public void drawHealthBar(EntityLiving entityLiving, double d, double d1,
			double d2, float f, float f1) {
		float f2 = 1.6F;
		float f3 = 0.01666667F * f2;
		if ((float) entityLiving
				.getDistanceToEntity(renderManager.livingPlayer) < 28F
				&& Minecraft.isGuiEnabled()) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) d + 0.0F, (float) d1 + 1.1F, (float) d2);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-f3, -f3, f3);
			GL11.glDisable(2896 /* GL_LIGHTING */);
			GL11.glDepthMask(false);
			GL11.glDisable(2929 /* GL_DEPTH_TEST */);
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 771);
			Tessellator tessellator = Tessellator.instance;
			byte byte0 = -25;
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			tessellator.startDrawingQuads();
			float f5 = ((EntityWaterPixelmon) entityLiving).getHealth();
			float f6 = ((EntityWaterPixelmon) entityLiving).stats.HP;
			float f8 = 50F * (f5 / f6);
			tessellator.setColorRGBA_F(1.0F, 0.0F, 0.0F, 1.0F);
			tessellator.addVertex(-25F + f8, -7 + byte0, 0.0D);
			tessellator.addVertex(-25F + f8, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -7 + byte0, 0.0D);
			tessellator.setColorRGBA_F(0.0F, 1.0F, 0.0F, 1.0F);
			tessellator.addVertex(-25D, -7 + byte0, 0.0D);
			tessellator.addVertex(-25D, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -7 + byte0, 0.0D);
			tessellator.draw();
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			GL11.glEnable(2929 /* GL_DEPTH_TEST */);
			GL11.glDepthMask(true);
			GL11.glEnable(2896 /* GL_LIGHTING */);
			GL11.glDisable(3042 /* GL_BLEND */);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}
	
	public void drawExpBar(EntityLiving entityLiving, double d, double d1,
			double d2, float f, float f1) {
		float f2 = 1.6F;
		float f3 = 0.01666667F * f2;
		if ((float) entityLiving
				.getDistanceToEntity(renderManager.livingPlayer) < 28F
				&& Minecraft.isGuiEnabled()) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) d + 0.0F, (float) d1 + 1.1F, (float) d2);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-f3, -f3, f3);
			GL11.glDisable(2896 /* GL_LIGHTING */);
			GL11.glDepthMask(false);
			GL11.glDisable(2929 /* GL_DEPTH_TEST */);
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 771);
			Tessellator tessellator = Tessellator.instance;
			byte byte0 = -20;
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			tessellator.startDrawingQuads();
			float f5 = ((IHaveHelper) entityLiving).getHelper().getLvl().getExp();
			float f6 = ((IHaveHelper) entityLiving).getHelper().getLvl().getExpToNextLevel();
			if (f5 >= f6)
				f5 = 56;
			float f8 = 50F * (f5 / f6);
			tessellator.setColorRGBA_F(0.0039F, 0.03137F, 0.4196F, 1.0F);
			tessellator.addVertex(-25F + f8, -7 + byte0, 0.0D);
			tessellator.addVertex(-25F + f8, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -7 + byte0, 0.0D);
			tessellator.setColorRGBA_F(0.0F, 0.8901F, 0.8901F, 1.0F);
			tessellator.addVertex(-25D, -7 + byte0, 0.0D);
			tessellator.addVertex(-25D, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -7 + byte0, 0.0D);
			tessellator.draw();
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			GL11.glEnable(2929 /* GL_DEPTH_TEST */);
			GL11.glDepthMask(true);
			GL11.glEnable(2896 /* GL_LIGHTING */);
			GL11.glDisable(3042 /* GL_BLEND */);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}

	protected void preRenderScale(EntityWaterPixelmon entity, float f) {
		GL11.glScalef(entity.scale*1F, entity.scale*1F, entity.scale*1F);
	}

	protected void preRenderCallback(EntityLiving entityliving, float f) {
		preRenderScale((EntityWaterPixelmon) entityliving, f);
	}

}
