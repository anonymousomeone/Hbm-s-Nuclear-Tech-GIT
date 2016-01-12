// Date: 19.05.2015 14:19:31
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package com.hbm.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSword extends ModelBase
{
  //fields
    ModelRenderer GripBottom;
    ModelRenderer GripHandle;
    ModelRenderer Shield;
    ModelRenderer Blade;
    ModelRenderer BladeTip;
    ModelRenderer Shield1;
    ModelRenderer Shield2;
  
  public ModelSword()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      GripBottom = new ModelRenderer(this, 0, 17);
      GripBottom.addBox(0F, 0F, 0F, 3, 3, 1);
      GripBottom.setRotationPoint(0F, 0F, 0F);
      GripBottom.setTextureSize(64, 32);
      GripBottom.mirror = true;
      setRotation(GripBottom, 0F, 0F, 0F);
      GripHandle = new ModelRenderer(this, 8, 2);
      GripHandle.addBox(0F, 0F, 0F, 2, 5, 1);
      GripHandle.setRotationPoint(0.5F, -5F, 0F);
      GripHandle.setTextureSize(64, 32);
      GripHandle.mirror = true;
      setRotation(GripHandle, 0F, 0F, 0F);
      Shield = new ModelRenderer(this, 14, 5);
      Shield.addBox(0F, 0F, 0F, 6, 1, 3);
      Shield.setRotationPoint(-1.5F, -6F, -1F);
      Shield.setTextureSize(64, 32);
      Shield.mirror = true;
      setRotation(Shield, 0F, 0F, 0F);
      Blade = new ModelRenderer(this, 0, 0);
      Blade.addBox(0F, 0F, 0F, 3, 16, 1);
      Blade.setRotationPoint(0F, -22F, 0F);
      Blade.setTextureSize(64, 32);
      Blade.mirror = true;
      setRotation(Blade, 0F, 0F, 0F);
      BladeTip = new ModelRenderer(this, 8, 0);
      BladeTip.addBox(0F, 0F, 0F, 2, 1, 1);
      BladeTip.setRotationPoint(0.5F, -23F, 0F);
      BladeTip.setTextureSize(64, 32);
      BladeTip.mirror = true;
      setRotation(BladeTip, 0F, 0F, 0F);
      Shield1 = new ModelRenderer(this, 14, 0);
      Shield1.addBox(0F, 0F, 0F, 1, 1, 4);
      Shield1.setRotationPoint(-2F, -6.5F, -1.5F);
      Shield1.setTextureSize(64, 32);
      Shield1.mirror = true;
      setRotation(Shield1, 0F, 0F, 0F);
      Shield2 = new ModelRenderer(this, 24, 0);
      Shield2.addBox(0F, 0F, 0F, 1, 1, 4);
      Shield2.setRotationPoint(4F, -6.5F, -1.5F);
      Shield2.setTextureSize(64, 32);
      Shield2.mirror = true;
      setRotation(Shield2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    GripBottom.render(f5);
    GripHandle.render(f5);
    Shield.render(f5);
    Blade.render(f5);
    BladeTip.render(f5);
    Shield1.render(f5);
    Shield2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
