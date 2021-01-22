package com.spdfnerd.mtr.entity;

import com.spdfnerd.mtr.data.Train;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class EntityLightRail1 extends EntityTrainBase {

	public EntityLightRail1(World world, double x, double y, double z) {
		super(Registration.LIGHT_RAIL_1.get(), world, x, y, z);
	}

	public EntityLightRail1(EntityType<?> type, World world) {
		super(type, world);
	}

	@Override
	protected Train.TrainType getTrainType() {
		return null; // Train.TrainType.LIGHT_RAIL_1;
	}

}
