package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelOmastar extends ModelBase
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
    ModelRenderer Shape20;
    ModelRenderer Shape21;
  
  public ModelOmastar()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 6, 9, 10);
      Shape1.setRotationPoint(-3F, 12F, 0F);
      Shape1.setTextureSize(128, 64);
      Shape1.mirror = true;
      setRotation(Shape1, -0.3665191F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 32, 0);
      Shape2.addBox(0F, 0F, 0F, 6, 3, 7);
      Shape2.setRotationPoint(-3F, 20F, -3.2F);
      Shape2.setTextureSize(128, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, 0F, 0F, 1, 5, 1);
      Shape3.setRotationPoint(-2F, 22F, -2F);
      Shape3.setTextureSize(128, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 1.003822F, 0F, 1.375609F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0F, -0.5F, 0F, 1, 5, 1);
      Shape4.setRotationPoint(3.2F, 22.5F, -1.5F);
      Shape4.setTextureSize(128, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0.4461433F, 0F, -1.78457F);
      Shape5 = new ModelRenderer(this, 58, 0);
      Shape5.addBox(0F, 0F, 0F, 2, 1, 3);
      Shape5.setRotationPoint(1F, 23F, -3.2F);
      Shape5.setTextureSize(128, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 58, 0);
      Shape6.addBox(0F, 0F, 0F, 2, 1, 3);
      Shape6.setRotationPoint(-3F, 23F, -3.2F);
      Shape6.setTextureSize(128, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(0F, 0F, 0F, 1, 5, 1);
      Shape7.setRotationPoint(-2F, 22F, -2F);
      Shape7.setTextureSize(128, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0.2602503F, 0F, 2.342252F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(0F, -0.5F, 0F, 1, 5, 1);
      Shape8.setRotationPoint(3.2F, 22.5F, -1.5F);
      Shape8.setTextureSize(128, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0.2602503F, 0F, -2.342249F);
      Shape9 = new ModelRenderer(this, 0, 0);
      Shape9.addBox(0F, 0F, 0F, 1, 5, 1);
      Shape9.setRotationPoint(4F, 17.3F, -3F);
      Shape9.setTextureSize(128, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0.4461433F);
      Shape10 = new ModelRenderer(this, 0, 0);
      Shape10.addBox(0F, 0F, 0F, 1, 5, 1);
      Shape10.setRotationPoint(-4.5F, 17.5F, -3F);
      Shape10.setTextureSize(128, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, -0.3346075F);
      Shape11 = new ModelRenderer(this, 0, 0);
      Shape11.addBox(0F, -0.5F, 0F, 1, 5, 1);
      Shape11.setRotationPoint(3.2F, 22.5F, -1.5F);
      Shape11.setTextureSize(128, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0.8179294F, 0F, -1.152534F);
      Shape12 = new ModelRenderer(this, 0, 0);
      Shape12.addBox(0F, 0F, 0F, 1, 5, 1);
      Shape12.setRotationPoint(-2F, 22F, -2F);
      Shape12.setTextureSize(128, 64);
      Shape12.mirror = true;
      setRotation(Shape12, 0.5948578F, 0F, 1.747395F);
      Shape13 = new ModelRenderer(this, 68, 0);
      Shape13.addBox(-1F, 1F, 1F, 1, 7, 8);
      Shape13.setRotationPoint(-3F, 12F, 0F);
      Shape13.setTextureSize(128, 64);
      Shape13.mirror = true;
      setRotation(Shape13, -0.3665191F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 68, 0);
      Shape14.addBox(-1F, 1F, 1F, 1, 7, 8);
      Shape14.setRotationPoint(4F, 12F, 0F);
      Shape14.setTextureSize(128, 64);
      Shape14.mirror = true;
      setRotation(Shape14, -0.3665191F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 0, 19);
      Shape15.addBox(0F, 0F, 0F, 2, 1, 1);
      Shape15.setRotationPoint(-1F, 22F, -3.9F);
      Shape15.setTextureSize(128, 64);
      Shape15.mirror = true;
      setRotation(Shape15, 0.7853982F, 0F, 0F);
      Shape16 = new ModelRenderer(this, 0, 19);
      Shape16.addBox(0F, 0F, 0F, 1, 2, 2);
      Shape16.setRotationPoint(-0.5F, 17F, -3.5F);
      Shape16.setTextureSize(128, 64);
      Shape16.mirror = true;
      setRotation(Shape16, 0.4089647F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 0, 19);
      Shape17.addBox(0F, 0F, 0F, 1, 2, 2);
      Shape17.setRotationPoint(-0.5F, 11.8F, 3F);
      Shape17.setTextureSize(128, 64);
      Shape17.mirror = true;
      setRotation(Shape17, -1.152537F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 0, 19);
      Shape18.addBox(0F, 0F, 0F, 1, 2, 2);
      Shape18.setRotationPoint(-0.5F, 13.8F, 8F);
      Shape18.setTextureSize(128, 64);
      Shape18.mirror = true;
      setRotation(Shape18, -1.152537F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 0, 19);
      Shape19.addBox(0F, 0F, 0F, 1, 2, 2);
      Shape19.setRotationPoint(-0.5F, 16.8F, 9F);
      Shape19.setTextureSize(128, 64);
      Shape19.mirror = true;
      setRotation(Shape19, -1.264073F, 0F, 0F);
      Shape20 = new ModelRenderer(this, 0, 19);
      Shape20.addBox(0F, 0F, 0F, 1, 2, 2);
      Shape20.setRotationPoint(-0.5F, 20.8F, 7.7F);
      Shape20.setTextureSize(128, 64);
      Shape20.mirror = true;
      setRotation(Shape20, -1.264073F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 0, 19);
      Shape21.addBox(0F, 0F, 0F, 1, 2, 2);
      Shape21.setRotationPoint(-0.5F, 13F, -1.7F);
      Shape21.setTextureSize(128, 64);
      Shape21.mirror = true;
      setRotation(Shape21, 0.4089647F, 0F, 0F);
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
    Shape20.render(f5);
    Shape21.render(f5);
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
