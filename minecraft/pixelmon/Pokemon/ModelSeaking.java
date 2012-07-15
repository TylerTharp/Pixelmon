package pixelmon.Pokemon;

import net.minecraft.src.*;

public class ModelSeaking extends ModelBase
{
  //fields
    ModelRenderer Righttailfin;
    ModelRenderer Lefttailfin;
    ModelRenderer topfin;
    ModelRenderer rightfin;
    ModelRenderer leftfin;
    ModelRenderer black1;
    ModelRenderer black2;
    ModelRenderer white1;
    ModelRenderer white2;
    ModelRenderer Horn;
    ModelRenderer Horn1;
    ModelRenderer Horn2;
    ModelRenderer Body;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Body5;
    ModelRenderer Body6;
    ModelRenderer Lefttooth;
    ModelRenderer Lips2;
    ModelRenderer Lips3;
    ModelRenderer righttooth;
    ModelRenderer Lips4;
    ModelRenderer Lips5;
    ModelRenderer Lips6;
  
  public ModelSeaking()
  {
    textureWidth = 256;
    textureHeight = 128;
    
      Righttailfin = new ModelRenderer(this, 104, -10);
      Righttailfin.addBox(0F, -17F, 0F, 0, 32, 12);
      Righttailfin.setRotationPoint(-4F, 7F, 20F);
      Righttailfin.setTextureSize(256, 128);
      Righttailfin.mirror = true;
      setRotation(Righttailfin, -0.2216568F, -0.6691592F, 0F);
      Lefttailfin = new ModelRenderer(this, 104, -10);
      Lefttailfin.addBox(0F, -17F, 0F, 0, 32, 12);
      Lefttailfin.setRotationPoint(4F, 7F, 20F);
      Lefttailfin.setTextureSize(256, 128);
      Lefttailfin.mirror = true;
      setRotation(Lefttailfin, -0.2230717F, 0.669215F, 0F);
      topfin = new ModelRenderer(this, 53, 0);
      topfin.addBox(0F, 0F, 0F, 0, 9, 16);
      topfin.setRotationPoint(0F, -6.5F, 4F);
      topfin.setTextureSize(256, 128);
      topfin.mirror = true;
      setRotation(topfin, 0F, 0F, 0F);
      rightfin = new ModelRenderer(this, 46, 57);
      rightfin.addBox(-9F, 0F, -3F, 9, 0, 8);
      rightfin.setRotationPoint(-5.5F, 10F, 8F);
      rightfin.setTextureSize(256, 128);
      rightfin.mirror = true;
      setRotation(rightfin, 0.7807508F, 0.2602503F, -0.3346075F);
      rightfin.mirror = false;
      leftfin = new ModelRenderer(this, 46, 57);
      leftfin.addBox(0F, 0F, -3F, 9, 0, 8);
      leftfin.setRotationPoint(5.5F, 10F, 8F);
      leftfin.setTextureSize(256, 128);
      leftfin.mirror = true;
      setRotation(leftfin, 0.7807556F, -0.260246F, 0.3346145F);
      black1 = new ModelRenderer(this, 95, 4);
      black1.addBox(0F, 0F, 0F, 1, 3, 3);
      black1.setRotationPoint(-5.7F, 4F, 3F);
      black1.setTextureSize(256, 128);
      black1.mirror = true;
      setRotation(black1, 0F, 0F, 0F);
      black2 = new ModelRenderer(this, 95, 4);
      black2.addBox(0F, 0F, 0F, 1, 3, 3);
      black2.setRotationPoint(4.7F, 4F, 3F);
      black2.setTextureSize(256, 128);
      black2.mirror = true;
      setRotation(black2, 0F, 0F, 0F);
      white1 = new ModelRenderer(this, 93, 12);
      white1.addBox(0F, 0F, 0F, 1, 4, 4);
      white1.setRotationPoint(-5.6F, 3.5F, 2.5F);
      white1.setTextureSize(256, 128);
      white1.mirror = true;
      setRotation(white1, 0F, 0F, 0F);
      white2 = new ModelRenderer(this, 93, 12);
      white2.addBox(0F, 0F, 0F, 1, 4, 4);
      white2.setRotationPoint(4.6F, 3.5F, 2.5F);
      white2.setTextureSize(256, 128);
      white2.mirror = true;
      setRotation(white2, 0F, 0F, 0F);
      Horn = new ModelRenderer(this, 78, 2);
      Horn.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
      Horn.setRotationPoint(0F, -1F, -5F);
      Horn.setTextureSize(256, 128);
      Horn.mirror = true;
      setRotation(Horn, 0.7435722F, 0.5948578F, 0.5576792F);
      Horn1 = new ModelRenderer(this, 78, 2);
      Horn1.addBox(-1.5F, -4F, -1.5F, 3, 4, 3);
      Horn1.setRotationPoint(0F, 3F, 0F);
      Horn1.setTextureSize(256, 128);
      Horn1.mirror = true;
      setRotation(Horn1, 0.7435722F, 0.5948578F, 0.5576792F);
      Horn2 = new ModelRenderer(this, 78, 2);
      Horn2.addBox(-1F, -4F, -1F, 2, 4, 2);
      Horn2.setRotationPoint(0F, 0.5F, -3F);
      Horn2.setTextureSize(256, 128);
      Horn2.mirror = true;
      setRotation(Horn2, 0.7435722F, 0.5948578F, 0.5576792F);
      Body = new ModelRenderer(this, 116, 63);
      Body.addBox(-8F, 0F, -1F, 6, 12, 18);
      Body.setRotationPoint(5F, 2F, 3F);
      Body.setTextureSize(256, 128);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Body1 = new ModelRenderer(this, 0, 98);
      Body1.addBox(-8F, 0F, 0F, 11, 15, 15);
      Body1.setRotationPoint(2.5F, 0.5F, 0.5F);
      Body1.setTextureSize(256, 128);
      Body1.mirror = true;
      setRotation(Body1, 0F, 0F, 0F);
      Body2 = new ModelRenderer(this, 107, 95);
      Body2.addBox(-8F, 0F, -1F, 9, 15, 18);
      Body2.setRotationPoint(3.5F, 0.5F, 0F);
      Body2.setTextureSize(256, 128);
      Body2.mirror = true;
      setRotation(Body2, 0F, 0F, 0F);
      Body3 = new ModelRenderer(this, 163, 94);
      Body3.addBox(-8F, 0F, -1F, 8, 14, 20);
      Body3.setRotationPoint(4F, 1F, -1F);
      Body3.setTextureSize(256, 128);
      Body3.mirror = true;
      setRotation(Body3, 0F, 0F, 0F);
      Body4 = new ModelRenderer(this, 167, 27);
      Body4.addBox(-8F, 0F, 0F, 9, 17, 15);
      Body4.setRotationPoint(3.5F, -0.5F, 0.5F);
      Body4.setTextureSize(256, 128);
      Body4.mirror = true;
      setRotation(Body4, 0F, 0F, 0F);
      Body5 = new ModelRenderer(this, 54, 96);
      Body5.addBox(-8F, 0F, 0F, 10, 16, 16);
      Body5.setRotationPoint(3F, 0F, 0F);
      Body5.setTextureSize(256, 128);
      Body5.mirror = true;
      setRotation(Body5, 0F, 0F, 0F);
      Body6 = new ModelRenderer(this, 167, 61);
      Body6.addBox(-8F, 0F, -1F, 7, 13, 18);
      Body6.setRotationPoint(4.5F, 1.5F, 2F);
      Body6.setTextureSize(256, 128);
      Body6.mirror = true;
      setRotation(Body6, 0F, 0F, 0F);
      Lefttooth = new ModelRenderer(this, 93, 12);
      Lefttooth.addBox(0F, 0F, 0F, 1, 1, 1);
      Lefttooth.setRotationPoint(1.2F, 7.5F, -2.59F);
      Lefttooth.setTextureSize(256, 128);
      Lefttooth.mirror = true;
      setRotation(Lefttooth, 0F, 0F, 0.7853982F);
      Lips2 = new ModelRenderer(this, 183, 26);
      Lips2.addBox(0F, 0F, 0F, 4, 1, 2);
      Lips2.setRotationPoint(-2F, 8.5F, -2.2F);
      Lips2.setTextureSize(256, 128);
      Lips2.mirror = true;
      setRotation(Lips2, 0F, 0F, 0F);
      Lips3 = new ModelRenderer(this, 93, -1);
      Lips3.addBox(0F, 0F, 0F, 1, 3, 2);
      Lips3.setRotationPoint(1.6F, 7.5F, -2.8F);
      Lips3.setTextureSize(256, 128);
      Lips3.mirror = true;
      setRotation(Lips3, 0F, 0F, 0F);
      righttooth = new ModelRenderer(this, 93, 12);
      righttooth.addBox(0F, 0F, 0F, 1, 1, 1);
      righttooth.setRotationPoint(-1.2F, 7.5F, -2.59F);
      righttooth.setTextureSize(256, 128);
      righttooth.mirror = true;
      setRotation(righttooth, 0F, 0F, 0.7853982F);
      Lips4 = new ModelRenderer(this, 91, 0);
      Lips4.addBox(0F, 0F, 0F, 4, 1, 2);
      Lips4.setRotationPoint(-2F, 9.5F, -2.8F);
      Lips4.setTextureSize(256, 128);
      Lips4.mirror = true;
      setRotation(Lips4, 0F, 0F, 0F);
      Lips5 = new ModelRenderer(this, 91, 0);
      Lips5.addBox(0F, 0F, 0F, 4, 1, 2);
      Lips5.setRotationPoint(-2F, 7.5F, -2.8F);
      Lips5.setTextureSize(256, 128);
      Lips5.mirror = true;
      setRotation(Lips5, 0F, 0F, 0F);
      Lips6 = new ModelRenderer(this, 93, -1);
      Lips6.addBox(0F, 0F, 0F, 1, 3, 2);
      Lips6.setRotationPoint(-2.6F, 7.5F, -2.8F);
      Lips6.setTextureSize(256, 128);
      Lips6.mirror = true;
      setRotation(Lips6, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Righttailfin.render(f5);
    Lefttailfin.render(f5);
    topfin.render(f5);
    rightfin.render(f5);
    leftfin.render(f5);
    black1.render(f5);
    black2.render(f5);
    white1.render(f5);
    white2.render(f5);
    Horn.render(f5);
    Horn1.render(f5);
    Horn2.render(f5);
    Body.render(f5);
    Body1.render(f5);
    Body2.render(f5);
    Body3.render(f5);
    Body4.render(f5);
    Body5.render(f5);
    Body6.render(f5);
    Lefttooth.render(f5);
    Lips2.render(f5);
    Lips3.render(f5);
    righttooth.render(f5);
    Lips4.render(f5);
    Lips5.render(f5);
    Lips6.render(f5);
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
