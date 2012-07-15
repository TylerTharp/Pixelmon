package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelArbok extends ModelBase
{
  //fields
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tail4;
    ModelRenderer Tail5;
    ModelRenderer Tail6;
    ModelRenderer Tail7;
    ModelRenderer Tail8;
    ModelRenderer Tail9;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Body5;
    ModelRenderer Body6;
    ModelRenderer Body7;
    ModelRenderer Head1;
    ModelRenderer Head2;
    ModelRenderer Head3;
    ModelRenderer Head4;
    ModelRenderer Teeth1;
    ModelRenderer Teeth2;
    ModelRenderer Teeth3;
    ModelRenderer Teeth4;
    ModelRenderer Segment3;
    ModelRenderer Segment4;
    ModelRenderer Segment5;
    ModelRenderer Segment6;
    ModelRenderer Segment7;
    ModelRenderer Segment8;
    ModelRenderer Segment9;
  
  public ModelArbok()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Tail1 = new ModelRenderer(this, 99, 109);
      Tail1.addBox(-3.5F, 0F, 0F, 7, 11, 7);
      Tail1.setRotationPoint(0F, 0F, -16F);
      Tail1.setTextureSize(128, 128);
      Tail1.mirror = true;
      setRotation(Tail1, 0.0349066F, 0F, 0F);
      Tail2 = new ModelRenderer(this, 89, 88);
      Tail2.addBox(-3F, 0F, 0F, 6, 6, 13);
      Tail2.setRotationPoint(0F, 7F, -11F);
      Tail2.setTextureSize(128, 128);
      Tail2.mirror = true;
      setRotation(Tail2, -0.8901179F, 0F, 0F);
      Body1 = new ModelRenderer(this, 103, 0);
      Body1.addBox(-3.5F, 0F, 0F, 7, 22, 5);
      Body1.setRotationPoint(0F, -22F, -15F);
      Body1.setTextureSize(128, 128);
      Body1.mirror = true;
      setRotation(Body1, 0F, 0F, 0F);
      Body2 = new ModelRenderer(this, 84, 0);
      Body2.addBox(-7F, 0F, 0F, 7, 22, 2);
      Body2.setRotationPoint(-3F, -22F, -14F);
      Body2.setTextureSize(128, 128);
      Body2.mirror = true;
      setRotation(Body2, 0F, 0F, 0F);
      Body3 = new ModelRenderer(this, 67, 0);
      Body3.addBox(-6F, 0F, 0F, 6, 18, 2);
      Body3.setRotationPoint(-10F, -20F, -14F);
      Body3.setTextureSize(128, 128);
      Body3.mirror = true;
      setRotation(Body3, 0F, 0F, 0F);
      Body4 = new ModelRenderer(this, 104, 31);
      Body4.addBox(-2F, 0F, 0F, 2, 14, 2);
      Body4.setRotationPoint(-16F, -18F, -14F);
      Body4.setTextureSize(128, 128);
      Body4.mirror = true;
      setRotation(Body4, 0F, 0F, 0F);
      Body5 = new ModelRenderer(this, 84, 26);
      Body5.addBox(0F, 0F, 0F, 7, 22, 2);
      Body5.setRotationPoint(3F, -22F, -14F);
      Body5.setTextureSize(128, 128);
      Body5.mirror = true;
      setRotation(Body5, 0F, 0F, 0F);
      Body6 = new ModelRenderer(this, 67, 23);
      Body6.addBox(0F, 0F, 0F, 6, 18, 2);
      Body6.setRotationPoint(10F, -20F, -14F);
      Body6.setTextureSize(128, 128);
      Body6.mirror = true;
      setRotation(Body6, 0F, 0F, 0F);
      Body7 = new ModelRenderer(this, 117, 31);
      Body7.addBox(0F, 0F, 0F, 2, 14, 2);
      Body7.setRotationPoint(16F, -18F, -14F);
      Body7.setTextureSize(128, 128);
      Body7.mirror = true;
      setRotation(Body7, 0F, 0F, 0F);
      Head1 = new ModelRenderer(this, 0, 114);
      Head1.addBox(-3.5F, -8F, 0F, 7, 8, 5);
      Head1.setRotationPoint(0F, -17.6F, -12.5F);
      Head1.setTextureSize(128, 128);
      Head1.mirror = true;
      setRotation(Head1, 1.047198F, 0F, 0F);
      Head2 = new ModelRenderer(this, 0, 98);
      Head2.addBox(-3.5F, 0F, 0F, 7, 3, 10);
      Head2.setRotationPoint(0F, -25F, -26.9F);
      Head2.setTextureSize(128, 128);
      Head2.mirror = true;
      setRotation(Head2, 0.1047198F, 0F, 0F);
      Head3 = new ModelRenderer(this, 0, 87);
      Head3.addBox(-2.5F, 0F, -7F, 5, 1, 8);
      Head3.setRotationPoint(0F, -22F, -19F);
      Head3.setTextureSize(128, 128);
      Head3.mirror = true;
      setRotation(Head3, 0.6108652F, 0F, 0F);
      Head4 = new ModelRenderer(this, 0, 74);
      Head4.addBox(0F, 0F, -10F, 1, 0, 11);
      Head4.setRotationPoint(0F, -22F, -19F);
      Head4.setTextureSize(128, 128);
      Head4.mirror = true;
      setRotation(Head4, 0.5759587F, 0F, 0F);
      Teeth1 = new ModelRenderer(this, 0, 69);
      Teeth1.addBox(0F, -1F, 0F, 1, 2, 1);
      Teeth1.setRotationPoint(1.4F, -19F, -24.8F);
      Teeth1.setTextureSize(128, 128);
      Teeth1.mirror = true;
      setRotation(Teeth1, 0.5759587F, 0F, 0F);
      Teeth2 = new ModelRenderer(this, 0, 70);
      Teeth2.addBox(0F, -1F, 0F, 1, 2, 1);
      Teeth2.setRotationPoint(-2.4F, -19F, -24.8F);
      Teeth2.setTextureSize(128, 128);
      Teeth2.mirror = true;
      setRotation(Teeth2, 0.5759587F, 0F, 0F);
      Teeth3 = new ModelRenderer(this, 0, 70);
      Teeth3.addBox(0F, 0F, 0F, 1, 3, 1);
      Teeth3.setRotationPoint(-3F, -23F, -26.6F);
      Teeth3.setTextureSize(128, 128);
      Teeth3.mirror = true;
      setRotation(Teeth3, 0.1919862F, 0F, 0F);
      Teeth4 = new ModelRenderer(this, 0, 70);
      Teeth4.addBox(0F, 0F, 0F, 1, 3, 1);
      Teeth4.setRotationPoint(2F, -23F, -26.6F);
      Teeth4.setTextureSize(128, 128);
      Teeth4.mirror = true;
      setRotation(Teeth4, 0.1919862F, 0F, 0F);
    Segment3 = new ModelRenderer(this, "Segment3");
    Segment3.setRotationPoint(0F, 18F, -7F);
    setRotation(Segment3, 0F, 0F, 0F);
    Segment3.mirror = true;
      ModelRenderer Tail3 = new ModelRenderer(this, 89, 88);
      Tail3 = new ModelRenderer(this, 89, 88);
      Tail3.addBox(-3F, -3F, 0F, 6, 6, 13);
      Tail3.setRotationPoint(0F, 0F, 0F);
      Tail3.setTextureSize(128, 128);
      Tail3.mirror = true;
      setRotation(Tail3, -0.2443461F, 0F, 0F);
      Segment3.addChild(Tail3);
    Segment4 = new ModelRenderer(this, "Segment4");
    Segment4.setRotationPoint(0F, 3F, 12F);
    setRotation(Segment4, 0F, 0F, 0F);
    Segment4.mirror = true;
      ModelRenderer Tail4 = new ModelRenderer(this, 89, 88);
      Tail4 = new ModelRenderer(this, 89, 88);
      Tail4.addBox(-3F, -3F, 0F, 6, 6, 13);
      Tail4.setRotationPoint(0F, 0F, 0F);
      Tail4.setTextureSize(128, 128);
      Tail4.mirror = true;
      setRotation(Tail4, 0F, 0F, 0F);
      Segment4.addChild(Tail4);
      Segment3.addChild(Segment4);
    Segment5 = new ModelRenderer(this, "Segment5");
    Segment5.setRotationPoint(0F, 0F, 12F);
    setRotation(Segment5, 0F, 0F, 0F);
    Segment5.mirror = true;
    ModelRenderer Tail5 = new ModelRenderer(this, 89, 88);
      Tail5 = new ModelRenderer(this, 89, 88);
      Tail5.addBox(-3F, -3F, 0F, 6, 6, 13);
      Tail5.setRotationPoint(0F, 0F, 0F);
      Tail5.setTextureSize(128, 128);
      Tail5.mirror = true;
      setRotation(Tail5, 0F, 0F, 0F);
      Segment5.addChild(Tail5);
      Segment4.addChild(Segment5);
    Segment6 = new ModelRenderer(this, "Segment6");
    Segment6.setRotationPoint(0F, 0.5F, 12F);
    setRotation(Segment6, 0F, 0F, 0F);
    Segment6.mirror = true;
      ModelRenderer Tail6 = new ModelRenderer(this, 44, 110);
      Tail6 = new ModelRenderer(this, 44, 110);
      Tail6.addBox(-2.5F, -2.5F, 0F, 5, 5, 11);
      Tail6.setRotationPoint(0F, 0F, 0F);
      Tail6.setTextureSize(128, 128);
      Tail6.mirror = true;
      setRotation(Tail6, 0F, 0F, 0F);
      Segment6.addChild(Tail6);
      Segment5.addChild(Segment6);
    Segment7 = new ModelRenderer(this, "Segment7");
    Segment7.setRotationPoint(0F, 0.5F, 10F);
    setRotation(Segment7, 0F, 0F, 0F);
    Segment7.mirror = true;
      ModelRenderer Tail7 = new ModelRenderer(this, 45, 93);
      Tail7 = new ModelRenderer(this, 45, 93);
      Tail7.addBox(-2F, -2F, 0F, 4, 4, 10);
      Tail7.setRotationPoint(0F, 0F, 0F);
      Tail7.setTextureSize(128, 128);
      Tail7.mirror = true;
      setRotation(Tail7, 0F, 0F, 0F);
      Segment7.addChild(Tail7);
      Segment6.addChild(Segment7);
    Segment8 = new ModelRenderer(this, "Segment8");
    Segment8.setRotationPoint(0F, 0.5F, 9F);
    setRotation(Segment8, 0F, 0F, 0F);
    Segment8.mirror = true;
      ModelRenderer Tail8 = new ModelRenderer(this, 0, 24);
      Tail8 = new ModelRenderer(this, 0, 24);
      Tail8.addBox(-1.5F, -1.5F, 0F, 3, 3, 8);
      Tail8.setRotationPoint(0F, 0F, 0F);
      Tail8.setTextureSize(128, 128);
      Tail8.mirror = true;
      setRotation(Tail8, 0F, 0F, 0F);
      Segment8.addChild(Tail8);
      Segment7.addChild(Segment8);
    Segment9 = new ModelRenderer(this, "Segment9");
    Segment9.setRotationPoint(0F, 0.5F, 7F);
    setRotation(Segment9, 0F, 0F, 0F);
    Segment9.mirror = true;
      ModelRenderer Tail9 = new ModelRenderer(this, 0, 0);
      Tail9 = new ModelRenderer(this, 0, 0);
      Tail9.addBox(-1F, -1F, 0F, 2, 2, 6);
      Tail9.setRotationPoint(0F, 0F, 0F);
      Tail9.setTextureSize(128, 128);
      Tail9.mirror = true;
      setRotation(Tail9, 0F, 0F, 0F);
      Segment9.addChild(Tail9);
      Segment8.addChild(Segment9);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    
    Body1.render(f5);
    Body2.render(f5);
    Body3.render(f5);
    Body4.render(f5);
    Body5.render(f5);
    Body6.render(f5);
    Body7.render(f5);
    Head1.render(f5);
    Head2.render(f5);
    Head3.render(f5);
    Head4.render(f5);
    Teeth1.render(f5);
    Teeth2.render(f5);
    Teeth3.render(f5);
    Teeth4.render(f5);
    Segment3.render(f5);
    Tail1.render(f5);
    Tail2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
    float PI = (float) Math.PI;
	float TopAngle = 1 * PI / 4;
	float initialOffset = PI / 2;
	float offset = PI * 3 / 11;
	float animationSpeed = -0.35f;
	float dampingFactor = 0.99f;
	float currentAngle = 0;
	Segment3.rotateAngleY = ((float) Math.pow(dampingFactor, -5)) * TopAngle * (MathHelper.cos(animationSpeed * f + initialOffset));
	currentAngle = Segment3.rotateAngleY;
	Segment4.rotateAngleY = ((float) Math.pow(dampingFactor, 2)) * TopAngle * (MathHelper.cos(animationSpeed * f + 1f * offset + initialOffset))
			- currentAngle;
	currentAngle = Segment3.rotateAngleY + Segment4.rotateAngleY;
	Segment5.rotateAngleY = ((float) Math.pow(dampingFactor, 3)) * TopAngle
			* (MathHelper.cos(animationSpeed * f + 1.1f * 2 * offset + PI / 9 + initialOffset)) - currentAngle;
	currentAngle = Segment3.rotateAngleY + Segment4.rotateAngleY + Segment5.rotateAngleY;
	Segment6.rotateAngleY = ((float) Math.pow(dampingFactor, 5)) * TopAngle
			* (MathHelper.cos(animationSpeed * f + 1.2f * 3 * offset + 2 * PI / 9 + initialOffset)) - currentAngle;// -PI/6;
	currentAngle = Segment3.rotateAngleY + Segment4.rotateAngleY + Segment5.rotateAngleY + Segment6.rotateAngleY;
	Segment7.rotateAngleY = ((float) Math.pow(dampingFactor, 6)) * TopAngle
			* (MathHelper.cos(animationSpeed * f + 1.3f * 4 * offset + PI / 9 + initialOffset)) - currentAngle;// -PI/7;
	currentAngle = currentAngle + Segment7.rotateAngleY;
	Segment8.rotateAngleY = ((float) Math.pow(dampingFactor, 7)) * TopAngle * (MathHelper.cos(animationSpeed * f + 1.4f * 5 * offset + initialOffset))
			- currentAngle;// -PI/8;
	currentAngle = currentAngle + Segment8.rotateAngleY;
	Segment9.rotateAngleY = ((float) Math.pow(dampingFactor, 6)) * TopAngle * (MathHelper.cos(animationSpeed * f + 1.5f * 6 * offset + initialOffset))
			- currentAngle;
	currentAngle = currentAngle + Segment9.rotateAngleY;
  }

}
