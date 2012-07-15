package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelCharmander extends ModelBase
{
  //fields
    ModelRenderer Body;
    ModelRenderer LegLeft;
    ModelRenderer LegRight;
    ModelRenderer Head;
    ModelRenderer Snout;
    ModelRenderer Neck;
    ModelRenderer ArmRight;
    ModelRenderer ArmLeft;
    ModelRenderer TailBase;
    ModelRenderer TailEnd;
    ModelRenderer Flame;
  
  public ModelCharmander()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Body = new ModelRenderer(this, 48, 0);
      Body.addBox(-2F, 0F, -3F, 4, 8, 4);
      Body.setRotationPoint(0F, 13F, 1F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0.1745329F, 0F, 0F);
      LegLeft = new ModelRenderer(this, 0, 25);
      LegLeft.addBox(0F, -0.5F, -1F, 2, 5, 2);
      LegLeft.setRotationPoint(1F, 19.5F, 0F);
      LegLeft.setTextureSize(64, 32);
      LegLeft.mirror = true;
      setRotation(LegLeft, 0F, 0F, 0F);
      LegRight = new ModelRenderer(this, 0, 25);
      LegRight.addBox(-2F, -0.5F, -1F, 2, 5, 2);
      LegRight.setRotationPoint(-1F, 19.5F, 0F);
      LegRight.setTextureSize(64, 32);
      LegRight.mirror = true;
      setRotation(LegRight, 0F, 0F, 0F);
      LegRight.mirror = false;
      Head = new ModelRenderer(this, 38, 24);
      Head.addBox(-2F, -5F, -3F, 4, 4, 4);
      Head.setRotationPoint(0F, 13.5F, 1F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Snout = new ModelRenderer(this, 27, 29);
      Snout.addBox(-2F, -3F, -4F, 4, 2, 1);
      Snout.setRotationPoint(0F, 13.5F, 1F);
      Snout.setTextureSize(64, 32);
      Snout.mirror = true;
      setRotation(Snout, 0F, 0F, 0F);
      Neck = new ModelRenderer(this, 20, 0);
      Neck.addBox(-1.5F, -0.5F, -2F, 3, 1, 3);
      Neck.setRotationPoint(0F, 13F, 1F);
      Neck.setTextureSize(64, 32);
      Neck.mirror = true;
      setRotation(Neck, 0F, 0F, 0F);
      ArmRight = new ModelRenderer(this, 12, 26);
      ArmRight.addBox(-2F, -1F, -1F, 2, 4, 2);
      ArmRight.setRotationPoint(-2F, 15F, 0F);
      ArmRight.setTextureSize(64, 32);
      ArmRight.mirror = true;
      setRotation(ArmRight, 0F, 0F, 0F);
      ArmRight.mirror = false;
      ArmLeft = new ModelRenderer(this, 12, 26);
      ArmLeft.addBox(0F, -1F, -1F, 2, 4, 2);
      ArmLeft.setRotationPoint(2F, 15F, 0F);
      ArmLeft.setTextureSize(64, 32);
      ArmLeft.mirror = true;
      setRotation(ArmLeft, 0F, 0F, 0F);
      TailBase = new ModelRenderer(this, 10, 0);
      TailBase.addBox(-1F, 0F, -1F, 2, 3, 2);
      TailBase.setRotationPoint(0F, 19.7F, 3F);
      TailBase.setTextureSize(64, 32);
      TailBase.mirror = true;
      setRotation(TailBase, 1.58825F, 0F, 0F);
      TailEnd = new ModelRenderer(this, 0, 0);
      TailEnd.addBox(-1F, 0F, -1F, 2, 3, 2);
      TailEnd.setRotationPoint(0F, 19.7F, 5.5F);
      TailEnd.setTextureSize(64, 32);
      TailEnd.mirror = true;
      setRotation(TailEnd, 2.146755F, 0F, 0F);
      Flame = new ModelRenderer(this, 0, 5);
      Flame.addBox(0F, -5F, 0F, 0, 5, 4);
      Flame.setRotationPoint(0F, 19.5F, 6.5F);
      Flame.setTextureSize(64, 32);
      Flame.mirror = true;
      setRotation(Flame, 0.0523599F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Body.render(f5);
    LegLeft.render(f5);
    LegRight.render(f5);
    Head.render(f5);
    Snout.render(f5);
    Neck.render(f5);
    ArmRight.render(f5);
    ArmLeft.render(f5);
    TailBase.render(f5);
    TailEnd.render(f5);
    Flame.render(f5);
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
    Head.rotateAngleY = f3 / (180F / (float)Math.PI);
    Head.rotateAngleX = f4 / (180F / (float)Math.PI);
    Snout.rotateAngleY = Head.rotateAngleY;
    Snout.rotateAngleX = Head.rotateAngleX;
    ArmRight.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
    ArmLeft.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
    ArmRight.rotateAngleZ = 0.0F;
    ArmLeft.rotateAngleZ = 0.0F;
    LegRight.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    LegLeft.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    LegRight.rotateAngleY = 0.0F;
    LegLeft.rotateAngleY = 0.0F;
  }

}
