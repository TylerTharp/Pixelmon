package pixelmon.gui;

import net.minecraft.src.RenderHelper;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ProgressBar {
	int value;
	private int maxValue = 100;
	private int height;
	private int width;
	private int x;
	private int y;

	public ProgressBar(int height, int width, int x, int y) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}

	public void setProgress(int progress){
		value = progress;
	}
	
	public void draw() {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) (x) , (float) (y), 50.0F);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 1.0F);
		tessellator.addVertex(-320, 0, 0.0);
		tessellator.addVertex(-320, height, 0.0);
		tessellator.addVertex(-320 + width, height, 0.0);
		tessellator.addVertex(-320 + width, 0, 0.0);
		
		int barWidth = (int) (((float) value) / ((float) maxValue) * (((float) width) - 4f));
		tessellator.setColorRGBA_F(0.2F, 1.0F, 0.2F, 1.0F);
		tessellator.addVertex(-323 + width - barWidth, 3, 0.0);
		tessellator.addVertex(-323 + width - barWidth, height-3, 0.0);
		tessellator.addVertex(-323 + width, height-3, 0.0);
		tessellator.addVertex(-323 + width, 3, 0.0);
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}
}
