package com.hbm.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.hbm.explosion.ExplosionChaos;

public class EntityGrenadeCluster extends EntityThrowable
{
    private static final String __OBFID = "CL_00001722";

    public EntityGrenadeCluster(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    public EntityGrenadeCluster(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntityGrenadeCluster(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    protected void onImpact(MovingObjectPosition p_70184_1_)
    {
        if (p_70184_1_.entityHit != null)
        {
            byte b0 = 0;

            if (p_70184_1_.entityHit instanceof EntityBlaze)
            {
                b0 = 3;
            }

            p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)b0);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
            ExplosionChaos.cluster(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, 10, 50);
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.5F, true);
        }
    }
}
