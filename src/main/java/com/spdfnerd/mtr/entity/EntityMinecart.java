package com.spdfnerd.mtr.entity;

import com.spdfnerd.mtr.data.Train;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EntityMinecart extends EntityTrainBase {

	public EntityMinecart(World world, double x, double y, double z) {
		super(Registration.MINECART.get(), world, x, y, z);
	}

	public EntityMinecart(EntityType<?> type, World world) {
		super(type, world);
	}

	@Override
	public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
		if (player.isSecondaryUseActive()) {
			return ActionResultType.PASS;
		} else if (isBeingRidden()) {
			return ActionResultType.PASS;
		} else if (!world.isRemote) {
			return player.startRiding(this) ? ActionResultType.CONSUME : ActionResultType.PASS;
		} else {
			return ActionResultType.SUCCESS;
		}
	}

	@Override
	public boolean canBeCollidedWith() {
		return !removed;
	}

	@Override
	public double getMountedYOffset() {
		return 0;
	}

	@Override
	protected Train.TrainType getTrainType() {
		return Train.TrainType.MINECART;
	}

	@Override
	protected void mountCollidingLivingEntities() {
	}

}
