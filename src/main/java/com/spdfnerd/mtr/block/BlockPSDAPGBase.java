package com.spdfnerd.mtr.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class BlockPSDAPGBase extends HorizontalBlock implements IBlock {

	public BlockPSDAPGBase() {
		super(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).setOpaque((state, reader, pos) -> false));
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		boolean isTop = IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP;
		if ((isTop && facing == Direction.DOWN || !isTop && facing == Direction.UP) && !(facingState.getBlock() == this)) {
			return Blocks.AIR.getDefaultState();
		} else {
			return state;
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP) {
			IBlock.onBreakCreative(world, player, pos.down());
		}
		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state;
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state;
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
		return new ItemStack(asItem());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		final int height = isAPG() && IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP ? 9 : 16;
		return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, height, 4, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING));
	}

	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	protected boolean isAPG() {
		return this instanceof BlockAPGDoor || this instanceof BlockAPGGlass || this instanceof BlockAPGGlassEnd;
	}

}
