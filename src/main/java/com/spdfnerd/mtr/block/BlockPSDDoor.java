package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.item.Item;

public class BlockPSDDoor extends BlockPSDAPGDoorBase {

	@Override
	public Item asItem() {
		return Registration.PSD_DOOR_ITEM.get();
	}

}
