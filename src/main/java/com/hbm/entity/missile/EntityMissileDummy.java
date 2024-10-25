package com.hbm.entity.missile;

import com.hbm.explosion.ExplosionLarge;
import com.hbm.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityMissileDummy extends EntityMissileBaseAdvanced {

    public EntityMissileDummy(World worldIn) {
        super(worldIn);
        this.setSize(1F, 7F);
    }

    public EntityMissileDummy(World world, float x, float y, float z, int a, int b) {
        super(world, x, y, z, a, b);
        this.setSize(1F, 7F);
    }

    @Override
    public void onImpact() {
        ExplosionLarge.explode(world, posX, posY, posZ, 0.0F, true, true, false);
    }

    @Override
    public RadarTargetType getTargetType() {
        return RadarTargetType.MISSILE_TIER0;
    }

    @Override
    public List<ItemStack> getDebris() {
        List<ItemStack> list = new ArrayList<ItemStack>();

        list.add(new ItemStack(ModItems.wire_aluminium, 4));
        list.add(new ItemStack(ModItems.plate_titanium, 4));
        list.add(new ItemStack(ModItems.hull_small_aluminium, 2));
        list.add(new ItemStack(ModItems.ducttape, 1));
        list.add(new ItemStack(ModItems.circuit_targeting_tier1, 1));

        return list;
    }

    // this isnt even used tf?
    @Override
    public ItemStack getDebrisRareDrop() {
        return new ItemStack(ModItems.hull_small_steel);
    }

}