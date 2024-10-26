package com.hbm.inventory.gui;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerAutocrafter;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachineAutocrafter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GUIAutocrafter extends GuiInfoContainer {

	private static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/processing/gui_autocrafter.png");
	private TileEntityMachineAutocrafter autocrafter;

	public GUIAutocrafter(InventoryPlayer invPlayer, TileEntityMachineAutocrafter tema) {
		super(new ContainerAutocrafter(invPlayer, tema));
		autocrafter = tema;

		this.xSize = 176;
		this.ySize = 240;
	}

	@Override
	public void drawScreen(int x, int y, float interp) {
		super.drawScreen(x, y, interp);

		this.drawElectricityInfo(this, x, y, guiLeft + 17, guiTop + 45, 16, 52, autocrafter.getPower(), autocrafter.getMaxPower());

		if(this.mc.player.inventory.getItemStack() == null) {
			for(int i = 0; i < 9; ++i) {
				Slot slot = this.inventorySlots.inventorySlots.get(i);

				if(this.isMouseOverSlot(slot, x, y) && autocrafter.modes[i] != null) {

					String label = TextFormatting.YELLOW + "";

					switch(autocrafter.modes[i]) {
					case "exact": label += "Item and meta match"; break;
					case "wildcard": label += "Item matches"; break;
					default: label += "Ore dict key matches: " + autocrafter.modes[i]; break;
					}

					this.drawHoveringText(Arrays.asList(TextFormatting.RED + "Right click to change", label), x, y - 30);
				}
			}

			Slot slot = this.inventorySlots.inventorySlots.get(9);

			if(this.isMouseOverSlot(slot, x, y) && !autocrafter.inventory.getStackInSlot(9).isEmpty()) {
				this.drawHoveringText(Arrays.asList(TextFormatting.RED + "Right click to change", TextFormatting.YELLOW + "" + (autocrafter.recipeIndex + 1) + " / " + autocrafter.recipeCount), x, y - 30);
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.autocrafter.hasCustomInventoryName() ? this.autocrafter.getInventoryName() : I18n.format(this.autocrafter.getInventoryName());

		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int i = (int)(autocrafter.getPower() * 52 / autocrafter.getMaxPower());
		drawTexturedModalRect(guiLeft + 17, guiTop + 97 - i, 176, 52 - i, 16, i);

	}
}
