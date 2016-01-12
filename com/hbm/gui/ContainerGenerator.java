package com.hbm.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.hbm.blocks.TileEntityMachineGenerator;
import com.hbm.blocks.TileEntityRtgFurnace;

public class ContainerGenerator extends Container {
	
	private TileEntityMachineGenerator diFurnace;
	
	private int water;
	private int cool;
	private int power;
	private int heat;
	
	public ContainerGenerator(InventoryPlayer invPlayer, TileEntityMachineGenerator tedf) {
		
		diFurnace = tedf;
		
		this.addSlotToContainer(new Slot(tedf, 0, 116, 36));
		this.addSlotToContainer(new Slot(tedf, 1, 134, 36));
		this.addSlotToContainer(new Slot(tedf, 2, 152, 36));
		this.addSlotToContainer(new Slot(tedf, 3, 116, 54));
		this.addSlotToContainer(new Slot(tedf, 4, 134, 54));
		this.addSlotToContainer(new Slot(tedf, 5, 152, 54));
		this.addSlotToContainer(new Slot(tedf, 6, 116, 72));
		this.addSlotToContainer(new Slot(tedf, 7, 134, 72));
		this.addSlotToContainer(new Slot(tedf, 8, 152, 72));
		this.addSlotToContainer(new Slot(tedf, 9, 8, 90));
		this.addSlotToContainer(new Slot(tedf, 10, 26, 90));
		this.addSlotToContainer(new Slot(tedf, 11, 62, 90));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + 56));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142 + 56));
		}
	}
	
	public void addCraftingToCrafters(ICrafting crafting) {
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.diFurnace.water);
		crafting.sendProgressBarUpdate(this, 1, this.diFurnace.cool);
		crafting.sendProgressBarUpdate(this, 2, this.diFurnace.power);
		crafting.sendProgressBarUpdate(this, 3, this.diFurnace.heat);
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
			
			if(this.water != this.diFurnace.water)
			{
				par1.sendProgressBarUpdate(this, 0, this.diFurnace.water);
			}
			
			if(this.cool != this.diFurnace.cool)
			{
				par1.sendProgressBarUpdate(this, 1, this.diFurnace.cool);
			}
			
			if(this.power != this.diFurnace.power)
			{
				par1.sendProgressBarUpdate(this, 2, this.diFurnace.power);
			}
			
			if(this.heat != this.diFurnace.heat)
			{
				par1.sendProgressBarUpdate(this, 3, this.diFurnace.heat);
			}
		}
		
		this.water = this.diFurnace.water;
		this.cool = this.diFurnace.cool;
		this.power = this.diFurnace.power;
		this.heat = this.diFurnace.heat;
	}
	
	public void updateProgressBar(int i, int j) {
		if(i == 0)
		{
			diFurnace.water = j;
		}
		if(i == 1)
		{
			diFurnace.cool = j;
		}
		if(i == 2)
		{
			diFurnace.power = j;
		}
		if(i == 3)
		{
			diFurnace.heat = j;
		}
	}

}
