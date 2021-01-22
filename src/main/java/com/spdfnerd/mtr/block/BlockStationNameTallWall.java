package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.tileentity.StationNameTallWallTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockStationNameTallWall extends BlockStationNameTallBase {

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		final Tuple<Integer, Integer> bounds = getBounds(state);
		return IBlock.getVoxelShapeByDirection(2, bounds.getA(), 0, 14, bounds.getB(), 0.5, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		final Direction blockSide = context.getFace();
		final Direction facing = blockSide == Direction.UP || blockSide == Direction.DOWN ? context.getPlayer().getHorizontalFacing() : blockSide.getOpposite();
		return IBlock.isReplaceable(context, Direction.UP, 3) ? getDefaultState().with(HORIZONTAL_FACING, facing).with(METAL, true).with(THIRD,
				EnumThird.LOWER) :	null;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new StationNameTallWallTileEntity();
	}

}
