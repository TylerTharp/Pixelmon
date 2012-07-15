package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelMareepShaven extends ModelBase
{
  //fields
    ModelRenderer Body1;
    ModelRenderer butt;
    ModelRenderer body2;
    ModelRenderer haunch1;
    ModelRenderer haunch2;
    ModelRenderer haunch3;
    ModelRenderer haunch4;
    ModelRenderer backlegleft;
    ModelRenderer frontlegleft;
    ModelRenderer frontlegright;
    ModelRenderer backlegright;
    ModelRenderer TAIL1PIECE;
    ModelRenderer NECKPIECE;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer Tail3;
    ModelRenderer Tailtip;
    ModelRenderer leftear;
    ModelRenderer rightear;
    ModelRenderer Neck1;
    ModelRenderer Head1;
  
    
    ModelRenderer TAIL2PIECE;
    ModelRenderer TAIL3PIECE;
    ModelRenderer TAIL4PIECE;
    ModelRenderer EAR1PIECE;
    ModelRenderer EAR2PIECE;
    ModelRenderer HEADPIECE;
    
    
    
    
  
  public ModelMareepShaven()
  {
    textureWidth = 128;
    textureHeight = 128;
    setTextureOffset("NECKPIECE.NeckFolder", 0, 0);
    
      Body1 = new ModelRenderer(this, 32, 0);
      Body1.addBox(0F, 0F, 0F, 7, 6, 16);
      Body1.setRotationPoint(-3F, 12F, -3F);
      Body1.setTextureSize(128, 128);
      Body1.mirror = true;
      setRotation(Body1, 0F, 0F, 0F);
      butt = new ModelRenderer(this, 0, 58);
      butt.addBox(0F, 0F, 0F, 2, 2, 4);
      butt.setRotationPoint(-0.5F, 14F, 12.2F);
      butt.setTextureSize(128, 128);
      butt.mirror = true;
      setRotation(butt, 0.0743572F, 0F, 0F);
      body2 = new ModelRenderer(this, 48, 6);
      body2.addBox(0F, -1F, 0F, 6, 5, 2);
      body2.setRotationPoint(-2.5F, 13F, -3.5F);
      body2.setTextureSize(128, 128);
      body2.mirror = true;
      setRotation(body2, -0.1858931F, 0F, 0F);
      haunch1 = new ModelRenderer(this, 0, 0);
      haunch1.addBox(-1F, 0F, 0F, 2, 3, 3);
      haunch1.setRotationPoint(3.7F, 14.5F, -1F);
      haunch1.setTextureSize(128, 128);
      haunch1.mirror = true;
      setRotation(haunch1, 0F, 0F, -0.1487144F);
      haunch2 = new ModelRenderer(this, 0, 0);
      haunch2.addBox(-1F, 0F, 0F, 2, 3, 3);
      haunch2.setRotationPoint(3.7F, 14.5F, 10F);
      haunch2.setTextureSize(128, 128);
      haunch2.mirror = true;
      setRotation(haunch2, 0F, 0F, -0.1487144F);
      haunch3 = new ModelRenderer(this, 0, 0);
      haunch3.addBox(1F, 0F, 0F, 2, 3, 3);
      haunch3.setRotationPoint(-4.7F, 14.5F, -1F);
      haunch3.setTextureSize(128, 128);
      haunch3.mirror = true;
      setRotation(haunch3, 0F, 0F, 0.1487195F);
      haunch4 = new ModelRenderer(this, 0, 0);
      haunch4.addBox(1F, 0F, 0F, 2, 3, 3);
      haunch4.setRotationPoint(-4.7F, 14.5F, 10F);
      haunch4.setTextureSize(128, 128);
      haunch4.mirror = true;
      setRotation(haunch4, 0F, 0F, 0.1487195F);
      backlegleft = new ModelRenderer(this, 0, 37);
      backlegleft.addBox(0F, 0F, -1F, 2, 7, 2);
      backlegleft.setRotationPoint(3F, 17F, 11.5F);
      backlegleft.setTextureSize(128, 128);
      backlegleft.mirror = true;
      setRotation(backlegleft, 0F, 0F, 0F);
      frontlegleft = new ModelRenderer(this, 0, 37);
      frontlegleft.addBox(0F, 0F, -1F, 2, 7, 2);
      frontlegleft.setRotationPoint(3F, 17F, 0.5F);
      frontlegleft.setTextureSize(128, 128);
      frontlegleft.mirror = true;
      setRotation(frontlegleft, 0F, 0F, 0F);
      frontlegright = new ModelRenderer(this, 0, 37);
      frontlegright.addBox(-2F, 0F, -1F, 2, 7, 2);
      frontlegright.setRotationPoint(-2F, 17F, 0.5F);
      frontlegright.setTextureSize(128, 128);
      frontlegright.mirror = true;
      setRotation(frontlegright, 0F, 0F, 0F);
      backlegright = new ModelRenderer(this, 0, 37);
      backlegright.addBox(-2F, 0F, -1F, 2, 7, 2);
      backlegright.setRotationPoint(-2F, 17F, 11.5F);
      backlegright.setTextureSize(128, 128);
      backlegright.mirror = true;
      setRotation(backlegright, 0F, 0F, 0F);
    TAIL1PIECE = new ModelRenderer(this, "TAIL1PIECE");
    TAIL1PIECE.setRotationPoint(0.5F, 15F, 14F);
    setRotation(TAIL1PIECE, 0F, 0F, 0F);
    TAIL1PIECE.mirror = true;
    

      Tail1 = new ModelRenderer(this, 0, 48);
      Tail1.addBox(0F, 0F, -0.3F, 2, 2, 3);
      Tail1.setRotationPoint(-1.01F, 5.5F, -3F);
      Tail1.setTextureSize(128, 128);
      Tail1.mirror = true;
      setRotation(Tail1, 0.4089647F, 0F, 0F);
    TAIL2PIECE = new ModelRenderer(this, "TAIL2PIECE");
    TAIL2PIECE.setRotationPoint(0.5F, -1.5F, 5F);
    setRotation(TAIL2PIECE, 0.1858931F, 0F, 0F);
 
    TAIL2PIECE.mirror = true;
    
      TAIL1PIECE.addChild(Tail1);
      TAIL1PIECE.addChild(TAIL2PIECE);
      
      Tail2 = new ModelRenderer(this, 0, 57);
      Tail2.addBox(0F, 0F, -0.2F, 2, 2, 3);
      Tail2.setRotationPoint(-1.5F, 14.8F, -5.3F);
      Tail2.setTextureSize(128, 128);
      Tail2.mirror = true;
      setRotation(Tail2, 0.7063936F, 0F, 0F);
    TAIL3PIECE = new ModelRenderer(this, "TAIL3PIECE");
    TAIL3PIECE.setRotationPoint(0F, -2F, 2F);
    setRotation(TAIL3PIECE, 0F, 0F, 0F);
    TAIL3PIECE.mirror = true;
    
    TAIL2PIECE.addChild(Tail2);
    TAIL2PIECE.addChild(TAIL3PIECE);
    
      Tailtip = new ModelRenderer(this, 0, 69);
      Tailtip.addBox(0F, 0F, -0.2F, 3, 3, 3);
      Tailtip.setRotationPoint(-2F, -12.7F, 8.8F);
      Tailtip.setTextureSize(128, 128);
      Tailtip.mirror = true;
      setRotation(Tailtip, -0.5205006F, 0F, 0F);
      Tail3 = new ModelRenderer(this, 0, 49);
      Tail3.addBox(-0.01F, -0.4F, 0F, 2, 2, 2);
      Tail3.setRotationPoint(-1.5F, 26F, 1F);
      Tail3.setTextureSize(128, 128);
      Tail3.mirror = true;
      setRotation(Tail3, 1.152537F, 0F, 0F);
      
      TAIL3PIECE.addChild(Tail3);
      TAIL3PIECE.addChild(Tailtip);
     
     
    NECKPIECE = new ModelRenderer(this, "NECKPIECE");
    NECKPIECE.setRotationPoint(0F, 14F, -3F);
    setRotation(NECKPIECE, 0F, 0F, 0F);
    NECKPIECE.mirror = true;
      Neck1 = new ModelRenderer(this, 25, 50);
      Neck1.addBox(0F, -7F, 8F, 4, 4, 8);
      Neck1.setRotationPoint(-1.5F, 0.5F, -2.2F);
      Neck1.setTextureSize(128, 128);
      Neck1.mirror = true;
      setRotation(Neck1, -0.9098432F, 0F, 0F);
    HEADPIECE = new ModelRenderer(this, "HEADPIECE");
    HEADPIECE.setRotationPoint(0F, -4F, -3F);
    setRotation(HEADPIECE, 0F, 0F, 0F);
    HEADPIECE.mirror = true;
    
    NECKPIECE.addChild(Neck1);
    NECKPIECE.addChild(HEADPIECE);
    
      Head1 = new ModelRenderer(this, 0, 19);
      Head1.addBox(0F, 0F, 0F, 5, 5, 5);
      Head1.setRotationPoint(-2F, -3F, -3F);
      Head1.setTextureSize(128, 128);
      Head1.mirror = true;
      setRotation(Head1, 0F, 0F, 0F);
    EAR2PIECE = new ModelRenderer(this, "EAR2PIECE");
    EAR2PIECE.setRotationPoint(-1F, -2F, 1F);
    setRotation(EAR2PIECE, 0F, 0F, 0F);
    EAR2PIECE.mirror = true;
      rightear = new ModelRenderer(this, 12, 32);
      rightear.addBox(-1F, 0F, 0F, 3, 2, 2);
      rightear.setRotationPoint(-4F, -0.7F, -1F);
      rightear.setTextureSize(128, 128);
      rightear.mirror = true;
      setRotation(rightear, 0F, 0F, -0.1373574F);
      
      EAR2PIECE.addChild(rightear);
      
    EAR1PIECE = new ModelRenderer(this, "EAR1PIECE");
    EAR1PIECE.setRotationPoint(2F, -2.666667F, 1F);
    setRotation(EAR1PIECE, 0F, 0F, 0F);
    EAR1PIECE.mirror = true;
      leftear = new ModelRenderer(this, 0, 32);
      leftear.addBox(0F, 0F, 0F, 3, 2, 2);
      leftear.setRotationPoint(2F, -0.5F, -1F);
      leftear.setTextureSize(128, 128);
      leftear.mirror = true;
      setRotation(leftear, 0F, 0F, 0.1373543F);
    
      EAR1PIECE.addChild(leftear);
      HEADPIECE.addChild(Head1);
      HEADPIECE.addChild(EAR1PIECE);
      HEADPIECE.addChild(EAR2PIECE);
     
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Body1.render(f5);
    butt.render(f5);
    body2.render(f5);
    haunch1.render(f5);
    haunch2.render(f5);
    haunch3.render(f5);
    haunch4.render(f5);
    backlegleft.render(f5);
    frontlegleft.render(f5);
    frontlegright.render(f5);
    backlegright.render(f5);
    TAIL1PIECE.render(f5);
    NECKPIECE.render(f5);
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
