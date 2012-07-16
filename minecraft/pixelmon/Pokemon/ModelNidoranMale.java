package pixelmon.Pokemon;

import net.minecraft.src.Entity;

import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelNidoranMale extends ModelBase
{
  //fields
    ModelRenderer Tail_segment3;
    ModelRenderer Tail_segment4;
    ModelRenderer Tail_segment2;
    ModelRenderer Tail_segment6;
    ModelRenderer Tail_segment1;
    ModelRenderer Tail_segment5;
    ModelRenderer Body;
    ModelRenderer Right_leg;
    ModelRenderer right_foot;
    ModelRenderer Left_arm;
    ModelRenderer Right_arm;
    ModelRenderer right_leg;
    ModelRenderer HEADPIECE;
    ModelRenderer left_leg;
    ModelRenderer Left_foot;
    ModelRenderer Head;
    ModelRenderer Left_Ear;
    ModelRenderer Right_Ear;
    ModelRenderer Horn;
    ModelRenderer teeth;
    ModelRenderer RIGHTLEGPIECE;
    ModelRenderer LEFTLEGPIECE;
    
    
    

    
    
    
  public ModelNidoranMale()
  {
    textureWidth = 128;
    textureHeight = 128;
     
      Tail_segment3 = new ModelRenderer(this, 112, 10);
      Tail_segment3.addBox(0F, -4F, 0F, 0, 4, 1);
      Tail_segment3.setRotationPoint(-2F, 17F, 0F);
      Tail_segment3.setTextureSize(128, 128);
      Tail_segment3.mirror = true;
      setRotation(Tail_segment3, 0F, 0F, -0.2230705F);
      
      Tail_segment4 = new ModelRenderer(this, 112, 10);
      Tail_segment4.addBox(2F, -4F, 0F, 0, 4, 1);
      Tail_segment4.setRotationPoint(-4F, 17.8F, 1.3F);
      Tail_segment4.setTextureSize(128, 128);
      Tail_segment4.mirror = true;
      setRotation(Tail_segment4, -0.0743572F, 0F, -0.1115358F);
      
      Tail_segment2 = new ModelRenderer(this, 112, 10);
      Tail_segment2.addBox(0F, -4F, 0F, 0, 4, 1);
      Tail_segment2.setRotationPoint(2F, 17.5F, 1F);
      Tail_segment2.setTextureSize(128, 128);
      Tail_segment2.mirror = true;
      setRotation(Tail_segment2, -0.1115358F, 0F, 0.111544F);
      
      Tail_segment6 = new ModelRenderer(this, 112, 10);
      Tail_segment6.addBox(0F, -4F, 0F, 0, 4, 1);
      Tail_segment6.setRotationPoint(2.3F, 18.7F, 2F);
      Tail_segment6.setTextureSize(128, 128);
      Tail_segment6.mirror = true;
      setRotation(Tail_segment6, -0.1858931F, 0F, 0F);
      
      Tail_segment1 = new ModelRenderer(this, 112, 10);
      Tail_segment1.addBox(0F, -4F, 0F, 0, 4, 1);
      Tail_segment1.setRotationPoint(2F, 17F, 0F);
      Tail_segment1.setTextureSize(128, 128);
      Tail_segment1.mirror = true;
      setRotation(Tail_segment1, 0F, 0F, 0.2230717F);
      
      Tail_segment5 = new ModelRenderer(this, 112, 10);
      Tail_segment5.addBox(0F, -5F, 0F, 0, 4, 1);
      Tail_segment5.setRotationPoint(-2.3F, 20F, 2F);
      Tail_segment5.setTextureSize(128, 128);
      Tail_segment5.mirror = true;
      setRotation(Tail_segment5, -0.1858931F, 0F, 0F);
      
      Body = new ModelRenderer(this, 102, 10);
      Body.addBox(-3F, -1F, 0F, 6, 5, 7);
      Body.setRotationPoint(0F, 16F, -3F);
      Body.setTextureSize(128, 128);
      Body.mirror = true;
      setRotation(Body, -0.2147065F, 0F, 0F);
      
      Left_arm = new ModelRenderer(this, 41, 0);
      Left_arm.addBox(-1F, 0F, -4F, 2, 2, 4);
      Left_arm.setRotationPoint(2.3F, 19.5F, -2F);
      Left_arm.setTextureSize(128, 128);
      Left_arm.mirror = true;
      setRotation(Left_arm, 0.9294653F, 0F, 0F);
      
      Right_arm = new ModelRenderer(this, 41, 6);
      Right_arm.addBox(-1F, 0F, -4F, 2, 2, 4);
      Right_arm.setRotationPoint(-2.3F, 19.5F, -2F);
      Right_arm.setTextureSize(128, 128);
      Right_arm.mirror = true;
      setRotation(Right_arm, 0.9294653F, 0F, 0F);
      
    
      Left_foot = new ModelRenderer(this, 13, 0);
      Left_foot.addBox(3F, 18F, 4F, 3, 1, 4);
      Left_foot.setRotationPoint(0F,0F,0F);
      Left_foot.setTextureSize(128, 128);
      Left_foot.mirror = true;
      setRotation(Left_foot, 0.4461433F, 0F, 0F);

      left_leg = new ModelRenderer(this, 27, 0);
      left_leg.addBox(-2F, -0.5F, -0.5F, 3, 3, 4);
      left_leg.setRotationPoint(0F,0F,0F);
      left_leg.setTextureSize(128, 128);
      left_leg.mirror = true;
      setRotation(left_leg, -0.9294653F, 0F, 0F);
   
      Horn = new ModelRenderer(this, 77, 0);
      Horn.addBox(0F, -9F, -1F, 0, 4, 1);
      Horn.setRotationPoint(0F, 0F,0F);
      Horn.setTextureSize(128, 128);
      Horn.mirror = true;
      setRotation(Horn, 0.9666439F, 0F, 0F);
      
      Left_Ear = new ModelRenderer(this, 79, 0);
      Left_Ear.addBox(3F,-3.5F,2F, 0, 4, 6);
      Left_Ear.setRotationPoint(0F,0F,0F);
      Left_Ear.setTextureSize(128, 128);
      Left_Ear.mirror = true;
      setRotation(Left_Ear, 0.9294653F, 0.4461433F, 0F);
      
      Right_Ear = new ModelRenderer(this, 91, 0);
      Right_Ear.addBox(-3F,-3.5F,2F, 0, 4, 6);
      Right_Ear.setRotationPoint(1F, 3F, -12F);
      Right_Ear.setTextureSize(128, 128);
      Right_Ear.mirror = true;
      setRotation(Right_Ear, 0.9294653F, -0.3346075F, 0F);
      
      Head = new ModelRenderer(this, 53, 0);
      Head.addBox(-3F, -3F, -7F, 6, 5, 6);
      Head.setRotationPoint(0F,0F,0F);
      Head.setTextureSize(128, 128);
      Head.mirror = true;
      setRotation(Head, 0.0371786F, 0F, 0F);
   
      right_foot = new ModelRenderer(this, 13, 0);
      right_foot.addBox(-2F, 3.5F, -4.5F, 3, 1, 4);
      right_foot.setRotationPoint(0F,0F,0F);
      right_foot.setTextureSize(128, 128);
      right_foot.mirror = true;
      setRotation(right_foot, 0.4461433F, 0F, 0F);
      
      right_leg = new ModelRenderer(this, 27, 0);
      right_leg.addBox(-2F,18F,4F, 3, 3, 4);
      right_leg.setRotationPoint(0F,0F,0F);
      right_leg.setTextureSize(128, 128);
      right_leg.mirror = true;
      setRotation(right_leg, -0.9294653F, 0F, 0F);
      
      
      
      HEADPIECE = new ModelRenderer(this, "HEADPIECE");
      HEADPIECE.setRotationPoint(0F, 15F, -1F);
      setRotation(HEADPIECE, 0F, 0F, 0F);
      HEADPIECE.mirror = true;
      
            
      LEFTLEGPIECE = new ModelRenderer(this, "LEFTLEGPIECE");
      LEFTLEGPIECE.setRotationPoint(-2F, 18F, 4F);
      setRotation(LEFTLEGPIECE, 0F, 0F, 0F);
      LEFTLEGPIECE.mirror = true;
      
         
      RIGHTLEGPIECE = new ModelRenderer(this, "RIGHTLEGPIECE");
      RIGHTLEGPIECE.setRotationPoint(3F, 18F, 4F);
      setRotation(RIGHTLEGPIECE, 0F, 0F, 0F);
      RIGHTLEGPIECE.mirror = true;
      
      
      
      HEADPIECE.addChild(Head);
      HEADPIECE.addChild(Horn);
      HEADPIECE.addChild(Left_Ear);
      HEADPIECE.addChild(Right_Ear);
      
      LEFTLEGPIECE.addChild(Left_foot);
      LEFTLEGPIECE.addChild(left_leg);
      
      RIGHTLEGPIECE.addChild(right_foot);
      RIGHTLEGPIECE.addChild(right_leg);
      
      
      
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    HEADPIECE.render(f5);
    Tail_segment3.render(f5);
    Tail_segment4.render(f5);
    Tail_segment2.render(f5);
    Tail_segment6.render(f5);
    Tail_segment1.render(f5);
    Tail_segment5.render(f5);
    Body.render(f5);
    RIGHTLEGPIECE.render(f5);
    LEFTLEGPIECE.render(f5);
    
    
    
    
    
    
    
    
    
    
    
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
