package com.spdfnerd.mtr.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class BlockPlatform extends HorizontalBlock {

	public static final EnumProperty<EnumDoorType> DOOR_TYPE = EnumProperty.create("door_type", EnumDoorType.class);
	public static final IntegerProperty SIDE = IntegerProperty.create("side", 0, 4);

	public BlockPlatform(Properties properties) {
		super(properties);
		setDefaultState(getDefaultState().with(DOOR_TYPE, EnumDoorType.NONE));
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		return getActualState(world, currentPos, state);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return getActualState(context.getWorld(), context.getPos(), getDefaultState());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, DOOR_TYPE, SIDE);
	}

	private BlockState getActualState(IWorld world, BlockPos pos, BlockState state) {
		Direction facing = searchBlock(world, pos, AbstractRailBlock.class, 3);
		if (facing == null) {
			facing = searchBlock(world, pos, AirBlock.class, 1);
		}
		if (facing == null) {
			facing = searchBlock(world, pos.up(), AbstractRailBlock.class, 3);
		}
		if (facing == null) {
			facing = searchBlock(world, pos.up(), AirBlock.class, 1);
		}
		if (facing == null) {
			facing = Direction.NORTH;
		}

		final BlockState stateAbove = world.getBlockState(pos.up());
		final Block blockAbove = stateAbove.getBlock();

		EnumDoorType doorType;
		if (blockAbove instanceof BlockPSDDoor || blockAbove instanceof BlockPSDGlass || blockAbove instanceof BlockPSDGlassEnd) {
			doorType = EnumDoorType.PSD;
			facing = IBlock.getStatePropertySafe(stateAbove, HORIZONTAL_FACING);
		} else if (blockAbove instanceof BlockAPGDoor || blockAbove instanceof BlockAPGGlass || blockAbove instanceof BlockAPGGlassEnd) {
			doorType = EnumDoorType.APG;
			facing = IBlock.getStatePropertySafe(stateAbove, HORIZONTAL_FACING);
		} else {
			doorType = EnumDoorType.NONE;
		}

		final boolean aboveIsDoor = blockAbove instanceof BlockPSDAPGDoorBase;

		final BlockState stateLeftAbove = world.getBlockState(pos.up().offset(facing.rotateYCCW()));
		final boolean leftAboveIsDoor = stateLeftAbove.getBlock() instanceof BlockPSDAPGDoorBase;

		final BlockState stateRightAbove = world.getBlockState(pos.up().offset(facing.rotateY()));
		final boolean rightAboveIsDoor = stateRightAbove.getBlock() instanceof BlockPSDAPGDoorBase;

		int side;
		if (aboveIsDoor && rightAboveIsDoor) {
			side = 2;
		} else if (aboveIsDoor && leftAboveIsDoor) {
			side = 3;
		} else if (rightAboveIsDoor) {
			side = 1;
			facing = IBlock.getStatePropertySafe(stateRightAbove, HORIZONTAL_FACING);
		} else if (leftAboveIsDoor) {
			side = 4;
			facing = IBlock.getStatePropertySafe(stateLeftAbove, HORIZONTAL_FACING);
		} else {
			side = 0;
		}

		return state.with(HORIZONTAL_FACING, facing).with(DOOR_TYPE, doorType).with(SIDE, side);
	}

	private Direction searchBlock(IBlockReader world, BlockPos pos, Class<? extends Block> blockClass, int maxRadius) {
		for (int radius = 1; radius <= maxRadius; radius++) {
			for (final Direction facing : new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST}) {
				if (blockClass.isInstance(world.getBlockState(pos.offset(facing, radius)).getBlock())) {
					return facing;
				}
			}
		}

		return null;
	}

	private enum EnumDoorType implements IStringSerializable {

		NONE("none"), PSD("psd"), APG("apg");
		private final String name;

		EnumDoorType(String nameIn) {
			name = nameIn;
		}

		@Override
		public String getString() {
			return name;
		}

	}

}
