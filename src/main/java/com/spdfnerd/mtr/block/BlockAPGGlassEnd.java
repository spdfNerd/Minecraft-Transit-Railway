package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.item.Item;

public class BlockAPGGlassEnd extends BlockPSDAPGGlassEndBase {

	@Override
	public Item asItem() {
		return Registration.APG_GLASS_END_ITEM.get();
	}

}
