package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelBulbasaur extends ModelBase//Quadruped
{
	public ModelBulbasaur()
	{
		//super(6, 0.0F);
		Left_ear = new ModelRenderer(this, 27, 0);
		Left_ear.addBox(2, -6, -4, 2, 2, 4, 0F);
		Left_ear.setRotationPoint(0, 16, -7);
		
		Left_ear.rotateAngleX = 0F;
		Left_ear.rotateAngleY = 0F;
		Left_ear.rotateAngleZ = 0F;
		
		Right_Ear = new ModelRenderer(this, 27, 0);
		Right_Ear.addBox(-4, -6, -4, 2, 2, 4, 0F);
		Right_Ear.setRotationPoint(0, 16, -7);
		
		Right_Ear.rotateAngleX = 0F;
		Right_Ear.rotateAngleY = 0F;
		Right_Ear.rotateAngleZ = 0F;
		
		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-5, -7, -7, 10, 14, 7, 0F);
		Body.setRotationPoint(0, 14, 1);
		
		Body.rotateAngleX = 1.396263F;
		Body.rotateAngleY = 0F;
		Body.rotateAngleZ = 0F;
		
		Head = new ModelRenderer(this, 32, 17);
		Head.addBox(-4, -4, -8, 8, 7, 8, 0F);
		Head.setRotationPoint(0, 16, -7);
		
		Head.rotateAngleX = 0F;
		Head.rotateAngleY = 0F;
		Head.rotateAngleZ = 0F;
		
		Bulb_Top = new ModelRenderer(this, 14, 21);
		Bulb_Top.addBox(-3, -3, 5, 6, 6, 3, 0F);
		Bulb_Top.setRotationPoint(0, 14, 1);
		
		Bulb_Top.rotateAngleX = 1.396263F;
		Bulb_Top.rotateAngleY = 0F;
		Bulb_Top.rotateAngleZ = 0F;
		
		Bulb_Base = new ModelRenderer(this, 34, 2);
		Bulb_Base.addBox(-5, -5, 0, 10, 10, 5, 0F);
		Bulb_Base.setRotationPoint(0, 14, 1);
		
		Bulb_Base.rotateAngleX = 1.396263F;
		Bulb_Base.rotateAngleY = 0F;
		Bulb_Base.rotateAngleZ = 0F;
		
		Leg_FrontRight = new ModelRenderer(this, 0, 21);
		Leg_FrontRight.addBox(-3, 0, -2, 3, 8, 3, 0F);
		Leg_FrontRight.setRotationPoint(-5, 16, -3);
		
		Leg_FrontRight.rotateAngleX = 0F;
		Leg_FrontRight.rotateAngleY = 0F;
		Leg_FrontRight.rotateAngleZ = 0F;
		
		Leg_RearRight = new ModelRenderer(this, 0, 21);
		Leg_RearRight.addBox(-3, 0, -2, 3, 6, 3, 0F);
		Leg_RearRight.setRotationPoint(-5, 18, 5);
		
		Leg_RearRight.rotateAngleX = 0F;
		Leg_RearRight.rotateAngleY = 0F;
		Leg_RearRight.rotateAngleZ = 0F;
		
		Leg_RearLeft = new ModelRenderer(this, 0, 21);
		Leg_RearLeft.addBox(0, 0, -2, 3, 6, 3, 0F);
		Leg_RearLeft.setRotationPoint(5, 18, 5);
		
		Leg_RearLeft.rotateAngleX = 0F;
		Leg_RearLeft.rotateAngleY = 0F;
		Leg_RearLeft.rotateAngleZ = 0F;
		
		Leg_FrontLeft = new ModelRenderer(this, 0, 21);
		Leg_FrontLeft.addBox(0, 0, -2, 3, 8, 3, 0F);
		Leg_FrontLeft.setRotationPoint(5, 16, -3);
		
		Leg_FrontLeft.rotateAngleX = 0F;
		Leg_FrontLeft.rotateAngleY = 0F;
		Leg_FrontLeft.rotateAngleZ = 0F;
	}
	public void render(Entity var1, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(var1, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Left_ear.render(f5);
		Right_Ear.render(f5);
		Body.render(f5);
		Head.render(f5);
		Bulb_Top.render(f5);
		Bulb_Base.render(f5);
		Leg_FrontRight.render(f5);
		Leg_RearRight.render(f5);
		Leg_RearLeft.render(f5);
		Leg_FrontLeft.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		//super.setRotationAngles(f, f1, f2, f3, f4, f5);
		Head.rotateAngleX = f4 / 57.29578F;
        Head.rotateAngleY = f3 / 57.29578F;
		Left_ear.rotateAngleX = f4 / 57.29578F;
        Left_ear.rotateAngleY = f3 / 57.29578F;
		Right_Ear.rotateAngleX = f4 / 57.29578F;
        Right_Ear.rotateAngleY = f3 / 57.29578F;
        Leg_RearRight.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        Leg_RearLeft.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg_FrontRight.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        Leg_FrontLeft.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	}
	
	//fields
	public ModelRenderer Left_ear;
	public ModelRenderer Right_Ear;
	public ModelRenderer Body;
	public ModelRenderer Head;
	public ModelRenderer Bulb_Top;
	public ModelRenderer Bulb_Base;
	public ModelRenderer Leg_FrontRight;
	public ModelRenderer Leg_RearRight;
	public ModelRenderer Leg_RearLeft;
	public ModelRenderer Leg_FrontLeft;

}
