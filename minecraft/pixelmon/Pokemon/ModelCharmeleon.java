package pixelmon.Pokemon;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelCharmeleon extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer muzzle;
    ModelRenderer creast;
    ModelRenderer creast1;
    ModelRenderer creast2;
    ModelRenderer body;
    ModelRenderer belly;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer flame;
  
  public ModelCharmeleon()
  {
	    textureWidth = 64;
	    textureHeight = 64;
	    
	      head = new ModelRenderer(this, 0, 52);
	      head.addBox(-3F, -6F, -4F, 6, 6, 6);
	      head.setRotationPoint(0F, 6F, 1F);
	      head.setTextureSize(64, 64);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      muzzle = new ModelRenderer(this, 24, 59);
	      muzzle.addBox(-2.5F, -3F, -6F, 5, 3, 2);
	      muzzle.setRotationPoint(0F, 6F, 1F);
	      muzzle.setTextureSize(64, 64);
	      muzzle.mirror = true;
	      setRotation(muzzle, 0F, 0F, 0F);
	      creast = new ModelRenderer(this, 8, 47);
	      creast.addBox(-1.5F, -6.5F, 2F, 3, 3, 2);
	      creast.setRotationPoint(0F, 6F, 0F);
	      creast.setTextureSize(64, 64);
	      creast.mirror = true;
	      setRotation(creast, -0.1570796F, 0F, 0F);
	      creast1 = new ModelRenderer(this, 2, 49);
	      creast1.addBox(-1F, -7F, 3F, 2, 2, 1);
	      creast1.setRotationPoint(0F, 6.5F, 0.5F);
	      creast1.setTextureSize(64, 64);
	      creast1.mirror = true;
	      setRotation(creast1, -0.2268928F, 0F, 0F);
	      creast2 = new ModelRenderer(this, 4, 47);
	      creast2.addBox(-0.5F, -6F, 3F, 1, 1, 1);
	      creast2.setRotationPoint(0F, 6F, 0.8F);
	      creast2.setTextureSize(64, 64);
	      creast2.mirror = true;
	      setRotation(creast2, -0.296706F, 0F, 0F);
	      body = new ModelRenderer(this, 0, 0);
	      body.addBox(-4F, 0F, -2F, 8, 12, 4);
	      body.setRotationPoint(0F, 6F, 0F);
	      body.setTextureSize(64, 64);
	      body.mirror = true;
	      setRotation(body, 0.2268928F, 0F, 0F);
	      belly = new ModelRenderer(this, 0, 16);
	      belly.addBox(-4F, 0F, -2F, 4, 9, 4);
	      belly.setRotationPoint(2F, 9F, 0F);
	      belly.setTextureSize(64, 64);
	      belly.mirror = true;
	      setRotation(belly, 0.2268928F, 0F, 0F);
	      rightarm = new ModelRenderer(this, 24, 0);
	      rightarm.addBox(-3F, -1F, -2F, 3, 7, 3);
	      rightarm.setRotationPoint(-4F, 8F, 1F);
	      rightarm.setTextureSize(64, 64);
	      rightarm.mirror = true;
	      setRotation(rightarm, 0F, 0F, 0F);
	      leftarm = new ModelRenderer(this, 24, 10);
	      leftarm.addBox(0F, -1F, -2F, 3, 7, 3);
	      leftarm.setRotationPoint(4F, 8F, 1F);
	      leftarm.setTextureSize(64, 64);
	      leftarm.mirror = true;
	      setRotation(leftarm, 0F, 0F, 0F);
	      rightleg = new ModelRenderer(this, 24, 21);
	      rightleg.addBox(-2F, 0F, -2F, 3, 8, 4);
	      rightleg.setRotationPoint(-3F, 16F, 2F);
	      rightleg.setTextureSize(64, 64);
	      rightleg.mirror = true;
	      setRotation(rightleg, 0F, 0F, 0F);
	      leftleg = new ModelRenderer(this, 24, 33);
	      leftleg.addBox(-2F, 0F, -2F, 3, 8, 4);
	      leftleg.setRotationPoint(4F, 16F, 2F);
	      leftleg.setTextureSize(64, 64);
	      leftleg.mirror = true;
	      setRotation(leftleg, 0F, 0F, 0F);
	      Tail1 = new ModelRenderer(this, 38, 0);
	      Tail1.addBox(-1.5F, -1.5F, 0F, 3, 3, 7);
	      Tail1.setRotationPoint(0F, 15F, 4F);
	      Tail1.setTextureSize(64, 64);
	      Tail1.mirror = true;
	      setRotation(Tail1, 0.3490659F, 0.2617994F, -0.7853982F);
	      Tail2 = new ModelRenderer(this, 38, 10);
	      Tail2.addBox(-1F, -1F, 0F, 2, 2, 6);
	      Tail2.setRotationPoint(0F, 13F, 10F);
	      Tail2.setTextureSize(64, 64);
	      Tail2.mirror = true;
	      setRotation(Tail2, 0.9773844F, 0F, 0F);
	      flame = new ModelRenderer(this, 0, 29);
	      flame.addBox(0F, 3F, 13F, 0, 5, 4);
	      flame.setRotationPoint(0F, 1.75F, -1.5F);
	      flame.setTextureSize(64, 64);
	      flame.mirror = true;
	      setRotation(flame, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    head.render(f5);
    muzzle.render(f5);
    creast.render(f5);
    creast1.render(f5);
    creast2.render(f5);
    body.render(f5);
    belly.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    Tail1.render(f5);
    Tail2.render(f5);
    flame.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
      head.rotateAngleY = f3 / (180F / (float)Math.PI);
      head.rotateAngleX = f4 / (180F / (float)Math.PI);
      muzzle.rotateAngleY = head.rotateAngleY;
      muzzle.rotateAngleX = head.rotateAngleX;
      creast.rotateAngleY = head.rotateAngleY;
      creast.rotateAngleY = head.rotateAngleX;
      creast1.rotateAngleY = head.rotateAngleY;
      creast1.rotateAngleY = head.rotateAngleX;
      creast2.rotateAngleY = head.rotateAngleY;
      creast2.rotateAngleY = head.rotateAngleX;
      rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
      leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      rightarm.rotateAngleZ = 0.0F;
      leftarm.rotateAngleZ = 0.0F;
      rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
      rightleg.rotateAngleY = 0.0F;
      leftleg.rotateAngleY = 0.0F;
  }

}
