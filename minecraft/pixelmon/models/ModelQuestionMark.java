package pixelmon.models;

import net.minecraft.src.*;

public class ModelQuestionMark extends ModelBase
{
    ModelRenderer MiddleDot;
    ModelRenderer MiddleBase;
    ModelRenderer MiddleOuter;
    ModelRenderer MiddleTop;
    ModelRenderer MiddleHook;
  
  public ModelQuestionMark()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      MiddleDot = new ModelRenderer(this, 0, 0);
      MiddleDot.addBox(-2F, -2F, -2F, 4, 4, 4);
      MiddleDot.setRotationPoint(0F, 20F, 0F);
      MiddleDot.setTextureSize(64, 32);
      MiddleDot.mirror = true;
      setRotation(MiddleDot, 0F, 0F, 0F);
      MiddleBase = new ModelRenderer(this, 0, 0);
      MiddleBase.addBox(-2F, -3F, -2F, 4, 6, 4);
      MiddleBase.setRotationPoint(0F, 14F, 0F);
      MiddleBase.setTextureSize(64, 32);
      MiddleBase.mirror = true;
      setRotation(MiddleBase, 0F, 0F, 0F);
      MiddleOuter = new ModelRenderer(this, 0, 0);
      MiddleOuter.addBox(-2F, -4F, -2F, 4, 8, 4);
      MiddleOuter.setRotationPoint(4F, 7F, 0F);
      MiddleOuter.setTextureSize(64, 32);
      MiddleOuter.mirror = true;
      setRotation(MiddleOuter, 0F, 0F, 0F);
      MiddleTop = new ModelRenderer(this, 0, 0);
      MiddleTop.addBox(-2F, -2F, -2F, 4, 4, 4);
      MiddleTop.setRotationPoint(0F, 1F, 0F);
      MiddleTop.setTextureSize(64, 32);
      MiddleTop.mirror = true;
      setRotation(MiddleTop, 0F, 0F, 0F);
      MiddleHook = new ModelRenderer(this, 0, 0);
      MiddleHook.addBox(-2F, -2F, -2F, 4, 4, 4);
      MiddleHook.setRotationPoint(-4F, 5F, 0F);
      MiddleHook.setTextureSize(64, 32);
      MiddleHook.mirror = true;
      setRotation(MiddleHook, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    MiddleDot.render(f5);
    MiddleBase.render(f5);
    MiddleOuter.render(f5);
    MiddleTop.render(f5);
    MiddleHook.render(f5);
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
  }

}