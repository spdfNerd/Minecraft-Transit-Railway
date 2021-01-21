package com.spdfnerd.mtr.item;

import com.spdfnerd.mtr.block.BlockEscalatorBase;
import com.spdfnerd.mtr.block.BlockEscalatorSide;
import com.spdfnerd.mtr.block.BlockEscalatorStep;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ItemEscalator extends Item implements IBlock {

	public ItemEscalator(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (ItemPSDAPGBase.blocksNotReplaceable(context, 2, 2, null)) {
			return ActionResult.FAIL;
		}

		final World world = context.getWorld();
		Direction playerFacing = context.getPlayerFacing();
		BlockPos pos1 = context.getBlockPos().offset(context.getSide());
		BlockPos pos2 = pos1.offset(playerFacing.rotateYClockwise());

		final BlockState frontState = world.getBlockState(pos1.offset(playerFacing));
		if (frontState.getBlock() instanceof BlockEscalatorBase) {
			if (IBlock.getStatePropertySafe(frontState, BlockEscalatorBase.FACING) == playerFacing.getOpposite()) {
				playerFacing = playerFacing.getOpposite();
				final BlockPos pos3 = pos1;
				pos1 = pos2;
				pos2 = pos3;
			}
		}

		final BlockState stepState = Blocks.ESCALATOR_STEP.getDefaultState().with(BlockEscalatorStep.FACING, playerFacing);
		world.setBlockState(pos1, stepState.with(SIDE, EnumSide.LEFT));
		world.setBlockState(pos2, stepState.with(SIDE, EnumSide.RIGHT));

		final BlockState sideState = Blocks.ESCALATOR_SIDE.getDefaultState().with(BlockEscalatorSide.FACING, playerFacing);
		world.setBlockState(pos1.up(), sideState.with(SIDE, EnumSide.LEFT));
		world.setBlockState(pos2.up(), sideState.with(SIDE, EnumSide.RIGHT));

		context.getStack().decrement(1);
		return ActionResult.SUCCESS;
	}
}
