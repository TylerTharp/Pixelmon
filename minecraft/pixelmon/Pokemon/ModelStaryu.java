package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;


public class ModelStaryu extends ModelBase
{
  //fields
    ModelRenderer Small1;
    ModelRenderer Mid1;
    ModelRenderer Big1;
    ModelRenderer Small2;
    ModelRenderer Mid2;
    ModelRenderer Big2;
    ModelRenderer Small3;
    ModelRenderer Mid3;
    ModelRenderer Big3;
    ModelRenderer SMall4;
    ModelRenderer Mid4;
    ModelRenderer Big4;
    ModelRenderer Small5;
    ModelRenderer Mid5;
    ModelRenderer Big5;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Body;
    ModelRenderer Outer_Body;
    ModelRenderer Gem_Holder;
    ModelRenderer Gem;
    ModelRenderer Gem2;
  
  public ModelStaryu()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Small1 = new ModelRenderer(this, 0, 0);
      Small1.addBox(-1F, -8F, -1F, 2, 2, 2);
      Small1.setRotationPoint(4F, 10.6F, 0F);
      Small1.setTextureSize(64, 32);
      Small1.mirror = true;
      setRotation(Small1, 0F, 0.7854F, 0.8179294F);
      Mid1 = new ModelRenderer(this, 0, 0);
      Mid1.addBox(-1.5F, -6F, -1.5F, 3, 3, 3);
      Mid1.setRotationPoint(4F, 10.6F, 0F);
      Mid1.setTextureSize(64, 32);
      Mid1.mirror = true;
      setRotation(Mid1, 0F, 0.7854F, 0.8179294F);
      Big1 = new ModelRenderer(this, 0, 0);
      Big1.addBox(-2F, -3F, -2F, 4, 3, 4);
      Big1.setRotationPoint(4F, 10.6F, 0F);
      Big1.setTextureSize(64, 32);
      Big1.mirror = true;
      setRotation(Big1, 0F, 0.7854F, 0.8179294F);
      Small2 = new ModelRenderer(this, 0, 0);
      Small2.addBox(-1F, -8F, -1F, 2, 2, 2);
      Small2.setRotationPoint(-2F, 10.6F, 0F);
      Small2.setTextureSize(64, 32);
      Small2.mirror = true;
      setRotation(Small2, 0F, 0.7854F, -0.8179294F);
      Mid2 = new ModelRenderer(this, 0, 0);
      Mid2.addBox(-1.5F, -6F, -1.5F, 3, 3, 3);
      Mid2.setRotationPoint(-2F, 10.6F, 0F);
      Mid2.setTextureSize(64, 32);
      Mid2.mirror = true;
      setRotation(Mid2, 0F, 0.7854F, -0.8179294F);
      Big2 = new ModelRenderer(this, 0, 0);
      Big2.addBox(-2F, -3F, -2F, 4, 3, 4);
      Big2.setRotationPoint(-2F, 10.6F, 0F);
      Big2.setTextureSize(64, 32);
      Big2.mirror = true;
      setRotation(Big2, 0F, 0.7854F, -0.8179294F);
      Small3 = new ModelRenderer(this, 0, 0);
      Small3.addBox(-1F, -8F, -1F, 2, 2, 2);
      Small3.setRotationPoint(-1F, 13.6F, 0F);
      Small3.setTextureSize(64, 32);
      Small3.mirror = true;
      setRotation(Small3, 0F, 0.6738623F, -2.45619F);
      Mid3 = new ModelRenderer(this, 0, 0);
      Mid3.addBox(-1.5F, -6F, -1.5F, 3, 3, 3);
      Mid3.setRotationPoint(-1F, 13.6F, 0F);
      Mid3.setTextureSize(64, 32);
      Mid3.mirror = true;
      setRotation(Mid3, 0F, 0.6738623F, -2.45619F);
      Big3 = new ModelRenderer(this, 0, 0);
      Big3.addBox(-2F, -3F, -2F, 4, 3, 4);
      Big3.setRotationPoint(-1F, 13.6F, 0F);
      Big3.setTextureSize(64, 32);
      Big3.mirror = true;
      setRotation(Big3, 0F, 0.6738623F, -2.45619F);
      SMall4 = new ModelRenderer(this, 0, 0);
      SMall4.addBox(-1F, -8F, -1F, 2, 2, 2);
      SMall4.setRotationPoint(1F, 7.6F, 0F);
      SMall4.setTextureSize(64, 32);
      SMall4.mirror = true;
      setRotation(SMall4, 0F, 0.7853982F, 0F);
      Mid4 = new ModelRenderer(this, 0, 0);
      Mid4.addBox(-1.5F, -6F, -1.5F, 3, 3, 3);
      Mid4.setRotationPoint(1F, 7.6F, 0F);
      Mid4.setTextureSize(64, 32);
      Mid4.mirror = true;
      setRotation(Mid4, 0F, 0.7853982F, 0F);
      Big4 = new ModelRenderer(this, 0, 0);
      Big4.addBox(-2F, -3F, -2F, 4, 3, 4);
      Big4.setRotationPoint(1F, 7.6F, 0F);
      Big4.setTextureSize(64, 32);
      Big4.mirror = true;
      setRotation(Big4, 0F, 0.7853982F, 0F);
      Small5 = new ModelRenderer(this, 0, 0);
      Small5.addBox(-1F, -8F, -1F, 2, 2, 2);
      Small5.setRotationPoint(3F, 13.6F, 0F);
      Small5.setTextureSize(64, 32);
      Small5.mirror = true;
      setRotation(Small5, 0F, 0.7854F, 2.45619F);
      Mid5 = new ModelRenderer(this, 0, 0);
      Mid5.addBox(-1.5F, -6F, -1.5F, 3, 3, 3);
      Mid5.setRotationPoint(3F, 13.6F, 0F);
      Mid5.setTextureSize(64, 32);
      Mid5.mirror = true;
      setRotation(Mid5, 0F, 0.7854F, 2.45619F);
      Big5 = new ModelRenderer(this, 0, 0);
      Big5.addBox(-2F, -3F, -2F, 4, 3, 4);
      Big5.setRotationPoint(3F, 13.6F, 0F);
      Big5.setTextureSize(64, 32);
      Big5.mirror = true;
      setRotation(Big5, 0F, 0.7854F, 2.45619F);
      Shape1 = new ModelRenderer(this, 52, 29);
      Shape1.addBox(-4.2F, -0.5F, -0.5F, 4, 1, 2);
      Shape1.setRotationPoint(1F, 10.5F, -3F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, -2.356194F);
      Shape2 = new ModelRenderer(this, 52, 29);
      Shape2.addBox(-4.2F, -0.5F, -0.5F, 4, 1, 2);
      Shape2.setRotationPoint(1F, 10.5F, -3F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 2.356194F);
      Shape3 = new ModelRenderer(this, 52, 29);
      Shape3.addBox(-4F, -0.5F, -0.5F, 4, 1, 2);
      Shape3.setRotationPoint(1F, 10.5F, -3F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 3.141593F);
      Shape4 = new ModelRenderer(this, 52, 29);
      Shape4.addBox(-4.2F, -0.5F, -0.5F, 4, 1, 2);
      Shape4.setRotationPoint(1F, 10.5F, -3F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0.7853982F);
      Shape5 = new ModelRenderer(this, 52, 29);
      Shape5.addBox(-4.2F, -0.5F, -0.5F, 4, 1, 2);
      Shape5.setRotationPoint(1F, 10.5F, -3F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, -0.7853982F);
      Shape6 = new ModelRenderer(this, 52, 29);
      Shape6.addBox(-4F, -0.5F, -0.5F, 4, 1, 2);
      Shape6.setRotationPoint(1F, 10.5F, -3F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 29, 11);
      Body.addBox(-2F, -2F, -2F, 6, 6, 6);
      Body.setRotationPoint(0F, 9.533334F, -1F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Outer_Body = new ModelRenderer(this, 0, 0);
      Outer_Body.addBox(0F, 0F, 0F, 8, 8, 4);
      Outer_Body.setRotationPoint(-3F, 7F, -2F);
      Outer_Body.setTextureSize(64, 32);
      Outer_Body.mirror = true;
      setRotation(Outer_Body, 0F, 0F, 0F);
      Gem_Holder = new ModelRenderer(this, 52, 26);
      Gem_Holder.addBox(-0.5F, -1F, -3F, 4, 4, 2);
      Gem_Holder.setRotationPoint(-0.5F, 9.5F, -0.5F);
      Gem_Holder.setTextureSize(64, 32);
      Gem_Holder.mirror = true;
      setRotation(Gem_Holder, 0F, 0F, 0F);
      Gem = new ModelRenderer(this, 54, 0);
      Gem.addBox(-1F, -1.5F, -3F, 3, 3, 3);
      Gem.setRotationPoint(0.5F, 10.5F, -0.8F);
      Gem.setTextureSize(64, 32);
      Gem.mirror = true;
      setRotation(Gem, 0F, 0F, 0F);
      Gem2 = new ModelRenderer(this, 48, 0);
      Gem2.addBox(-1.5F, -2F, -3F, 2, 2, 3);
      Gem2.setRotationPoint(1.5F, 11.5F, -1F);
      Gem2.setTextureSize(64, 32);
      Gem2.mirror = true;
      setRotation(Gem2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Small1.render(f5);
    Mid1.render(f5);
    Big1.render(f5);
    Small2.render(f5);
    Mid2.render(f5);
    Big2.render(f5);
    Small3.render(f5);
    Mid3.render(f5);
    Big3.render(f5);
    SMall4.render(f5);
    Mid4.render(f5);
    Big4.render(f5);
    Small5.render(f5);
    Mid5.render(f5);
    Big5.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Body.render(f5);
    Outer_Body.render(f5);
    Gem_Holder.render(f5);
    Gem.render(f5);
    Gem2.render(f5);
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
