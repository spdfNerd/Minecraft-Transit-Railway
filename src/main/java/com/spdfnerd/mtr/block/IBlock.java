package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public interface IBlock {

	EnumProperty<SlabType> HALF = EnumProperty.create("half", SlabType.class);
	EnumProperty<EnumThird> THIRD = EnumProperty.create("third", EnumThird.class);
	EnumProperty<EnumSide> SIDE_EXTENDED = EnumProperty.create("side", EnumSide.class);
	EnumProperty<EnumSide> SIDE = EnumProperty.create("side", EnumSide.class, side -> side != EnumSide.MIDDLE && side != EnumSide.SINGLE);

	static ActionResultType checkHoldingBrush(World world, PlayerEntity player, Runnable callbackBrush, Runnable callbackNoBrush) {
		if (player.getHeldItemMainhand().getItem() == Registration.BRUSH.get()) {
			if (!world.isRemote) {
				callbackBrush.run();
			}
			return ActionResultType.SUCCESS;
		} else {
			if (callbackNoBrush == null) {
				return ActionResultType.FAIL;
			} else {
				if (!world.isRemote) {
					callbackNoBrush.run();
					return ActionResultType.CONSUME;
				} else {
					return ActionResultType.SUCCESS;
				}
			}
		}
	}

	static ActionResultType checkHoldingBrush(World world, PlayerEntity player, Runnable callbackBrush) {
		return checkHoldingBrush(world, player, callbackBrush, null);
	}

	static VoxelShape getVoxelShapeByDirection(double x1, double y1, double z1, double x2, double y2, double z2, Direction facing) {
		switch (facing) {
			case NORTH:
				return Block.makeCuboidShape(x1, y1, z1, x2, y2, z2);
			case EAST:
				return Block.makeCuboidShape(16 - z2, y1, x1, 16 - z1, y2, x2);
			case SOUTH:
				return Block.makeCuboidShape(16 - x2, y1, 16 - z2, 16 - x1, y2, 16 - z1);
			case WEST:
				return Block.makeCuboidShape(z1, y1, 16 - x2, z2, y2, 16 - x1);
			default:
				return VoxelShapes.fullCube();
		}
	}

	static boolean isReplaceable(BlockItemUseContext context, Direction direction, int totalLength) {
		for (int i = 0; i < totalLength; i++) {
			if (!context.getWorld().getBlockState(context.getPos().offset(direction, i)).isReplaceable(context)) {
				return false;
			}
		}
		return true;
	}

	static void onBreakCreative(World world, PlayerEntity player, BlockPos pos) {
		if (!world.isRemote && player.isCreative()) {
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 35);
			final BlockState state = world.getBlockState(pos);
			world.playEvent(player, 2001, pos, Block.getStateId(state));
		}
	}

	static Direction getSideDirection(BlockState state) {
		final Direction facing = IBlock.getStatePropertySafe(state, HorizontalBlock.HORIZONTAL_FACING);
		return IBlock.getStatePropertySafe(state, SIDE) == EnumSide.LEFT ? facing.rotateY() : facing.rotateYCCW();
	}

	static <T extends Comparable<T>> T getStatePropertySafe(IWorld world, BlockPos pos, Property<T> property) {
		return getStatePropertySafe(world.getBlockState(pos), property);
	}

	@SuppressWarnings("unchecked")
	static <T extends Comparable<T>> T getStatePropertySafe(BlockState state, Property<T> property) {
		return state.hasProperty(property) ? state.get(property) : (T) property.getAllowedValues().toArray()[0];
	}

	enum EnumThird implements IStringSerializable {

		LOWER("lower"), MIDDLE("middle"), UPPER("upper");
		private final String name;

		EnumThird(String name) {
			this.name = name;
		}

		@Override
		public String getString() {
			return name;
		}
	}

	enum EnumSide implements IStringSerializable {

		LEFT("left"), RIGHT("right"), MIDDLE("middle"), SINGLE("single");
		private final String name;

		EnumSide(String name) {
			this.name = name;
		}

		@Override
		public String getString() {
			return name;
		}

	}

}
