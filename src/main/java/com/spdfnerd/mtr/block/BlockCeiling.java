package com.spdfnerd.mtr.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class BlockCeiling extends Block {

	public static final BooleanProperty FACING = BooleanProperty.create("facing");
	public static final BooleanProperty LIGHT = BooleanProperty.create("light");

	public BlockCeiling(Properties properties) {
		super(properties);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		boolean facing = context.getPlayer().getHorizontalFacing().getAxis() == Direction.Axis.X;
		return getDefaultState().with(FACING, facing).with(LIGHT, hasLight(facing, context.getPos()));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return Block.makeCuboidShape(0, 7, 0, 16, 10, 16);
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return state.with(LIGHT, hasLight(IBlock.getStatePropertySafe(state, FACING), currentPos));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, LIGHT);
	}

	private boolean hasLight(boolean facing, BlockPos pos) {
		if (facing) {
			return pos.getZ() % 3 == 0;
		} else {
			return pos.getX() % 3 == 0;
		}
	}

}
