package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelOmanyte extends ModelBase
{
  //fields
    ModelRenderer Head;
    ModelRenderer leg1;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
  
  public ModelOmanyte()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Head = new ModelRenderer(this, 9, 0);
      Head.addBox(0F, 0F, 0F, 4, 6, 6);
      Head.setRotationPoint(-2F, 16F, -2F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, -0.418879F, 0F, 0F);
      leg1 = new ModelRenderer(this, 0, 0);
      leg1.addBox(0F, -1F, 0F, 1, 2, 1);
      leg1.setRotationPoint(2F, 23F, -3F);
      leg1.setTextureSize(64, 32);
      leg1.mirror = true;
      setRotation(leg1, -0.2094395F, -0.6108652F, -0.8028515F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, -1F, 0F, 1, 3, 1);
      Shape3.setRotationPoint(-2F, 22F, -4.7F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, -0.7853982F, 0.296706F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0F, 0F, 0F, 1, 2, 1);
      Shape4.setRotationPoint(-2F, 22F, -2.666667F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, -0.8028515F, 1.673038F, 0.0645462F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(0F, -1F, 0F, 1, 3, 1);
      Shape5.setRotationPoint(-2F, 22F, -4F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, -0.6890436F, 0.3907885F, 0.7284942F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(0F, 0F, 0F, 1, 2, 1);
      Shape6.setRotationPoint(1.2F, 22F, -3.5F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, -0.8005794F, -0.9484677F, -0.2823509F);
      Shape10 = new ModelRenderer(this, 0, 0);
      Shape10.addBox(0F, 1.5F, 0F, 1, 3, 1);
      Shape10.setRotationPoint(0.7F, 20F, -3.2F);
      Shape10.setTextureSize(64, 32);
      Shape10.mirror = true;
      setRotation(Shape10, -0.6806784F, -0.296706F, 0F);
      Shape11 = new ModelRenderer(this, 31, 0);
      Shape11.addBox(0F, 0F, -0.5F, 1, 4, 4);
      Shape11.setRotationPoint(2F, 17.5F, -1F);
      Shape11.setTextureSize(64, 32);
      Shape11.mirror = true;
      setRotation(Shape11, -0.418879F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 31, 0);
      Shape12.addBox(0F, 0F, -0.5F, 1, 4, 4);
      Shape12.setRotationPoint(-3F, 17.5F, -1F);
      Shape12.setTextureSize(64, 32);
      Shape12.mirror = true;
      setRotation(Shape12, -0.418879F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 0, 0);
      Shape13.addBox(0F, -1F, 0F, 1, 3, 1);
      Shape13.setRotationPoint(0F, 22F, -4.7F);
      Shape13.setTextureSize(64, 32);
      Shape13.mirror = true;
      setRotation(Shape13, -0.6806784F, -0.1919862F, 0F);
      Shape14 = new ModelRenderer(this, 0, 0);
      Shape14.addBox(0F, -1F, 0F, 1, 3, 1);
      Shape14.setRotationPoint(-1F, 22F, -4.7F);
      Shape14.setTextureSize(64, 32);
      Shape14.mirror = true;
      setRotation(Shape14, -0.7853982F, 0.1570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Head.render(f5);
    leg1.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
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
