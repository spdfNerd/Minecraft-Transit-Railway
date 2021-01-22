package com.spdfnerd.mtr.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class BlockPSDAPGDoorBase extends BlockPSDAPGBase {

	public static final int MAX_OPEN_VALUE = 32;

	public static final BooleanProperty END = BooleanProperty.create("end");
	public static final IntegerProperty OPEN = IntegerProperty.create("open", 0, MAX_OPEN_VALUE);

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if (IBlock.getSideDirection(state) == facing && !(facingState.getBlock() == this)) {
			return Blocks.AIR.getDefaultState();
		} else {
			final BlockState superState = super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
			if (superState.getBlock() == Blocks.AIR) {
				return superState;
			} else {
				final boolean end = world.getBlockState(currentPos.offset(IBlock.getSideDirection(state).getOpposite())).getBlock() instanceof BlockPSDAPGGlassEndBase;
				return superState.with(END, end);
			}
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos offsetPos = pos;
		if (IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP) {
			offsetPos = offsetPos.down();
		}
		if (IBlock.getStatePropertySafe(state, SIDE) == EnumSide.RIGHT) {
			offsetPos = offsetPos.offset(IBlock.getSideDirection(state));
		}
		IBlock.onBreakCreative(world, player, offsetPos);
		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		final int open = IBlock.getStatePropertySafe(state, OPEN);
		if (open > 0) {
			final int height = isAPG() && IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP ? 9 : 16;
			final EnumSide side = IBlock.getStatePropertySafe(state, SIDE);
			final double open1 = open / 2D;
			final double open2 = 16 - open / 2D;
			return IBlock.getVoxelShapeByDirection(side == EnumSide.LEFT ? 0 : open1, 0, 0, side == EnumSide.RIGHT ? 16 : open2, height, 4, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING));
		} else {
			return super.getShape(state, world, pos, context);
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(END, HORIZONTAL_FACING, HALF, OPEN, SIDE);
	}

}
