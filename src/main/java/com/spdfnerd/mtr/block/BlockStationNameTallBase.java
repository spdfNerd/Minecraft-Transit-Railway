package com.spdfnerd.mtr.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BlockStationNameTallBase extends BlockStationNameBase implements IBlock {

	public static final BooleanProperty METAL = BooleanProperty.create("metal");

	public BlockStationNameTallBase() {
		super(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(2).setOpaque((state, reader, pos) -> false));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		return IBlock.checkHoldingBrush(world, player, () -> {
			final boolean isWhite = IBlock.getStatePropertySafe(state, COLOUR) == 0;
			final int newColorProperty = isWhite ? 2 : 0;
			final boolean newMetalProperty = isWhite == IBlock.getStatePropertySafe(state, METAL);

			updateProperties(world, pos, newMetalProperty, newColorProperty);
			switch (IBlock.getStatePropertySafe(state, THIRD)) {
				case LOWER:
					updateProperties(world, pos.up(), newMetalProperty, newColorProperty);
					updateProperties(world, pos.up(2), newMetalProperty, newColorProperty);
					break;
				case MIDDLE:
					updateProperties(world, pos.down(), newMetalProperty, newColorProperty);
					updateProperties(world, pos.up(), newMetalProperty, newColorProperty);
					break;
				case UPPER:
					updateProperties(world, pos.down(), newMetalProperty, newColorProperty);
					updateProperties(world, pos.down(2), newMetalProperty, newColorProperty);
					break;
			}
		});
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if ((facing == Direction.UP && IBlock.getStatePropertySafe(state, THIRD) != EnumThird.UPPER || facing == Direction.DOWN && IBlock.getStatePropertySafe(state, THIRD) != EnumThird.LOWER) && !(facingState.getBlock() == this)) {
			return Blocks.AIR.getDefaultState();
		} else {
			return state;
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		switch (IBlock.getStatePropertySafe(state, THIRD)) {
			case MIDDLE:
				IBlock.onBreakCreative(world, player, pos.down());
				break;
			case UPPER:
				IBlock.onBreakCreative(world, player, pos.down(2));
				break;
		}
		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		if (!world.isRemote) {
			final Direction facing = IBlock.getStatePropertySafe(state, HORIZONTAL_FACING);
			world.setBlockState(pos.up(), getDefaultState().with(HORIZONTAL_FACING, facing).with(METAL, true).with(THIRD, EnumThird.MIDDLE), 3);
			world.setBlockState(pos.up(2), getDefaultState().with(HORIZONTAL_FACING, facing).with(METAL, true).with(THIRD, EnumThird.UPPER), 3);
			world.func_230547_a_(pos, Blocks.AIR);
			state.updateNeighbours(world, pos, 3);
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(COLOUR, HORIZONTAL_FACING, METAL, THIRD);
	}

	protected static Tuple<Integer, Integer> getBounds(BlockState state) {
		final EnumThird third = IBlock.getStatePropertySafe(state, THIRD);
		final int start, end;
		switch (third) {
			case LOWER:
				start = 10;
				end = 16;
				break;
			case UPPER:
				start = 0;
				end = 8;
				break;
			default:
				start = 0;
				end = 16;
				break;
		}
		return new Tuple<>(start, end);
	}

	private static void updateProperties(World world, BlockPos pos, boolean metalProperty, int colorProperty) {
		world.setBlockState(pos, world.getBlockState(pos).with(COLOUR, colorProperty).with(METAL, metalProperty));
	}

}
