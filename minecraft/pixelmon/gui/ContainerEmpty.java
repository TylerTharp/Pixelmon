package pixelmon.gui;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;

public class ContainerEmpty extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
}
