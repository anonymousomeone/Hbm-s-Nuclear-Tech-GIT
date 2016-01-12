package com.hbm.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.hbm.blocks.TileEntityMachineBattery;
import com.hbm.blocks.TileEntityMachineElectricFurnace;

public class ContainerMachineBattery extends Container {
	
	private TileEntityMachineBattery diFurnace;
	private int power;
	
	public ContainerMachineBattery(InventoryPlayer invPlayer, TileEntityMachineBattery tedf) {
		power = 0;
		
		diFurnace = tedf;
		
		this.addSlotToContainer(new Slot(tedf, 0, 26, 35));
		this.addSlotToContainer(new Slot(tedf, 1, 134, 35));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.diFurnace.power);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
		return null;
    }

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return diFurnace.isUseableByPlayer(player);
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting par1 = (ICrafting)this.crafters.get(i);
			
			if(this.power != this.diFurnace.power)
			{
				par1.sendProgressBarUpdate(this, 0, this.diFurnace.power);
			}
		}
		
		this.power = this.diFurnace.power;
	}
	
	public void updateProgressBar(int i, int j) {
		if(i == 0)
		{
			diFurnace.power = j;
		}
	}
}
