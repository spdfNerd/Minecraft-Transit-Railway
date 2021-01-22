package com.spdfnerd.mtr.item;

import com.spdfnerd.mtr.block.BlockEscalatorBase;
import com.spdfnerd.mtr.block.BlockEscalatorSide;
import com.spdfnerd.mtr.block.BlockEscalatorStep;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemEscalator extends Item implements IBlock {

	public ItemEscalator(Properties properties) {
		super(properties);
	}


	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		if (ItemPSDAPGBase.blocksNotReplaceable(context, 2, 2, null)) {
			return ActionResultType.FAIL;
		}

		final World world = context.getWorld();
		Direction playerFacing = context.getPlayer().getHorizontalFacing();
		BlockPos pos1 = context.getPos().offset(context.getFace());
		BlockPos pos2 = pos1.offset(playerFacing.rotateY());

		final BlockState frontState = world.getBlockState(pos1.offset(playerFacing));
		if (frontState.getBlock() instanceof BlockEscalatorBase) {
			if (IBlock.getStatePropertySafe(frontState, BlockEscalatorBase.HORIZONTAL_FACING) == playerFacing.getOpposite()) {
				playerFacing = playerFacing.getOpposite();
				final BlockPos pos3 = pos1;
				pos1 = pos2;
				pos2 = pos3;
			}
		}

		final BlockState stepState = Registration.ESCALATOR_STEP.get().getDefaultState().with(BlockEscalatorStep.HORIZONTAL_FACING, playerFacing);
		world.setBlockState(pos1, stepState.with(SIDE, EnumSide.LEFT));
		world.setBlockState(pos2, stepState.with(SIDE, EnumSide.RIGHT));

		final BlockState sideState = Registration.ESCALATOR_STEP.get().getDefaultState().with(BlockEscalatorSide.HORIZONTAL_FACING, playerFacing);
		world.setBlockState(pos1.up(), sideState.with(SIDE, EnumSide.LEFT));
		world.setBlockState(pos2.up(), sideState.with(SIDE, EnumSide.RIGHT));

		context.getItem().getStack().shrink(1);
		return ActionResultType.SUCCESS;
	}

}
