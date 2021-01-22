package com.spdfnerd.mtr.block;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class BlockStationColor extends Block {

	public BlockStationColor(Properties properties) {
		super(properties);
	}

	@Override
	public String getTranslationKey() {
		return super.getTranslationKey().replace("block.mtr.station_color_", "block.minecraft.");
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("tooltip.mtr.station_color").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.GRAY))));
	}

}
