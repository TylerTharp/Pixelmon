package pixelmon.gui;

import net.minecraft.src.RenderHelper;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ProgressBar {
	int value;
	private int maxValue = 100;

	public ProgressBar() {
	}

	public void setProgress(int progress){
		value = progress;
	}
	
	public void draw(int x, int y, int height, int width, int screenWidth, int screenHeight) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 1.0F);
		tessellator.addVertex(x-width/2, y, 0.0);
		tessellator.addVertex(x-width/2, y+height, 0.0);
		tessellator.addVertex(x + width/2, y+height, 0.0);
		tessellator.addVertex(x + width/2, y, 0.0);
		
		int barWidth = (int) (((float) value) / ((float) maxValue) * (((float) width) - 6f));
		tessellator.setColorRGBA_F(1.0f-((float)value/(float)maxValue)*0.8F, 0.2F+((float)value/(float)maxValue)*0.8F, 0.2F, 1.0F);
		tessellator.addVertex(x - width/2+3, y+3, 0.0);
		tessellator.addVertex(x - width/2+3, y+height-3, 0.0);
		tessellator.addVertex(x - width/2+3+barWidth, y+height-3, 0.0);
		tessellator.addVertex(x - width/2+3+barWidth, y+3, 0.0);
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glEnable(3553);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}
}
