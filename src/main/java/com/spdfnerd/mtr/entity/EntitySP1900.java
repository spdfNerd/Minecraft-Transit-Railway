package com.spdfnerd.mtr.entity;

import com.spdfnerd.mtr.data.Train;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class EntitySP1900 extends EntityTrainBase {

	public EntitySP1900(World world, double x, double y, double z) {
		super(Registration.SP1900.get(), world, x, y, z);
	}

	public EntitySP1900(EntityType<?> type, World world) {
		super(type, world);
	}

	@Override
	protected Train.TrainType getTrainType() {
		return Train.TrainType.SP1900;
	}

}
