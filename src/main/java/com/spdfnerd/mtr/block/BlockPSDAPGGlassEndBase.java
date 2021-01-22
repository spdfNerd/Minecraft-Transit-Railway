package com.spdfnerd.mtr.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public abstract class BlockPSDAPGGlassEndBase extends BlockPSDAPGGlassBase {

	public static final EnumProperty<EnumPSDAPGGlassEndSide> TOUCHING_LEFT = EnumProperty.create("touching_left", EnumPSDAPGGlassEndSide.class);
	public static final EnumProperty<EnumPSDAPGGlassEndSide> TOUCHING_RIGHT = EnumProperty.create("touching_right", EnumPSDAPGGlassEndSide.class);

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		BlockState superState = super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
		if (superState.getBlock() == Blocks.AIR) {
			return superState;
		} else {
			final Direction direction = IBlock.getStatePropertySafe(state, HORIZONTAL_FACING);
			final EnumPSDAPGGlassEndSide touchingLeft = getSideEnd(world, currentPos, direction.rotateYCCW());
			final EnumPSDAPGGlassEndSide touchingRight = getSideEnd(world, currentPos, direction.rotateY());
			return superState.with(TOUCHING_LEFT, touchingLeft).with(TOUCHING_RIGHT, touchingRight);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		VoxelShape superShape = super.getShape(state, world, pos, context);
		final boolean leftAir = IBlock.getStatePropertySafe(state, TOUCHING_LEFT) == EnumPSDAPGGlassEndSide.AIR;
		final boolean rightAir = IBlock.getStatePropertySafe(state, TOUCHING_RIGHT) == EnumPSDAPGGlassEndSide.AIR;
		final Direction facing = IBlock.getStatePropertySafe(state, HORIZONTAL_FACING);
		final double height = isAPG() && IBlock.getStatePropertySafe(state, HALF) == SlabType.TOP ? 9 : 16;

		if (facing == Direction.NORTH && leftAir || facing == Direction.SOUTH && rightAir) {
			superShape = VoxelShapes.or(superShape, Block.makeCuboidShape(0, 0, 0, 4, height, 16));
		}
		if (facing == Direction.EAST && leftAir || facing == Direction.WEST && rightAir) {
			superShape = VoxelShapes.or(superShape, Block.makeCuboidShape(0, 0, 0, 16, height, 4));
		}
		if (facing == Direction.SOUTH && leftAir || facing == Direction.NORTH && rightAir) {
			superShape = VoxelShapes.or(superShape, Block.makeCuboidShape(12, 0, 0, 16, height, 16));
		}
		if (facing == Direction.WEST && leftAir || facing == Direction.EAST && rightAir) {
			superShape = VoxelShapes.or(superShape, Block.makeCuboidShape(0, 0, 12, 16, height, 16));
		}

		return superShape;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, HALF, SIDE_EXTENDED, TOUCHING_LEFT, TOUCHING_RIGHT);
	}

	private EnumPSDAPGGlassEndSide getSideEnd(IBlockReader world, BlockPos pos, Direction offset) {
		final BlockPos checkPos = pos.offset(offset);
		if (world.getBlockState(checkPos).getBlock() instanceof BlockPSDAPGDoorBase) {
			return EnumPSDAPGGlassEndSide.DOOR;
		} else if (world.getBlockState(checkPos).getBlock() instanceof BlockPSDAPGBase) {
			return EnumPSDAPGGlassEndSide.NONE;
		} else {
			return EnumPSDAPGGlassEndSide.AIR;
		}
	}

	public enum EnumPSDAPGGlassEndSide implements IStringSerializable {

		AIR("air"), DOOR("door"), NONE("none");
		private final String name;

		EnumPSDAPGGlassEndSide(String nameIn) {
			name = nameIn;
		}

		@Override
		public String getString() {
			return name;
		}

	}
}
