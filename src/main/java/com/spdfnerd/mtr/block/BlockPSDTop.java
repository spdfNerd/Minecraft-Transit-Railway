package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.setup.Registration;
import com.spdfnerd.mtr.tileentity.PSDTopTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPSDTop extends HorizontalBlock implements ITileEntityProvider, IBlock, IPropagateBlock {

	public static final EnumProperty<EnumDoorLight> DOOR_LIGHT = EnumProperty.create("door_light", EnumDoorLight.class);
	public static final BooleanProperty AIR_LEFT = BooleanProperty.create("air_left");
	public static final BooleanProperty AIR_RIGHT = BooleanProperty.create("air_right");

	public BlockPSDTop() {
		super(AbstractBlock.Properties.create(Material.IRON, MaterialColor.QUARTZ).setRequiresTool().hardnessAndResistance(2f).setLightLevel(value -> 15)
				.setOpaque((state, reader, pos) -> false));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		return IBlock.checkHoldingBrush(world, player, () -> {
			world.setBlockState(pos, state.func_235896_a_(PROPAGATE_PROPERTY));
			propagate(world, pos, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING).rotateY());
			propagate(world, pos, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING).rotateYCCW());
		});
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state;
	}

	@Override
	public Item asItem() {
		return Registration.PSD_GLASS_ITEM.get();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
		return new ItemStack(asItem());
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		final Block blockDown = world.getBlockState(pos.down()).getBlock();
		if (blockDown instanceof BlockPSDAPGBase) {
			blockDown.onBlockHarvested(world, pos.down(), world.getBlockState(pos.down()), player);
			world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
		}
		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public void onExplosionDestroy(World world, BlockPos pos, Explosion explosionIn) {
		onBlockHarvested(world, pos, null, null);
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.DOWN && !(facingState.getBlock() instanceof BlockPSDAPGBase)) {
			return Blocks.AIR.getDefaultState();
		} else {
			return getActualState(world, currentPos);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (IBlock.getStatePropertySafe(state, AIR_LEFT) || IBlock.getStatePropertySafe(state, AIR_RIGHT)) {
			return VoxelShapes.fullCube();
		} else {
			return IBlock.getVoxelShapeByDirection(0d, 0d, 0d, 16d, 16d, 6d, IBlock.getStatePropertySafe(state, HORIZONTAL_FACING));
		}
	}

	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOOR_LIGHT, HORIZONTAL_FACING, SIDE_EXTENDED, AIR_LEFT, AIR_RIGHT, PROPAGATE_PROPERTY);
	}

	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new PSDTopTileEntity();
	}

	public static BlockState getActualState(IWorld world, BlockPos pos) {
		EnumDoorLight doorLight = EnumDoorLight.NONE;
		Direction facing = Direction.NORTH;
		EnumSide side = EnumSide.SINGLE;
		boolean airLeft = false, airRight = false;

		final BlockState stateBelow = world.getBlockState(pos.down());
		if (stateBelow.getBlock() instanceof BlockPSDAPGBase) {
			if (stateBelow.getBlock() instanceof BlockPSDAPGDoorBase) {
				doorLight = IBlock.getStatePropertySafe(stateBelow, BlockPSDAPGDoorBase.OPEN) > 0 ? EnumDoorLight.ON : EnumDoorLight.OFF;
				side = IBlock.getStatePropertySafe(stateBelow, SIDE);
			} else {
				side = IBlock.getStatePropertySafe(stateBelow, SIDE_EXTENDED);
			}

			if (stateBelow.getBlock() instanceof BlockPSDAPGGlassEndBase) {
				if (IBlock.getStatePropertySafe(stateBelow, BlockPSDAPGGlassEndBase.TOUCHING_LEFT) == BlockPSDAPGGlassEndBase.EnumPSDAPGGlassEndSide.AIR) {
					airLeft = true;
				}
				if (IBlock.getStatePropertySafe(stateBelow, BlockPSDAPGGlassEndBase.TOUCHING_RIGHT) == BlockPSDAPGGlassEndBase.EnumPSDAPGGlassEndSide.AIR) {
					airRight = true;
				}
			}

			facing = IBlock.getStatePropertySafe(stateBelow, HORIZONTAL_FACING);
		}

		final BlockState oldState = world.getBlockState(pos);
		return (oldState.getBlock() instanceof BlockPSDTop ? oldState : Registration.PSD_TOP.get().getDefaultState()).with(DOOR_LIGHT, doorLight).with(HORIZONTAL_FACING,
				facing).with(SIDE_EXTENDED, side).with(AIR_LEFT, airLeft).with(AIR_RIGHT, airRight);
	}

	public enum EnumDoorLight implements IStringSerializable {

		ON("on"), OFF("off"), NONE("none");
		private final String name;

		EnumDoorLight(String nameIn) {
			name = nameIn;
		}

		@Override
		public String getString() {
			return name;
		}

	}

}
