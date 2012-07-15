package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelPidgey extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
  
  public ModelPidgey()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Shape1 = new ModelRenderer(this, 10, 25);
      Shape1.addBox(0F, 0F, 0F, 3, 3, 4);
      Shape1.setRotationPoint(-1.5F, 19F, -1F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, -0.2617994F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 24, 0);
      Shape2.addBox(0F, 0F, 0F, 1, 1, 1);
      Shape2.setRotationPoint(0.4F, 22F, 0F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 28, 0);
      Shape3.addBox(-1F, 0F, 0F, 1, 1, 1);
      Shape3.setRotationPoint(-0.4F, 22F, 0F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 21);
      Shape4.addBox(0F, 0F, 0F, 1, 2, 3);
      Shape4.setRotationPoint(1F, 19.2F, -0.9F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, -0.2617994F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 26);
      Shape5.addBox(0F, 0F, -3F, 3, 4, 2);
      Shape5.setRotationPoint(-1.5F, 18F, 1F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 8, 21);
      Shape6.addBox(0F, 0F, -2F, 2, 2, 2);
      Shape6.setRotationPoint(-1F, 17F, -0.4F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0.2792527F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 14);
      Shape7.addBox(0F, 0F, 0F, 1, 2, 3);
      Shape7.setRotationPoint(-2F, 19.2F, -0.9F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, -0.2617994F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 8, 0);
      Shape8.addBox(0F, 0F, 0F, 1, 1, 1);
      Shape8.setRotationPoint(-0.5F, 18.3F, -1.5F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, -2.156359F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 0, 5);
      Shape9.addBox(0F, 0F, 0F, 1, 1, 3);
      Shape9.setRotationPoint(-1F, 17F, -3F);
      Shape9.setTextureSize(64, 32);
      Shape9.mirror = true;
      setRotation(Shape9, 0.4476924F, -0.1745329F, 0F);
      Shape10 = new ModelRenderer(this, 0, 11);
      Shape10.addBox(0F, 0F, 0F, 3, 1, 2);
      Shape10.setRotationPoint(-1.5F, 20F, 2F);
      Shape10.setTextureSize(64, 32);
      Shape10.mirror = true;
      setRotation(Shape10, 0.1745329F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 0, 9);
      Shape11.addBox(0F, 0F, 0F, 1, 1, 1);
      Shape11.setRotationPoint(-0.5F, 19.7F, 3.9F);
      Shape11.setTextureSize(64, 32);
      Shape11.mirror = true;
      setRotation(Shape11, 0.2617994F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 0, 19);
      Shape12.addBox(0F, 0F, 0F, 1, 1, 1);
      Shape12.setRotationPoint(1F, 20F, 2F);
      Shape12.setTextureSize(64, 32);
      Shape12.mirror = true;
      setRotation(Shape12, -0.2617994F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 0, 1);
      Shape13.addBox(-1F, 0F, 0F, 1, 1, 3);
      Shape13.setRotationPoint(1F, 17F, -3F);
      Shape13.setTextureSize(64, 32);
      Shape13.mirror = true;
      setRotation(Shape13, 0.4363323F, 0.1745329F, 0F);
      Shape14 = new ModelRenderer(this, 8, 5);
      Shape14.addBox(0F, 0F, 0F, 1, 1, 3);
      Shape14.setRotationPoint(-0.5F, 17F, -3F);
      Shape14.setTextureSize(64, 32);
      Shape14.mirror = true;
      setRotation(Shape14, 0.4363323F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 18, 0);
      Shape15.addBox(0F, 0F, -2F, 1, 1, 2);
      Shape15.setRotationPoint(0.4F, 22.8F, 1F);
      Shape15.setTextureSize(64, 32);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 12, 0);
      Shape16.addBox(0F, 0F, -2F, 1, 1, 2);
      Shape16.setRotationPoint(-1.4F, 22.8F, 1F);
      Shape16.setTextureSize(64, 32);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 4, 19);
      Shape17.addBox(-1F, 0F, 0F, 1, 1, 1);
      Shape17.setRotationPoint(-1F, 20F, 2F);
      Shape17.setTextureSize(64, 32);
      Shape17.mirror = true;
      setRotation(Shape17, -0.2617994F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 16, 4);
      Shape18.addBox(0F, 0F, 0F, 1, 1, 1);
      Shape18.setRotationPoint(0F, 17F, -2F);
      Shape18.setTextureSize(64, 32);
      Shape18.mirror = true;
      setRotation(Shape18, -0.7853982F, -0.6981317F, 0F);
      Shape19 = new ModelRenderer(this, 20, 4);
      Shape19.addBox(-1F, 0.06666667F, 0F, 1, 1, 1);
      Shape19.setRotationPoint(0F, 17F, -2F);
      Shape19.setTextureSize(64, 32);
      Shape19.mirror = true;
      setRotation(Shape19, -0.7853982F, 0.6981317F, 0F);
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
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
    Shape16.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    Shape19.render(f5);
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
