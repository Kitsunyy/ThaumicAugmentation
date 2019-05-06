/**
 *	Thaumic Augmentation
 *	Copyright (c) 2019 TheCodex6824.
 *
 *  This file is part of Thaumic Augmentation.
 *
 *  Thaumic Augmentation is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Thaumic Augmentation is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Thaumic Augmentation.  If not, see <https://www.gnu.org/licenses/>.
 */

package thecodex6824.thaumicaugmentation.common.block;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.FXDispatcher;
import thecodex6824.thaumicaugmentation.api.TAConfig;
import thecodex6824.thaumicaugmentation.api.block.property.ILightSourceBlock;
import thecodex6824.thaumicaugmentation.common.block.prefab.BlockTABase;
import thecodex6824.thaumicaugmentation.common.tile.TileCastedLight;

public class BlockCastedLight extends BlockTABase implements ILightSourceBlock {

	protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.375, 0.375, 0.375, 0.625, 0.625, 0.625);
	protected static final AxisAlignedBB COLLISION_BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 0, 0, 0);
	
	public BlockCastedLight(String name) {
		super(name, Material.CLOTH, TileCastedLight.class);
		setHardness(0.0F);
		setResistance(0.0F);
	}
	
	@Override
	public SoundType getSoundType() {
		return SoundType.CLOTH;
	}
	
	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 0;
	}
	
	@Override
	public boolean canSpawnInBlock() {
		return true;
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return COLLISION_BOUNDING_BOX;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {}
	
	@Override
	public EnumPushReaction getPushReaction(IBlockState state) {
		return EnumPushReaction.DESTROY;
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}
	
	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return TAConfig.castedLightSimpleRenderer.getValue() ? EnumBlockRenderType.MODEL : EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public float getAmbientOcclusionLightValue(IBlockState state) {
		return state.getValue(ILightSourceBlock.LIGHT_LEVEL);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs item, NonNullList<ItemStack> items) {}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (!TAConfig.castedLightSimpleRenderer.getValue() && rand.nextBoolean()) {
			int color = Aspect.LIGHT.getColor();
			FXDispatcher.INSTANCE.drawWispyMotes(pos.getX() + 0.5F + rand.nextGaussian() / 4, pos.getY() + 0.5F + rand.nextGaussian() / 4,
					pos.getZ() + 0.5F + rand.nextGaussian() / 4, 0, 0, 0, 40, ((color >> 16) & 0xFF) / 255.0F, ((color >> 8) & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, 0.01F);
		}
	}
	
}