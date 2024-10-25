package com.hbm.blocks.machine;

import java.util.List;
import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.generic.YellowBarrel;
import com.hbm.lib.InventoryHelper;
import com.hbm.main.MainRegistry;
import com.hbm.tileentity.machine.TileEntityBarrel;
import com.hbm.tileentity.machine.TileEntityMachineBattery;
import com.hbm.tileentity.machine.TileEntitySafe;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.hbm.util.I18nUtil;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class BlockFluidBarrel extends BlockContainer {

	public static final String FLUID_NBT_KEY = "HbmFluidKey";

	private int capacity;
	public static boolean keepInventory;
	
	public BlockFluidBarrel(Material materialIn, int cap, String s) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		capacity = cap;
		
		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBarrel(capacity);
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag advanced) {
		if(this == ModBlocks.barrel_plastic) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "12,000"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannothot"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotcor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
		}
		
		if(this == ModBlocks.barrel_corroded) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "6,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhighcor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
			list.add(TextFormatting.RED + I18nUtil.resolveKey("desc.leaky"));
		}
		
		if(this == ModBlocks.barrel_iron) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "8,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotcor1"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
		}
		
		if(this == ModBlocks.barrel_steel) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "16,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.cancor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannothighcor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
		}
		
		if(this == ModBlocks.barrel_antimatter) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "16,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhighcor"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canam"));
		}
		
		if(this == ModBlocks.barrel_tcalloy) {
			list.add(TextFormatting.AQUA + I18nUtil.resolveKey("desc.capacity", "24,000"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhot"));
			list.add(TextFormatting.GREEN + I18nUtil.resolveKey("desc.canhighcor"));
			list.add(TextFormatting.YELLOW + I18nUtil.resolveKey("desc.cannotam"));
		}
	}
	
	@Override
	public Block setSoundType(SoundType sound) {
		return super.setSoundType(sound);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return true;
			
		} else if(!player.isSneaking()) {
			player.openGui(MainRegistry.instance, ModBlocks.guiID_barrel, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
			
		} else {
			return false;
		}
	}
	
	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest){
		if(!player.capabilities.isCreativeMode && !world.isRemote && willHarvest) {

			ItemStack drop = new ItemStack(this);
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityBarrel) {
				TileEntityBarrel barrel = (TileEntityBarrel) te;

				NBTTagCompound nbt = new NBTTagCompound();
				barrel.writeNBT(nbt);

				if(!nbt.hasNoTags()) {
					drop.setTagCompound(nbt);
				}
			}

			InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop);
		}
		return world.setBlockToAir(pos);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if(!keepInventory)
			InventoryHelper.dropInventoryItems(worldIn, pos, worldIn.getTileEntity(pos));
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
		TileEntity te = world.getTileEntity(pos);

		if(stack.hasTagCompound()){
			if (te instanceof TileEntityBarrel) {
				TileEntityBarrel barrel = (TileEntityBarrel) te;
				if(stack.hasDisplayName()) {
					barrel.setCustomName(stack.getDisplayName());
				}
				try {
					NBTTagCompound stackNBT = stack.getTagCompound();
					if(stackNBT.hasKey("NBT_PERSISTENT_KEY")){
						barrel.readNBT(stackNBT);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return YellowBarrel.BARREL_BB;
	}
}
