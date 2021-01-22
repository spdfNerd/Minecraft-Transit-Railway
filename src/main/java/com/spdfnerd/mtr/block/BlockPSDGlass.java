package com.spdfnerd.mtr.block;

import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.item.Item;

public class BlockPSDGlass extends BlockPSDAPGGlassBase {

	@Override
	public Item asItem() {
		return Registration.PSD_GLASS_END_ITEM.get();
	}

}
