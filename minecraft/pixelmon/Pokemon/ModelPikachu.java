package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelPikachu extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg4;
  
  public ModelPikachu()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Shape1 = new ModelRenderer(this, 30, 0);
      Shape1.addBox(0F, 0F, 0F, 0, 1, 3);
      Shape1.setRotationPoint(0F, 19F, 5F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 36, 0);
      Shape2.addBox(0F, 0F, 0F, 0, 1, 3);
      Shape2.setRotationPoint(0F, 18F, 7F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 42, 0);
      Shape3.addBox(0F, 0F, 8F, 0, 2, 6);
      Shape3.setRotationPoint(0F, 16F, 0F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 1);
      Shape4.addBox(0F, 0F, 0F, 5, 1, 0);
      Shape4.setRotationPoint(3.8F, 8F, 0F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, -0.2617994F);
      Shape5 = new ModelRenderer(this, 0, 2);
      Shape5.addBox(-5F, 0F, 0F, 5, 1, 0);
      Shape5.setRotationPoint(-4F, 8F, 0F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0.2617994F);
      Shape6 = new ModelRenderer(this, 36, 22);
      Shape6.addBox(0F, 0F, 0F, 2, 4, 2);
      Shape6.setRotationPoint(2F, 15F, -4F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, -0.2617994F, 0F, 0.2617994F);
      Shape7 = new ModelRenderer(this, 36, 16);
      Shape7.addBox(-2F, 0F, 0F, 2, 4, 2);
      Shape7.setRotationPoint(-2F, 15F, -4F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, -0.2617994F, 0F, -0.2617994F);
      head = new ModelRenderer(this, 0, 3);
      head.addBox(-4F, -4F, -7F, 8, 6, 7);
      head.setRotationPoint(0F, 12F, 3F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 1, 16);
      body.addBox(-5F, -10F, -7F, 9, 8, 8);
      body.setRotationPoint(0.5F, 24F, 3F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      leg1 = new ModelRenderer(this, 30, 10);
      leg1.addBox(-2F, 0F, -4F, 2, 2, 4);
      leg1.setRotationPoint(-1F, 22F, 2F);
      leg1.setTextureSize(64, 32);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0.2617994F, 0F);
      leg2 = new ModelRenderer(this, 23, 0);
      leg2.addBox(-2F, 0F, -2F, 0, 1, 2);
      leg2.setRotationPoint(2F, 20F, 6F);
      leg2.setTextureSize(64, 32);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 30, 4);
      leg4.addBox(0F, 0F, -4F, 2, 2, 4);
      leg4.setRotationPoint(1F, 22F, 2F);
      leg4.setTextureSize(64, 32);
      leg4.mirror = true;
      setRotation(leg4, 0F, -0.2617994F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    head.render(f5);
    body.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg4.render(f5);
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
