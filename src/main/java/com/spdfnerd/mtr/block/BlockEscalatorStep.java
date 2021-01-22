package com.spdfnerd.mtr.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockEscalatorStep extends BlockEscalatorBase {

	public static final BooleanProperty DIRECTION = BooleanProperty.create("direction");

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.UP && !(world.getBlockState(currentPos.up()).getBlock() instanceof BlockEscalatorSide)) {
			return Blocks.AIR.getDefaultState();
		} else {
			return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (IBlock.getStatePropertySafe(state, SIDE) == EnumSide.RIGHT) {
			IBlock.onBreakCreative(world, player, pos.offset(IBlock.getSideDirection(state)));
		}
		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		EnumEscalatorOrientation orientation = IBlock.getStatePropertySafe(state, ORIENTATION);
		if (orientation == EnumEscalatorOrientation.FLAT || orientation == EnumEscalatorOrientation.TRANSITION_BOTTOM) {
			return Block.makeCuboidShape(0d, 0d, 0d, 16d, 15d, 16);
		} else {
			return VoxelShapes.combineAndSimplify(Block.makeCuboidShape(1d, 0d, 1d, 15d, 16d, 15), super.getCollisionShape(state, world, pos, context),
					IBooleanFunction.AND);
		}
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		final Direction facing = IBlock.getStatePropertySafe(state, HORIZONTAL_FACING);
		final boolean direction = IBlock.getStatePropertySafe(state, DIRECTION);
		final float speed = 0.1F;

		switch (facing) {
			case NORTH:
				entity.addVelocity(0, 0, direction ? -speed : speed);
				break;
			case EAST:
				entity.addVelocity(direction ? speed : -speed, 0, 0);
				break;
			case SOUTH:
				entity.addVelocity(0, 0, direction ? speed : -speed);
				break;
			case WEST:
				entity.addVelocity(direction ? -speed : speed, 0, 0);
				break;
			default:
				break;
		}
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
		super.onFallenUpon(world, pos, entity, fallDistance * 0.5F);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		return IBlock.checkHoldingBrush(world, player, () -> {
			final boolean direction = !IBlock.getStatePropertySafe(state, DIRECTION);
			final Direction blockFacing = IBlock.getStatePropertySafe(state, HORIZONTAL_FACING);

			update(world, pos, blockFacing, direction);
			update(world, pos, blockFacing.getOpposite(), direction);

			final BlockPos sidePos = pos.offset(IBlock.getSideDirection(state));
			if (isStep(world, sidePos)) {
				final BlockEscalatorStep block = (BlockEscalatorStep) world.getBlockState(sidePos).getBlock();
				block.update(world, sidePos, blockFacing, direction);
				block.update(world, sidePos, blockFacing.getOpposite(), direction);
			}
		});
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, DIRECTION, ORIENTATION, SIDE);
	}

	private void update(World world, BlockPos pos, Direction offset, boolean direction) {
		world.setBlockState(pos, world.getBlockState(pos).with(DIRECTION, direction));
		final BlockPos offsetPos = pos.offset(offset);

		if (isStep(world, offsetPos)) {
			update(world, offsetPos, offset, direction);
		}
		if (isStep(world, offsetPos.up())) {
			update(world, offsetPos.up(), offset, direction);
		}
		if (isStep(world, offsetPos.down())) {
			update(world, offsetPos.down(), offset, direction);
		}
	}

	private boolean isStep(World world, BlockPos pos) {
		final Block block = world.getBlockState(pos).getBlock();
		return block instanceof BlockEscalatorStep;
	}

}
