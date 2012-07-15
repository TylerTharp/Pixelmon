package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelMetapod extends ModelBase
{
  //fields
    ModelRenderer HeadTip;
    ModelRenderer HeadTipB;
    ModelRenderer Face;
    ModelRenderer Nose;
    ModelRenderer RearFin;
    ModelRenderer Filler;
    ModelRenderer Back;
    ModelRenderer FinL;
    ModelRenderer OutterFinL;
    ModelRenderer FinR;
    ModelRenderer OutterFinR;
    ModelRenderer Body;
    ModelRenderer LowerBody;
    ModelRenderer TailSeg1;
    ModelRenderer TailSeg2;
    ModelRenderer TailSeg2B;
    ModelRenderer TailTip;
    ModelRenderer TailTipB;
  
  public ModelMetapod()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      HeadTip = new ModelRenderer(this, 0, 5);
      HeadTip.addBox(-1F, -1F, -0.5F, 2, 1, 1);
      HeadTip.setRotationPoint(0F, 15F, -1.3F);
      HeadTip.setTextureSize(64, 32);
      HeadTip.mirror = true;
      setRotation(HeadTip, -0.5235988F, 0F, 0F);
      HeadTipB = new ModelRenderer(this, 0, 5);
      HeadTipB.addBox(-1F, -1F, -0.5F, 2, 1, 1);
      HeadTipB.setRotationPoint(0F, 15.2F, -0.6F);
      HeadTipB.setTextureSize(64, 32);
      HeadTipB.mirror = true;
      setRotation(HeadTipB, -0.0872665F, 0F, 0F);
      Face = new ModelRenderer(this, 0, 20);
      Face.addBox(-1.5F, -4F, -0.5F, 3, 4, 1);
      Face.setRotationPoint(0F, 18.5F, -2.8F);
      Face.setTextureSize(64, 32);
      Face.mirror = true;
      setRotation(Face, -0.3839724F, 0F, 0F);
      Nose = new ModelRenderer(this, 8, 18);
      Nose.addBox(-0.5F, -3F, -1.5F, 1, 6, 1);
      Nose.setRotationPoint(0F, 16.5F, -1F);
      Nose.setTextureSize(64, 32);
      Nose.mirror = true;
      setRotation(Nose, -0.5759587F, 0F, 0F);
      RearFin = new ModelRenderer(this, 12, 6);
      RearFin.addBox(-0.5F, 0F, 0F, 1, 3, 2);
      RearFin.setRotationPoint(0F, 16.5F, -2F);
      RearFin.setTextureSize(64, 32);
      RearFin.mirror = true;
      setRotation(RearFin, 0.2617994F, 0F, 0F);
      Filler = new ModelRenderer(this, 0, 20);
      Filler.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 1);
      Filler.setRotationPoint(0F, 17F, -1.7F);
      Filler.setTextureSize(64, 32);
      Filler.mirror = true;
      setRotation(Filler, -0.0872665F, 0F, 0F);
      Back = new ModelRenderer(this, 0, 15);
      Back.addBox(-1.5F, -2F, -0.53F, 3, 4, 1);
      Back.setRotationPoint(0F, 17F, -0.7F);
      Back.setTextureSize(64, 32);
      Back.mirror = true;
      setRotation(Back, -0.0872665F, 0F, 0F);
      FinL = new ModelRenderer(this, 0, 10);
      FinL.addBox(0F, -1.5F, -1F, 1, 3, 2);
      FinL.setRotationPoint(1F, 18.8F, -1.5F);
      FinL.setTextureSize(64, 32);
      FinL.mirror = true;
      setRotation(FinL, 0.0872665F, 0F, 0F);
      OutterFinL = new ModelRenderer(this, 0, 7);
      OutterFinL.addBox(0F, -1F, -0.5F, 1, 2, 1);
      OutterFinL.setRotationPoint(1.3F, 19F, -1.3F);
      OutterFinL.setTextureSize(64, 32);
      OutterFinL.mirror = true;
      setRotation(OutterFinL, 0.0872665F, 0F, 0F);
      FinR = new ModelRenderer(this, 0, 10);
      FinR.addBox(-1F, -1.5F, -1F, 1, 3, 2);
      FinR.setRotationPoint(-1F, 18.8F, -1.5F);
      FinR.setTextureSize(64, 32);
      FinR.mirror = true;
      setRotation(FinR, 0.0872665F, 0F, 0F);
      OutterFinR = new ModelRenderer(this, 0, 7);
      OutterFinR.addBox(-1F, -1F, -0.5F, 1, 2, 1);
      OutterFinR.setRotationPoint(-1.3F, 19F, -1.3F);
      OutterFinR.setTextureSize(64, 32);
      OutterFinR.mirror = true;
      setRotation(OutterFinR, 0.0872665F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 25);
      Body.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
      Body.setRotationPoint(0F, 18F, -1.8F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0.1745329F, 0F, 0F);
      LowerBody = new ModelRenderer(this, 12, 26);
      LowerBody.addBox(-1.5F, -1.5F, 0F, 3, 3, 3);
      LowerBody.setRotationPoint(0F, 21F, -1.6F);
      LowerBody.setTextureSize(64, 32);
      LowerBody.mirror = true;
      setRotation(LowerBody, -0.6981317F, 0F, 0F);
      TailSeg1 = new ModelRenderer(this, 12, 21);
      TailSeg1.addBox(-1.5F, -1.5F, 0F, 3, 3, 2);
      TailSeg1.setRotationPoint(0F, 22.5F, 0F);
      TailSeg1.setTextureSize(64, 32);
      TailSeg1.mirror = true;
      setRotation(TailSeg1, -0.1745329F, 0F, 0F);
      TailSeg2 = new ModelRenderer(this, 12, 17);
      TailSeg2.addBox(-1F, -1F, 0F, 2, 2, 2);
      TailSeg2.setRotationPoint(0F, 22.5F, 1.8F);
      TailSeg2.setTextureSize(64, 32);
      TailSeg2.mirror = true;
      setRotation(TailSeg2, -0.1396263F, 0F, 0F);
      TailSeg2B = new ModelRenderer(this, 12, 14);
      TailSeg2B.addBox(-1F, -0.5F, 0F, 2, 1, 2);
      TailSeg2B.setRotationPoint(0F, 23.4F, 1.7F);
      TailSeg2B.setTextureSize(64, 32);
      TailSeg2B.mirror = true;
      setRotation(TailSeg2B, 0F, 0F, 0F);
      TailTip = new ModelRenderer(this, 12, 11);
      TailTip.addBox(-0.5F, -0.5F, 0F, 1, 1, 2);
      TailTip.setRotationPoint(0F, 22.4F, 3.4F);
      TailTip.setTextureSize(64, 32);
      TailTip.mirror = true;
      setRotation(TailTip, -0.122173F, 0F, 0F);
      TailTipB = new ModelRenderer(this, 12, 11);
      TailTipB.addBox(-0.5F, -0.5F, 0F, 1, 1, 2);
      TailTipB.setRotationPoint(0F, 23.4F, 3.4F);
      TailTipB.setTextureSize(64, 32);
      TailTipB.mirror = true;
      setRotation(TailTipB, 0.0872665F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    HeadTip.render(f5);
    HeadTipB.render(f5);
    Face.render(f5);
    Nose.render(f5);
    RearFin.render(f5);
    Filler.render(f5);
    Back.render(f5);
    FinL.render(f5);
    OutterFinL.render(f5);
    FinR.render(f5);
    OutterFinR.render(f5);
    Body.render(f5);
    LowerBody.render(f5);
    TailSeg1.render(f5);
    TailSeg2.render(f5);
    TailSeg2B.render(f5);
    TailTip.render(f5);
    TailTipB.render(f5);
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
