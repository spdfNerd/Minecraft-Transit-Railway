package com.spdfnerd.mtr.entity;

import com.spdfnerd.mtr.data.Train;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class EntityMTrainMini extends EntityTrainBase {

	public EntityMTrainMini(World world, double x, double y, double z) {
		super(Registration.M_TRAIN_MINI.get(), world, x, y, z);
	}

	public EntityMTrainMini(EntityType<?> type, World world) {
		super(type, world);
	}

	@Override
	protected Train.TrainType getTrainType() {
		return Train.TrainType.M_TRAIN_MINI;
	}

}
