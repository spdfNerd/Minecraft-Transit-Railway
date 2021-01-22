package com.spdfnerd.mtr.entity;

import com.spdfnerd.mtr.block.BlockPSDAPGBase;
import com.spdfnerd.mtr.block.BlockPSDAPGDoorBase;
import com.spdfnerd.mtr.block.BlockPlatform;
import com.spdfnerd.mtr.data.Pos3f;
import com.spdfnerd.mtr.data.RailwayData;
import com.spdfnerd.mtr.data.Train;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("EntityConstructor")
public abstract class EntityTrainBase extends Entity {

	public int stationCoolDown;

	private float prevYaw;
	private int clientRotationIncrements;
	private double clientX;
	private double clientY;
	private double clientZ;
	private double clientYaw;
	private double clientPitch;
	private int killTimer;

	private final Map<Integer, Pos3f> passengerOffsets;

	private static final DataParameter<Float> YAW = EntityDataManager.createKey(EntityTrainBase.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> PITCH = EntityDataManager.createKey(EntityTrainBase.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> DOOR_VALUE = EntityDataManager.createKey(EntityTrainBase.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> DOOR_LEFT = EntityDataManager.createKey(EntityTrainBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> DOOR_RIGHT = EntityDataManager.createKey(EntityTrainBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_END_1_HEAD = EntityDataManager.createKey(EntityTrainBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_END_2_HEAD = EntityDataManager.createKey(EntityTrainBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> HEAD_1_IS_FRONT = EntityDataManager.createKey(EntityTrainBase.class, DataSerializers.BOOLEAN);

	protected EntityTrainBase(EntityType<?> type, World world) {
		super(type, world);
		setNoGravity(true);
		noClip = true;
		passengerOffsets = new HashMap<>();
	}

	protected EntityTrainBase(EntityType<?> type, World world, double x, double y, double z) {
		this(type, world);
		setPosition(x, y, z);
		setVelocity(0f, 0f, 0f);
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
	}

	@Override
	public void tick() {
		if (world.isRemote) {
			final float dataManagerYaw = dataManager.get(YAW);
			final float dataManagerPitch = dataManager.get(PITCH);

			if (clientRotationIncrements > 0) {
				final double x = getPosX() + (clientX - getPosX()) / clientRotationIncrements;
				final double y = getPosY() + (clientY - getPosY()) / clientRotationIncrements;
				final double z = getPosZ() + (clientZ - getPosZ()) / clientRotationIncrements;

				rotationYaw = (float) (dataManagerYaw + MathHelper.wrapDegrees(clientYaw - dataManagerYaw) / clientRotationIncrements);
				rotationPitch = (float) (dataManagerPitch + (clientPitch - dataManagerPitch) / clientRotationIncrements);
				--clientRotationIncrements;
				setPosition(x, y, z);
			} else {
				rotationYaw = dataManagerYaw;
				rotationPitch = dataManagerPitch;
				recenterBoundingBox();
			}

			setRotation(rotationYaw, rotationPitch);
		} else {
			if (stationCoolDown > 0) {
				doBlockCollisions();
				mountCollidingLivingEntities();
			}

			if (stationCoolDown < RailwayData.TRAIN_STOP_TIME || stationCoolDown >= RailwayData.STATION_COOL_DOWN - RailwayData.TRAIN_STOP_TIME) {
				setDoorValue(0);
			} else if (stationCoolDown < RailwayData.TRAIN_STOP_TIME + BlockPSDAPGDoorBase.MAX_OPEN_VALUE) {
				setDoorValue(stationCoolDown - RailwayData.TRAIN_STOP_TIME);
			} else if (stationCoolDown >= RailwayData.STATION_COOL_DOWN - RailwayData.TRAIN_STOP_TIME - BlockPSDAPGDoorBase.MAX_OPEN_VALUE) {
				setDoorValue(RailwayData.STATION_COOL_DOWN - RailwayData.TRAIN_STOP_TIME - stationCoolDown);
			} else {
				setDoorValue(BlockPSDAPGDoorBase.MAX_OPEN_VALUE);
			}

			if (getDoorValue() > 0) {
				final int width = getTrainType().getWidth();
				final Vector3d offsetVec = new Vector3d(width, 0, 0).rotateYaw((float) Math.toRadians(rotationYaw));
				final BlockPos checkPosLeft = new BlockPos(getPositionVec().add(offsetVec));
				final BlockPos checkPosRight = new BlockPos(getPositionVec().subtract(offsetVec));
				setDoors(isPlatformOrDoor(checkPosLeft) || isPlatformOrDoor(checkPosLeft.up()) || isPlatformOrDoor(checkPosLeft.down()), isPlatformOrDoor(checkPosRight) || isPlatformOrDoor(checkPosRight.up()) || isPlatformOrDoor(checkPosRight.down()));
			} else {
				setDoors(false, false);
			}

			killTimer++;
			if (killTimer > 2) {
				onKillCommand();
			}
		}
	}

	@Override
	public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch) {
		super.setPositionAndRotation(x, y, z, yaw, pitch);
		dataManager.set(YAW, yaw);
		dataManager.set(PITCH, pitch);
		killTimer = 0;
	}

	@Override
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
		clientX = x;
		clientY = y;
		clientZ = z;
		clientYaw = yaw;
		clientPitch = pitch;
		clientRotationIncrements = posRotationIncrements + 2;
	}

	@Override
	public void updatePassenger(Entity passenger) {
		if (passenger instanceof LivingEntity) {
			final LivingEntity mob = (LivingEntity) passenger;
			final int entityId = passenger.getEntityId();

			if (passengerOffsets.containsKey(passenger.getEntityId())) {
				final Pos3f offset = passengerOffsets.get(entityId);
				offset.add(new Pos3f(mob.moveStrafing / 5, 0, mob.moveForward / 5).rotateY((float) Math.toRadians(-rotationYaw - passenger.rotationYaw)));

				final float length = getTrainType().getLength() / 2F;
				final float width = getTrainType().getWidth() / 2F;

				passengerOffsets.put(entityId, new Pos3f(MathHelper.clamp(offset.getX(), -width, width), 0, MathHelper.clamp(offset.getZ(), -length, length)));
			} else {
				passengerOffsets.put(entityId, new Pos3f(0, 0, 0));
			}

			final Pos3f offset = passengerOffsets.get(entityId).rotateX((float) Math.toRadians(rotationPitch)).rotateY((float) Math.toRadians(rotationYaw));
			passenger.setPosition(getPosX() + offset.getX(), getPosY() + getMountedYOffset() + offset.getY(), getPosZ() + offset.getZ());
		} else {
			passenger.setPosition(getPosX(), getPosY() + getMountedYOffset(), getPosZ());
		}

		final float yawChange = MathHelper.wrapDegrees(prevYaw - rotationYaw);
		passenger.rotationYaw += yawChange;
		passenger.setRotationYawHead(passenger.getRotationYawHead() + yawChange);
		prevYaw = rotationYaw;
	}

	@Override
	public Vector3d func_230268_c_(LivingEntity passenger) {
		final Vector3d offsetVec = new Vector3d(1, 0, 0).rotateYaw((float) Math.toRadians(rotationYaw));
		final int[] checkHeights = {0, -1, 1};

		if (getDoorValue() > 0 && (getDoorLeft() || getDoorRight())) {
			for (final int height : checkHeights) {
				for (int offset = 1; offset <= 3; offset++) {
					final Vector3d checkPos = passenger.getPositionVec().add(offsetVec.mul(offset * (getDoorLeft() ? 1 : -1), offset * (getDoorLeft() ? 1 :
							-1), offset * (getDoorLeft() ? 1 : -1)).subtract(0, height, 0));
					if (canDismountHere(checkPos)) {
						return checkPos;
					}
				}
			}
		}

		final int[] checkOffsets = {1, -1, 2, -2, 3, -3};
		for (final int height : checkHeights) {
			for (final int offset : checkOffsets) {
				final Vector3d checkPos = passenger.getPositionVec().add(offsetVec.mul(offset, offset, offset).subtract(0, height, 0));
				if (canDismountHere(checkPos)) {
					return checkPos;
				}
			}
		}

		return super.func_230268_c_(passenger);
	}

	@Override
	public double getMountedYOffset() {
		return 1;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return new SSpawnObjectPacket(this);
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		return getPassengers().size() < getTrainType().getCapacity();
	}

	@Override
	protected void readAdditional(CompoundNBT tag) {
	}

	@Override
	protected void writeAdditional(CompoundNBT tag) {
	}

	@Override
	protected void registerData() {
		dataManager.register(YAW, 0F);
		dataManager.register(PITCH, 0F);
		dataManager.register(DOOR_VALUE, 0);
		dataManager.register(DOOR_LEFT, false);
		dataManager.register(DOOR_RIGHT, false);
		dataManager.register(IS_END_1_HEAD, true);
		dataManager.register(IS_END_2_HEAD, true);
		dataManager.register(HEAD_1_IS_FRONT, true);
	}

	public boolean inTrain(Vector3d pos) {
		final float length = getTrainType().getLength() / 2F;
		final float widthBigger = getTrainType().getWidth() / 2F + 0.5F;
		final Vector3d posRelative =
				pos.subtract(getPositionVec()).rotatePitch((float) Math.toRadians(-rotationPitch)).rotateYaw((float) Math.toRadians(-rotationYaw));
		return RailwayData.isBetween(posRelative.x, -widthBigger, widthBigger) && RailwayData.isBetween(posRelative.y, -widthBigger, widthBigger) && RailwayData.isBetween(posRelative.z, -length, length);
	}

	protected void mountCollidingLivingEntities() {
		if (!world.isRemote && getDoorValue() > 0) {
			final float length = getTrainType().getLength() / 2F;
			world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(getPositionVec().subtract(length, length, length), getPositionVec().add(length, length, length))).stream()
					.filter(Entity::isPassenger).forEach(entity -> {
				if (inTrain(entity.getPositionVec())) {
					final Vector3d passengerOffsetRotated =
							entity.getPositionVec().subtract(getPositionVec()).rotateYaw((float) Math.toRadians(-rotationYaw)).rotatePitch((float) Math.toRadians(-rotationPitch));
					passengerOffsets.put(entity.getEntityId(), new Pos3f(0, 0, (float) passengerOffsetRotated.z));
					entity.startRiding(this);
				}
			});
		}
	}

	public void setDoorValue(int doorValue) {
		dataManager.set(DOOR_VALUE, doorValue);
	}

	public int getDoorValue() {
		return dataManager.get(DOOR_VALUE);
	}

	public void setDoors(boolean doorLeft, boolean doorRight) {
		dataManager.set(DOOR_LEFT, doorLeft);
		dataManager.set(DOOR_RIGHT, doorRight);
	}

	public boolean getDoorLeft() {
		return dataManager.get(DOOR_LEFT);
	}

	public boolean getDoorRight() {
		return dataManager.get(DOOR_RIGHT);
	}

	public void setIsEndHead(boolean isEnd1Head, boolean isEnd2Head) {
		dataManager.set(IS_END_1_HEAD, isEnd1Head);
		dataManager.set(IS_END_2_HEAD, isEnd2Head);
	}

	public boolean getIsEnd1Head() {
		return dataManager.get(IS_END_1_HEAD);
	}

	public boolean getIsEnd2Head() {
		return dataManager.get(IS_END_2_HEAD);
	}

	public void setHead1IsFront(boolean head1IsFront) {
		dataManager.set(HEAD_1_IS_FRONT, head1IsFront);
	}

	public boolean getHead1IsFront() {
		return dataManager.get(HEAD_1_IS_FRONT);
	}

	protected abstract Train.TrainType getTrainType();

	private boolean isPlatformOrDoor(BlockPos pos) {
		final Block block = world.getBlockState(pos).getBlock();
		return block instanceof BlockPlatform || block instanceof BlockPSDAPGBase;
	}

	private boolean canDismountHere(Vector3d vector3d) {
		final BlockPos pos = new BlockPos(vector3d);
		final boolean flatTop = world.getBlockState(pos.down()).isSolidSide(world, pos.down(), Direction.UP);
		final boolean middleNotSolid = !world.getBlockState(pos).isOpaqueCube(world, pos);
		final boolean topNotSolid = !world.getBlockState(pos.up()).isOpaqueCube(world, pos.up());
		return flatTop && middleNotSolid && topNotSolid;
	}

}
