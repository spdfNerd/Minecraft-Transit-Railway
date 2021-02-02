package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.data.Platform;
import com.spdfnerd.mtr.data.RailwayData;
import com.spdfnerd.mtr.entity.EntityTrainBase;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.RailShape;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockPlatformRail extends AbstractRailBlock {


	public static final EnumProperty<RailShape> SHAPE = EnumProperty.create("shape", RailShape.class,
			(shape) -> shape == RailShape.NORTH_SOUTH || shape == RailShape.EAST_WEST);

	public BlockPlatformRail(Properties properties) {
		super(true, properties);
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		super.onBlockAdded(state, world, pos, oldState, notify);
		updateRailwayData(world, pos);
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		super.neighborChanged(state, world, pos, blockIn, fromPos, isMoving);
		updateRailwayData(world, pos);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isRemote && entity instanceof EntityTrainBase) {
			final EntityTrainBase entityTrainBase = (EntityTrainBase) entity;
			if (entityTrainBase.inTrain(Vector3d.copy(pos))) {
				final RailShape railShape = IBlock.getStatePropertySafe(state, SHAPE);
				for (int x = -2; x <= 2; x++) {
					for (int y = 0; y <= 2; y++) {
						updateStationCoolDown(world, (railShape == RailShape.NORTH_SOUTH ? pos.east(x) : pos.north(x)).up(y), entityTrainBase.getDoorValue());
					}
				}
			}
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		return IBlock.checkHoldingBrush(world, player, () -> {
			final RailwayData railwayData = RailwayData.getInstance(world);
			if (railwayData != null) {
				BlockPos platformPos = getPlatformPos1(world, pos);
//				PacketTrainDataGuiServer.openPlatformScreenS2C(player, railwayData.getStations(), railwayData.getPlatforms(world), railwayData.getRoutes(), platformPos);
			}
		}, () -> {
			if (!(player.getHeldItemMainhand().getItem() == Registration.PLATFORM_RAIL.get().asItem())) {
				final RailwayData railwayData = RailwayData.getInstance(world);
				if (railwayData != null) {
					BlockPos platformPos = getPlatformPos1(world, pos);
//					PacketTrainDataGuiServer.openScheduleScreenS2C(player, railwayData.getStations(), railwayData.getPlatforms(world), railwayData.getRoutes(), platformPos);
				}
			}
		});
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.empty();
	}

	@Override
	public boolean isIn(ITag<Block> tag) {
		return tag == BlockTags.RAILS;
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
		return state;
	}

	@Override
	public Property<RailShape> getShapeProperty() {
		return SHAPE;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(SHAPE);
	}

	@Override
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.BLOCK;
	}

	@Override
	protected BlockState getUpdatedState(World worldIn, BlockPos pos, BlockState state, boolean placing) {
		try {
			return super.getUpdatedState(worldIn, pos, state, placing);
		} catch (Exception e) {
			return state;
		}
	}

	public static Platform createNewPlatform(IWorld world, BlockPos pos) {
		final BlockState state = world.getBlockState(pos);
		if (!(state.getBlock() instanceof BlockPlatformRail)) {
			return null;
		}

		final Direction scanDirection;
		final Direction.Axis axis;

		if (IBlock.getStatePropertySafe(state, SHAPE) == RailShape.NORTH_SOUTH) {
			scanDirection = Direction.SOUTH;
			axis = Direction.Axis.Z;
		} else {
			scanDirection = Direction.EAST;
			axis = Direction.Axis.X;
		}

		int length = 1;
		final BlockPos startPos = getPlatformPos1(world, pos);
		while (world.getBlockState(startPos.offset(scanDirection, length)).equals(state)) {
			length++;
		}

		return new Platform(startPos, axis, length - 1);
	}

	public static BlockPos getPlatformPos1(IWorld world, BlockPos pos) {
		final RailShape railShape = IBlock.getStatePropertySafe(world, pos, SHAPE);
		final Direction moveDirection = railShape == RailShape.NORTH_SOUTH ? Direction.NORTH : Direction.WEST;
		while (world.getBlockState(pos).getBlock() instanceof BlockPlatformRail && IBlock.getStatePropertySafe(world, pos, SHAPE) == railShape) {
			pos = pos.offset(moveDirection);
		}
		return pos.offset(moveDirection.getOpposite());
	}

	private static void updateRailwayData(World world, BlockPos pos) {
		if (!world.isRemote) {
			RailwayData railwayData = RailwayData.getInstance(world);
			if (railwayData != null) {
				railwayData.checkPlatformPos(world, pos);
			}
		}
	}

	private static void updateStationCoolDown(World world, BlockPos pos, int doorValue) {
		BlockState state = world.getBlockState(pos);
		if (state.getBlock() instanceof BlockPSDAPGDoorBase) {
			world.setBlockState(pos, state.with(BlockPSDAPGDoorBase.OPEN, doorValue));
		}
	}

}
