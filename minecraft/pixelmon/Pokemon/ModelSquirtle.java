package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelSquirtle extends ModelBase
{
	public 	ModelSquirtle()
	{
		Shell_Middle = new ModelRenderer(this, 27, 0);
		Shell_Middle.addBox(-4F, 0F, -1F, 8, 7, 2, 0F);
		Shell_Middle.setRotationPoint(0F, 14F, 0F);
		
		Shell_Middle.rotateAngleX = 0F;
		Shell_Middle.rotateAngleY = 0F;
		Shell_Middle.rotateAngleZ = 0F;
		Shell_Middle.mirror = false;
		
		Shell_Front = new ModelRenderer(this, 50, 0);
		Shell_Front.addBox(-3F, 1F, -2F, 6, 6, 1, 0F);
		Shell_Front.setRotationPoint(0F, 13F, 0F);
		
		Shell_Front.rotateAngleX = 0F;
		Shell_Front.rotateAngleY = 0F;
		Shell_Front.rotateAngleZ = 0F;
		Shell_Front.mirror = false;
		
		Shell_Back = new ModelRenderer(this, 32, 11);
		Shell_Back.addBox(-3F, 1F, 1F, 6, 6, 2, 0F);
		Shell_Back.setRotationPoint(0F, 13F, 0F);
		
		Shell_Back.rotateAngleX = 0F;
		Shell_Back.rotateAngleY = 0F;
		Shell_Back.rotateAngleZ = 0F;
		Shell_Back.mirror = false;
		
		Head = new ModelRenderer(this, 48, 24);
		Head.addBox(-2F, -4F, -2F, 4, 4, 4, 0F);
		Head.setRotationPoint(0F, 14F, -1F);
		
		Head.rotateAngleX = 0F;
		Head.rotateAngleY = 0F;
		Head.rotateAngleZ = 0F;
		Head.mirror = false;
		
		Arm_Right = new ModelRenderer(this, 0, 26);
		Arm_Right.addBox(-1F, 0F, -1F, 2, 4, 2, 0F);
		Arm_Right.setRotationPoint(-3F, 15F, -1F);
		
		Arm_Right.rotateAngleX = -0.4363323F;
		Arm_Right.rotateAngleY = 0.3490658F;
		Arm_Right.rotateAngleZ = 0F;
		Arm_Right.mirror = false;
		
		Arm_Left = new ModelRenderer(this, 0, 26);
		Arm_Left.addBox(-1F, 0F, -1F, 2, 4, 2, 0F);
		Arm_Left.setRotationPoint(3F, 15F, -1F);
		
		Arm_Left.rotateAngleX = -0.4363323F;
		Arm_Left.rotateAngleY = -0.3490658F;
		Arm_Left.rotateAngleZ = 0F;
		Arm_Left.mirror = true;
		
		Leg_Left = new ModelRenderer(this, 8, 26);
		Leg_Left.addBox(-1F, 0F, -1F, 2, 4, 2, 0F);
		Leg_Left.setRotationPoint(2F, 20F, -1F);
		
		Leg_Left.rotateAngleX = 0F;
		Leg_Left.rotateAngleY = 0F;
		Leg_Left.rotateAngleZ = 0F;
		Leg_Left.mirror = true;
		
		Leg_Right = new ModelRenderer(this, 8, 26);
		Leg_Right.addBox(-1F, 0F, -1F, 2, 4, 2, 0F);
		Leg_Right.setRotationPoint(-2F, 20F, -1F);
		
		Leg_Right.rotateAngleX = 0F;
		Leg_Right.rotateAngleY = 0F;
		Leg_Right.rotateAngleZ = 0F;
		Leg_Right.mirror = false;
		
		Tail_Base = new ModelRenderer(this, 0, 12);
		Tail_Base.addBox(-1F, 0F, -1F, 2, 2, 1, 0F);
		Tail_Base.setRotationPoint(0F, 20F, 1F);
		
		Tail_Base.rotateAngleX = 0.9424778F;
		Tail_Base.rotateAngleY = 0.02094395F;
		Tail_Base.rotateAngleZ = 0F;
		Tail_Base.mirror = false;
		
		Tail_Mid = new ModelRenderer(this, 0, 16);
		Tail_Mid.addBox(-1F, 0F, -1F, 2, 3, 2, 0F);
		Tail_Mid.setRotationPoint(0F, 21F, 2F);
		
		Tail_Mid.rotateAngleX = 1.570796F;
		Tail_Mid.rotateAngleY = 0F;
		Tail_Mid.rotateAngleZ = 0F;
		Tail_Mid.mirror = false;
		
		Tail_End = new ModelRenderer(this, 6, 2);
		Tail_End.addBox(-1F, 0F, -1F, 2, 4, 4, 0F);
		Tail_End.setRotationPoint(0F, 21F, 5F);
		
		Tail_End.rotateAngleX = 1.658063F;
		Tail_End.rotateAngleY = 0F;
		Tail_End.rotateAngleZ = 0F;
		Tail_End.mirror = false;
		
		
	}

	public void render(Entity var1, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(var1, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Shell_Middle.render(f5);
		Shell_Front.render(f5);
		Shell_Back.render(f5);
		Head.render(f5);
		Arm_Right.render(f5);
		Arm_Left.render(f5);
		Leg_Left.render(f5);
		Leg_Right.render(f5);
		Tail_Base.render(f5);
		Tail_Mid.render(f5);
		Tail_End.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
        Head.rotateAngleY = f3 / (180F / (float)Math.PI);
        Head.rotateAngleX = f4 / (180F / (float)Math.PI);
        Arm_Right.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        Arm_Left.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        Arm_Right.rotateAngleZ = 0.0F;
        Arm_Left.rotateAngleZ = 0.0F;
        Leg_Right.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Leg_Left.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        Leg_Right.rotateAngleY = 0.0F;
        Leg_Left.rotateAngleY = 0.0F;
	}

	//fields
	public ModelRenderer Shell_Middle;
	public ModelRenderer Shell_Front;
	public ModelRenderer Shell_Back;
	public ModelRenderer Head;
	public ModelRenderer Arm_Right;
	public ModelRenderer Arm_Left;
	public ModelRenderer Leg_Left;
	public ModelRenderer Leg_Right;
	public ModelRenderer Tail_Base;
	public ModelRenderer Tail_Mid;
	public ModelRenderer Tail_End;
}
