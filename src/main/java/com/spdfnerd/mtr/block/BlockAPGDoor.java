package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.item.Item;

public class BlockAPGDoor extends BlockPSDAPGDoorBase {

	@Override
	public Item asItem() {
		return Registration.APG_DOOR_ITEM.get();
	}

}
