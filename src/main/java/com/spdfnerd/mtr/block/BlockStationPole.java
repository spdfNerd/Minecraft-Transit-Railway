package com.spdfnerd.mtr.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class BlockStationPole extends Block {

	public BlockStationPole(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return getStationPoleShape();
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.mtr.station_color").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.GRAY))));
	}

	public static VoxelShape getStationPoleShape() {
		return Block.makeCuboidShape(6, 0, 6, 10, 16, 10);
	}

}
