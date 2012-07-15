package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelCaterpie extends ModelBase
{
  //fields
    ModelRenderer EyeR;
    ModelRenderer EyeL;
    ModelRenderer Mouth;
    ModelRenderer AntR;
    ModelRenderer AntL;
    ModelRenderer AntBase;
    ModelRenderer Head;
    ModelRenderer HeadWide;
    ModelRenderer HeadTall;
    ModelRenderer HeadThick;
    ModelRenderer UpperLegR;
    ModelRenderer BodyWide;
    ModelRenderer BodyTall;
    ModelRenderer Body;
    ModelRenderer BodyThick;
    ModelRenderer BodySeg2;
    ModelRenderer BodySeg2Tall;
    ModelRenderer BodySeg2WIde;
    ModelRenderer BodySeg3;
    ModelRenderer BodySeg4;
    ModelRenderer BodyUpperNarrow;
    ModelRenderer BodyLowerNarrow;
    ModelRenderer BodySeg5;
    ModelRenderer LowerLegR;
    ModelRenderer UpperLegL;
    ModelRenderer LowerLegL;
  
  public ModelCaterpie()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      EyeR = new ModelRenderer(this, 0, 2);
      EyeR.addBox(-1F, -0.5F, -0.5F, 0, 1, 1);
      EyeR.setRotationPoint(-1.1F, 19.7F, -4.5F);
      EyeR.setTextureSize(64, 32);
      EyeR.mirror = true;
      setRotation(EyeR, 0F, 0F, 0F);
      EyeL = new ModelRenderer(this, 0, 2);
      EyeL.addBox(1F, -0.5F, -0.5F, 0, 1, 1);
      EyeL.setRotationPoint(1.1F, 19.7F, -4.5F);
      EyeL.setTextureSize(64, 32);
      EyeL.mirror = true;
      setRotation(EyeL, 0F, 0F, 0F);
      Mouth = new ModelRenderer(this, 0, 8);
      Mouth.addBox(-0.5F, -0.5F, -1F, 1, 1, 1);
      Mouth.setRotationPoint(0F, 21F, -5.3F);
      Mouth.setTextureSize(64, 32);
      Mouth.mirror = true;
      setRotation(Mouth, 0.4363323F, 0F, 0F);
      AntR = new ModelRenderer(this, 7, 13);
      AntR.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
      AntR.setRotationPoint(-0.2F, 18.6F, -5.6F);
      AntR.setTextureSize(64, 32);
      AntR.mirror = true;
      setRotation(AntR, -0.2617994F, 0.1745329F, -0.7853982F);
      AntL = new ModelRenderer(this, 7, 13);
      AntL.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
      AntL.setRotationPoint(0.2F, 18.6F, -5.6F);
      AntL.setTextureSize(64, 32);
      AntL.mirror = true;
      setRotation(AntL, -0.2617994F, -0.1745329F, 0.7853982F);
      AntBase = new ModelRenderer(this, 3, 2);
      AntBase.addBox(0F, -1F, -0.5F, 1, 1, 1);
      AntBase.setRotationPoint(0F, 19.4F, -5.4F);
      AntBase.setTextureSize(64, 32);
      AntBase.mirror = true;
      setRotation(AntBase, 0.2792527F, 0.1745329F, -0.7679449F);
      Head = new ModelRenderer(this, 0, 26);
      Head.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
      Head.setRotationPoint(0F, 20F, -4F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      HeadWide = new ModelRenderer(this, 0, 22);
      HeadWide.addBox(-2F, -1F, -1F, 4, 2, 2);
      HeadWide.setRotationPoint(0F, 20F, -4F);
      HeadWide.setTextureSize(64, 32);
      HeadWide.mirror = true;
      setRotation(HeadWide, 0F, 0F, 0F);
      HeadTall = new ModelRenderer(this, 0, 16);
      HeadTall.addBox(-1F, -2F, -1F, 2, 4, 2);
      HeadTall.setRotationPoint(0F, 20F, -4F);
      HeadTall.setTextureSize(64, 32);
      HeadTall.mirror = true;
      setRotation(HeadTall, 0F, 0F, 0F);
      HeadThick = new ModelRenderer(this, 12, 22);
      HeadThick.addBox(-1F, -1F, -2F, 2, 2, 4);
      HeadThick.setRotationPoint(0F, 20F, -4F);
      HeadThick.setTextureSize(64, 32);
      HeadThick.mirror = true;
      setRotation(HeadThick, 0F, 0F, 0F);
      UpperLegR = new ModelRenderer(this, 0, 8);
      UpperLegR.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
      UpperLegR.setRotationPoint(-1F, 22F, -3F);
      UpperLegR.setTextureSize(64, 32);
      UpperLegR.mirror = true;
      setRotation(UpperLegR, -1.134464F, 0.5235988F, 0.1745329F);
      BodyWide = new ModelRenderer(this, 12, 28);
      BodyWide.addBox(-2F, -1F, -1F, 4, 2, 2);
      BodyWide.setRotationPoint(0F, 21.5F, -2F);
      BodyWide.setTextureSize(64, 32);
      BodyWide.mirror = true;
      setRotation(BodyWide, 0F, 0F, 0F);
      BodyTall = new ModelRenderer(this, 0, 16);
      BodyTall.addBox(-1F, -2F, -1F, 2, 4, 2);
      BodyTall.setRotationPoint(0F, 21.5F, -2F);
      BodyTall.setTextureSize(64, 32);
      BodyTall.mirror = true;
      setRotation(BodyTall, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 26);
      Body.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
      Body.setRotationPoint(0F, 21.5F, -2F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      BodyThick = new ModelRenderer(this, 12, 16);
      BodyThick.addBox(-1F, -1F, -2F, 2, 2, 4);
      BodyThick.setRotationPoint(0F, 21.5F, -2F);
      BodyThick.setTextureSize(64, 32);
      BodyThick.mirror = true;
      setRotation(BodyThick, 0F, 0F, 0F);
      BodySeg2 = new ModelRenderer(this, 0, 4);
      BodySeg2.addBox(-1F, -1F, -1F, 2, 2, 2);
      BodySeg2.setRotationPoint(0F, 22.5F, 0.5F);
      BodySeg2.setTextureSize(64, 32);
      BodySeg2.mirror = true;
      setRotation(BodySeg2, 0F, 0F, 0F);
      BodySeg2Tall = new ModelRenderer(this, 0, 12);
      BodySeg2Tall.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1);
      BodySeg2Tall.setRotationPoint(0F, 22.5F, 0.5F);
      BodySeg2Tall.setTextureSize(64, 32);
      BodySeg2Tall.mirror = true;
      setRotation(BodySeg2Tall, 0F, 0F, 0F);
      BodySeg2WIde = new ModelRenderer(this, 0, 10);
      BodySeg2WIde.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 1);
      BodySeg2WIde.setRotationPoint(0F, 22.5F, 0.5F);
      BodySeg2WIde.setTextureSize(64, 32);
      BodySeg2WIde.mirror = true;
      setRotation(BodySeg2WIde, 0F, 0F, 0F);
      BodySeg3 = new ModelRenderer(this, 12, 11);
      BodySeg3.addBox(-1F, -1F, 0F, 2, 2, 3);
      BodySeg3.setRotationPoint(0F, 22.7F, -1F);
      BodySeg3.setTextureSize(64, 32);
      BodySeg3.mirror = true;
      setRotation(BodySeg3, -0.0872665F, 0F, 0F);
      BodySeg4 = new ModelRenderer(this, 12, 11);
      BodySeg4.addBox(-1F, -1F, 0F, 2, 2, 3);
      BodySeg4.setRotationPoint(0F, 23F, 0.8F);
      BodySeg4.setTextureSize(64, 32);
      BodySeg4.mirror = true;
      setRotation(BodySeg4, 0.122173F, 0F, 0F);
      BodyUpperNarrow = new ModelRenderer(this, 12, 5);
      BodyUpperNarrow.addBox(-1F, -2F, -0.5F, 2, 2, 1);
      BodyUpperNarrow.setRotationPoint(0F, 22.4F, 3F);
      BodyUpperNarrow.setTextureSize(64, 32);
      BodyUpperNarrow.mirror = true;
      setRotation(BodyUpperNarrow, -1.308997F, 0F, 0F);
      BodyLowerNarrow = new ModelRenderer(this, 12, 8);
      BodyLowerNarrow.addBox(-1F, -2F, -0.5F, 2, 2, 1);
      BodyLowerNarrow.setRotationPoint(0F, 23.2F, 3.5F);
      BodyLowerNarrow.setTextureSize(64, 32);
      BodyLowerNarrow.mirror = true;
      setRotation(BodyLowerNarrow, -0.9599311F, 0F, 0F);
      BodySeg5 = new ModelRenderer(this, 12, 2);
      BodySeg5.addBox(-0.5F, -0.5F, 0F, 1, 1, 2);
      BodySeg5.setRotationPoint(0F, 22.2F, 4.8F);
      BodySeg5.setTextureSize(64, 32);
      BodySeg5.mirror = true;
      setRotation(BodySeg5, 0.9599311F, 0F, 0F);
      LowerLegR = new ModelRenderer(this, 0, 8);
      LowerLegR.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
      LowerLegR.setRotationPoint(-1F, 22.8F, -1.5F);
      LowerLegR.setTextureSize(64, 32);
      LowerLegR.mirror = true;
      setRotation(LowerLegR, -0.2617994F, 0.5235988F, 0.1745329F);
      UpperLegL = new ModelRenderer(this, 0, 8);
      UpperLegL.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
      UpperLegL.setRotationPoint(1F, 22F, -3F);
      UpperLegL.setTextureSize(64, 32);
      UpperLegL.mirror = true;
      setRotation(UpperLegL, -1.134464F, -0.5235988F, -0.1745329F);
      LowerLegL = new ModelRenderer(this, 0, 8);
      LowerLegL.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
      LowerLegL.setRotationPoint(1F, 22.8F, -1.5F);
      LowerLegL.setTextureSize(64, 32);
      LowerLegL.mirror = true;
      setRotation(LowerLegL, -0.2617994F, -0.5235988F, -0.1745329F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    EyeR.render(f5);
    EyeL.render(f5);
    Mouth.render(f5);
    AntR.render(f5);
    AntL.render(f5);
    AntBase.render(f5);
    Head.render(f5);
    HeadWide.render(f5);
    HeadTall.render(f5);
    HeadThick.render(f5);
    UpperLegR.render(f5);
    BodyWide.render(f5);
    BodyTall.render(f5);
    Body.render(f5);
    BodyThick.render(f5);
    BodySeg2.render(f5);
    BodySeg2Tall.render(f5);
    BodySeg2WIde.render(f5);
    BodySeg3.render(f5);
    BodySeg4.render(f5);
    BodyUpperNarrow.render(f5);
    BodyLowerNarrow.render(f5);
    BodySeg5.render(f5);
    LowerLegR.render(f5);
    UpperLegL.render(f5);
    LowerLegL.render(f5);
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
