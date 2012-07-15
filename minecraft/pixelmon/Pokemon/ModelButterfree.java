package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelButterfree extends ModelBase
{
  //fields
    ModelRenderer AntennaBaseR;
    ModelRenderer AntennaTipR;
    ModelRenderer AntennaBaseL;
    ModelRenderer AntennaTipL;
    ModelRenderer EyeR;
    ModelRenderer EyeL;
    ModelRenderer Head;
    ModelRenderer HeadTall;
    ModelRenderer HeadWide;
    ModelRenderer HeadThick;
    ModelRenderer Mouth;
    ModelRenderer NeckBR;
    ModelRenderer NeckFR;
    ModelRenderer NeckBL;
    ModelRenderer NeckFL;
    ModelRenderer Body;
    ModelRenderer BodyWide;
    ModelRenderer BodyThick;
    ModelRenderer UpperWingLeft;
    ModelRenderer UpperWingRight;
    ModelRenderer PalmL;
    ModelRenderer FootR;
    ModelRenderer FootL;
    ModelRenderer PalmR;
  
  public ModelButterfree()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      AntennaBaseR = new ModelRenderer(this, 27, 1);
      AntennaBaseR.addBox(0F, -4F, -0.5F, 0, 4, 1);
      AntennaBaseR.setRotationPoint(-1F, 9F, -1F);
      AntennaBaseR.setTextureSize(64, 32);
      AntennaBaseR.mirror = true;
      setRotation(AntennaBaseR, 0.1745329F, 0.0872665F, -0.122173F);
      AntennaTipR = new ModelRenderer(this, 27, 1);
      AntennaTipR.addBox(0F, -3F, -0.5F, 0, 3, 1);
      AntennaTipR.setRotationPoint(-1.5F, 5.3F, -1.6F);
      AntennaTipR.setTextureSize(64, 32);
      AntennaTipR.mirror = true;
      setRotation(AntennaTipR, 0.5235988F, 0.0872665F, -0.122173F);
      AntennaBaseL = new ModelRenderer(this, 27, 1);
      AntennaBaseL.addBox(0F, -4F, -0.5F, 0, 4, 1);
      AntennaBaseL.setRotationPoint(1F, 9F, -1F);
      AntennaBaseL.setTextureSize(64, 32);
      AntennaBaseL.mirror = true;
      setRotation(AntennaBaseL, 0.1745329F, -0.0872665F, 0.122173F);
      AntennaTipL = new ModelRenderer(this, 27, 1);
      AntennaTipL.addBox(0F, -3F, -0.5F, 0, 3, 1);
      AntennaTipL.setRotationPoint(1.5F, 5.3F, -1.6F);
      AntennaTipL.setTextureSize(64, 32);
      AntennaTipL.mirror = true;
      setRotation(AntennaTipL, 0.5235988F, -0.0872665F, 0.122173F);
      EyeR = new ModelRenderer(this, 18, 2);
      EyeR.addBox(-1F, -1F, -1F, 2, 2, 2);
      EyeR.setRotationPoint(-1.7F, 10F, -2F);
      EyeR.setTextureSize(64, 32);
      EyeR.mirror = true;
      setRotation(EyeR, 0F, 0.3490659F, -0.0872665F);
      EyeL = new ModelRenderer(this, 18, 2);
      EyeL.addBox(-1F, -1F, -1F, 2, 2, 2);
      EyeL.setRotationPoint(1.7F, 10F, -2F);
      EyeL.setTextureSize(64, 32);
      EyeL.mirror = true;
      setRotation(EyeL, 0F, -0.3490659F, 0.0872665F);
      EyeL.mirror = false;
      Head = new ModelRenderer(this, 18, 24);
      Head.addBox(-2.5F, -4F, -2.5F, 5, 4, 4);
      Head.setRotationPoint(0F, 12.5F, 0.2F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0.0349066F, 0F, 0F);
      HeadTall = new ModelRenderer(this, 18, 6);
      HeadTall.addBox(-2F, -1F, -1.5F, 4, 1, 3);
      HeadTall.setRotationPoint(0F, 9.1F, -0.3F);
      HeadTall.setTextureSize(64, 32);
      HeadTall.mirror = true;
      setRotation(HeadTall, 0.0349066F, 0.0872665F, 0F);
      HeadWide = new ModelRenderer(this, 18, 10);
      HeadWide.addBox(-3F, -1.5F, -1.5F, 6, 3, 3);
      HeadWide.setRotationPoint(0F, 10.8F, -0.4F);
      HeadWide.setTextureSize(64, 32);
      HeadWide.mirror = true;
      setRotation(HeadWide, 0.0349066F, 0F, 0F);
      HeadThick = new ModelRenderer(this, 18, 16);
      HeadThick.addBox(-2F, -1.5F, -2.5F, 4, 3, 5);
      HeadThick.setRotationPoint(0F, 10.8F, -0.4F);
      HeadThick.setTextureSize(64, 32);
      HeadThick.mirror = true;
      setRotation(HeadThick, 0.0349066F, 0F, 0F);
      Mouth = new ModelRenderer(this, 18, 0);
      Mouth.addBox(-1F, -0.5F, -1F, 2, 1, 1);
      Mouth.setRotationPoint(0F, 11.7F, -2.3F);
      Mouth.setTextureSize(64, 32);
      Mouth.mirror = true;
      setRotation(Mouth, 0.0349066F, 0F, 0F);
      NeckBR = new ModelRenderer(this, 6, 0);
      NeckBR.addBox(-1.5F, -1F, -1.5F, 3, 1, 3);
      NeckBR.setRotationPoint(-0.6F, 13F, 0F);
      NeckBR.setTextureSize(64, 32);
      NeckBR.mirror = true;
      setRotation(NeckBR, 0F, 0F, 0F);
      NeckFR = new ModelRenderer(this, 6, 0);
      NeckFR.addBox(-1.5F, -1F, -1.5F, 3, 1, 3);
      NeckFR.setRotationPoint(-0.6F, 13F, -0.5F);
      NeckFR.setTextureSize(64, 32);
      NeckFR.mirror = true;
      setRotation(NeckFR, 0F, 0F, 0F);
      NeckBL = new ModelRenderer(this, 6, 0);
      NeckBL.addBox(-1.5F, -1F, -1.5F, 3, 1, 3);
      NeckBL.setRotationPoint(0.6F, 13F, 0F);
      NeckBL.setTextureSize(64, 32);
      NeckBL.mirror = true;
      setRotation(NeckBL, 0F, 0F, 0F);
      NeckFL = new ModelRenderer(this, 6, 0);
      NeckFL.addBox(-1.5F, -1F, -1.5F, 3, 1, 3);
      NeckFL.setRotationPoint(0.6F, 13F, -0.5F);
      NeckFL.setTextureSize(64, 32);
      NeckFL.mirror = true;
      setRotation(NeckFL, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 23);
      Body.addBox(-2.5F, -2.5F, -2F, 5, 5, 4);
      Body.setRotationPoint(0F, 15.3F, 0F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0.1047198F, 0F, 0F);
      BodyWide = new ModelRenderer(this, 0, 16);
      BodyWide.addBox(-3F, -2F, -1.5F, 6, 4, 3);
      BodyWide.setRotationPoint(0F, 15.5F, 0F);
      BodyWide.setTextureSize(64, 32);
      BodyWide.mirror = true;
      setRotation(BodyWide, 0.1047198F, 0F, 0F);
      BodyThick = new ModelRenderer(this, 0, 7);
      BodyThick.addBox(-2F, -2F, -2.5F, 4, 4, 5);
      BodyThick.setRotationPoint(0F, 15.5F, 0F);
      BodyThick.setTextureSize(64, 32);
      BodyThick.mirror = true;
      setRotation(BodyThick, 0.1047198F, 0F, 0F);
      UpperWingLeft = new ModelRenderer(this, 44, 16);
      UpperWingLeft.addBox(0F, -8F, 0F, 9, 15, 1);
      UpperWingLeft.setRotationPoint(1.8F, 13F, 2F);
      UpperWingLeft.setTextureSize(64, 32);
      UpperWingLeft.mirror = true;
      setRotation(UpperWingLeft, 0.0872665F, -0.5235988F, -0.0349066F);
      UpperWingRight = new ModelRenderer(this, 44, 0);
      UpperWingRight.addBox(0F, -8F, 0F, 9, 15, 1);
      UpperWingRight.setRotationPoint(-1.8F, 13F, 2F);
      UpperWingRight.setTextureSize(64, 32);
      UpperWingRight.mirror = true;
      setRotation(UpperWingRight, -0.0872665F, -2.617994F, -0.0523599F);
      setRotation(UpperWingRight, -0.0872665F, -2.635447F, -0.0523599F);
      PalmL = new ModelRenderer(this, 7, 4);
      PalmL.addBox(-0.5F, -1F, -1F, 1, 2, 1);
      PalmL.setRotationPoint(1.4F, 14.6F, -1.9F);
      PalmL.setTextureSize(64, 32);
      PalmL.mirror = true;
      setRotation(PalmL, 0.6632251F, 0.1745329F, 0F);
      FootR = new ModelRenderer(this, 0, 1);
      FootR.addBox(-0.5F, 0F, -0.5F, 1, 4, 2);
      FootR.setRotationPoint(-1.5F, 17.4F, -0.3F);
      FootR.setTextureSize(64, 32);
      FootR.mirror = true;
      setRotation(FootR, -0.1745329F, 0.3316126F, 0F);
      FootL = new ModelRenderer(this, 0, 1);
      FootL.addBox(-0.5F, 0F, -0.5F, 1, 4, 2);
      FootL.setRotationPoint(1.4F, 17.4F, -0.3F);
      FootL.setTextureSize(64, 32);
      FootL.mirror = true;
      setRotation(FootL, -0.1745329F, -0.3316126F, 0F);
      PalmR = new ModelRenderer(this, 6, 4);
      PalmR.addBox(-0.5F, -1F, -1F, 1, 2, 1);
      PalmR.setRotationPoint(-1.4F, 14.6F, -1.9F);
      PalmR.setTextureSize(64, 32);
      PalmR.mirror = true;
      setRotation(PalmR, 0.6632251F, -0.1745329F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    AntennaBaseR.render(f5);
    AntennaTipR.render(f5);
    AntennaBaseL.render(f5);
    AntennaTipL.render(f5);
    EyeR.render(f5);
    EyeL.render(f5);
    Head.render(f5);
    HeadTall.render(f5);
    HeadWide.render(f5);
    HeadThick.render(f5);
    Mouth.render(f5);
    NeckBR.render(f5);
    NeckFR.render(f5);
    NeckBL.render(f5);
    NeckFL.render(f5);
    Body.render(f5);
    BodyWide.render(f5);
    BodyThick.render(f5);
    UpperWingLeft.render(f5);
    UpperWingRight.render(f5);
    PalmL.render(f5);
    FootR.render(f5);
    FootL.render(f5);
    PalmR.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  float timer = 0;
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
    
    timer += Math.PI/60;
    UpperWingLeft.rotateAngleY = 0.5F * MathHelper.cos((timer + (float)Math.PI));
    UpperWingRight.rotateAngleY = -0.5F * MathHelper.cos((timer + (float)Math.PI) )+ 6*(float)Math.PI/6;    
  }

}