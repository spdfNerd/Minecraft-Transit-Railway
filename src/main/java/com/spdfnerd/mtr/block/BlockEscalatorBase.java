package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public abstract class BlockEscalatorBase extends HorizontalBlock implements IBlock {

	public static final EnumProperty<EnumEscalatorOrientation> ORIENTATION = EnumProperty.create("orientation", EnumEscalatorOrientation.class);

	protected BlockEscalatorBase() {
		super(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2f).setOpaque((state, reader, pos) -> false));
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if (getSideDirection(state) == facing && !(facingState.getBlock() == this)) {
			return Blocks.AIR.getDefaultState();
		} else {
			return state.with(ORIENTATION, getOrientation(world, currentPos, state));
		}
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		final EnumEscalatorOrientation orientation = getOrientation(world, pos, state);

		if (orientation == EnumEscalatorOrientation.SLOPE || orientation == EnumEscalatorOrientation.TRANSITION_TOP) {
			VoxelShape box = VoxelShapes.empty();
			for (int step = 0; step < 16; step++) {
				box = VoxelShapes.or(box, IBlock.getVoxelShapeByDirection(0, 0, 0, 16, step, 15 - step, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING)));
			}
			return box;
		} else {
			return VoxelShapes.fullCube();
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state;
	}

	@Override
	public Item asItem() {
		return Registration.ESCALATOR_ITEM.get();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
		return new ItemStack(asItem());
	}

	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	protected final EnumEscalatorOrientation getOrientation(IBlockReader world, BlockPos pos, BlockState state) {
		final Direction facing = IBlock.getStatePropertySafe(state, HORIZONTAL_FACING);

		final BlockPos posAhead = pos.offset(facing);
		final BlockPos posBehind = pos.offset(facing, -1);

		final boolean isAhead = world.getBlockState(posAhead).getBlock() == this;
		final boolean isAheadUp = world.getBlockState(posAhead.up()).getBlock() == this;

		final boolean isBehind = world.getBlockState(posBehind).getBlock() == this;
		final boolean isBehindDown = world.getBlockState(posBehind.down()).getBlock() == this;

		if (isAhead && isBehind) {
			return EnumEscalatorOrientation.FLAT;
		} else if (isAheadUp && isBehindDown) {
			return EnumEscalatorOrientation.SLOPE;
		} else if (isAheadUp && isBehind) {
			return EnumEscalatorOrientation.TRANSITION_BOTTOM;
		} else if (isAhead && isBehindDown) {
			return EnumEscalatorOrientation.TRANSITION_TOP;
		} else if (isBehind) {
			return EnumEscalatorOrientation.LANDING_TOP;
		} else {
			return EnumEscalatorOrientation.LANDING_BOTTOM;
		}
	}

	private Direction getSideDirection(BlockState state) {
		final Direction facing = IBlock.getStatePropertySafe(state, HORIZONTAL_FACING);
		return IBlock.getStatePropertySafe(state, SIDE) == EnumSide.RIGHT ? facing.rotateYCCW() : facing.rotateY();
	}

	protected enum EnumEscalatorOrientation implements IStringSerializable {

		LANDING_BOTTOM("landing_bottom"), LANDING_TOP("landing_top"), FLAT("flat"), SLOPE("slope"), TRANSITION_BOTTOM("transition_bottom"), TRANSITION_TOP("transition_top");
		private final String name;

		EnumEscalatorOrientation(String nameIn) {
			name = nameIn;
		}

		@Override
		public String getString() {
			return name;
		}

	}

}
