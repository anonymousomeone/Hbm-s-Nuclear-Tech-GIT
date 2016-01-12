package com.hbm.entity;

import com.hbm.explosion.ExplosionChaos;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityGrenadeFlare extends EntityThrowable
{
    private static final String __OBFID = "CL_00001722";
    public Entity shooter;

    public EntityGrenadeFlare(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityGrenadeFlare(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntityGrenadeFlare(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }
    
    public void onUpdate() {
    	super.onUpdate();
    	if(this.ticksExisted > 250)
    	{
    		this.setDead();
    	}
    }

    protected void onImpact(MovingObjectPosition p_70184_1_)
    {
    	this.motionX = 0;
    	this.motionY = 0;
    	this.motionZ = 0;
    }
}
