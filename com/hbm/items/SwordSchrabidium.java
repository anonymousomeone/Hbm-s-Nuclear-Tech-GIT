package com.hbm.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class SwordSchrabidium extends ItemSword {

	public SwordSchrabidium(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
	}

    public EnumRarity getRarity(ItemStack p_77613_1_) {
    	
		return EnumRarity.rare;
    }

}
