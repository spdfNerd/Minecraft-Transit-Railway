package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.tileentity.StationNameTallBlockTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockStationNameTallBlock extends BlockStationNameTallBase {

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		final Tuple<Integer, Integer> bounds = getBounds(state);
		return VoxelShapes.or(IBlock.getVoxelShapeByDirection(2, bounds.getA(), 5, 14, bounds.getB(), 11, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING)),
				BlockStationPole.getStationPoleShape());
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx) {
		return IBlock.isReplaceable(ctx, Direction.UP, 3) ?
				getDefaultState().with(HORIZONTAL_FACING, ctx.getPlayer().getHorizontalFacing()).with(METAL, true).with(THIRD,
				EnumThird.LOWER) : null;
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new StationNameTallBlockTileEntity();
	}

}
