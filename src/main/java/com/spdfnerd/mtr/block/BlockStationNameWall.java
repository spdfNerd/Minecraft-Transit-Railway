package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.tileentity.StationNameTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockStationNameWall extends BlockStationNameBase {

	public BlockStationNameWall(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		return IBlock.checkHoldingBrush(world, player, () -> world.setBlockState(pos, state.func_235896_a_(COLOUR)));
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		final Direction facing = IBlock.getStatePropertySafe(state, HORIZONTAL_FACING);
		return worldIn.getBlockState(pos.offset(facing)).isSolidSide(worldIn, pos.offset(facing), facing.getOpposite());
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		final Direction side = context.getFace();
		if (side != Direction.UP && side != Direction.DOWN) {
			return getDefaultState().with(HORIZONTAL_FACING, side.getOpposite());
		} else {
			return null;
		}
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction direction, BlockState newState, IWorld world, BlockPos pos, BlockPos posFrom) {
		if (direction.getOpposite() == IBlock.getStatePropertySafe(state, HORIZONTAL_FACING).getOpposite() && !state.isValidPosition(world, pos) ) {
			return Blocks.AIR.getDefaultState();
		} else {
			return state;
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, 16, 1, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.empty();
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new StationNameTileEntity();
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(COLOUR, HORIZONTAL_FACING);
	}

}
