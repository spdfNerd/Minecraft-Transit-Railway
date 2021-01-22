package com.spdfnerd.mtr.block;

import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IPropagateBlock {

	IntegerProperty PROPAGATE_PROPERTY = IntegerProperty.create("propagate_property", 0, 3);

	default void propagate(World world, BlockPos pos, Direction direction) {
		for (int i = 1; i <= 3; i++) {
			final BlockPos offsetPos = pos.offset(direction, i);
			final BlockState offsetState = world.getBlockState(offsetPos);
			if (offsetState.getBlock() == this) {
				world.setBlockState(offsetPos, offsetState.with(PROPAGATE_PROPERTY, IBlock.getStatePropertySafe(world, pos, PROPAGATE_PROPERTY)));
				propagate(world, offsetPos, direction);
				return;
			}
		}
	}

}
