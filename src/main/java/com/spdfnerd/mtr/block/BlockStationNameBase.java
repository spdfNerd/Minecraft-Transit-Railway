package com.spdfnerd.mtr.block;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BlockStationNameBase extends HorizontalBlock implements ITileEntityProvider {

	public static final IntegerProperty COLOUR = IntegerProperty.create("color", 0, 2);

	protected BlockStationNameBase(Properties properties) {
		super(properties);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("tooltip.mtr.station_color_name").setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.GRAY))));
	}

	public abstract static class TileEntityStationNameBase extends TileEntity {

		public final boolean verticalChinese;
		public final boolean hasShadow;
		public final int scale;
		public final float yOffset;
		public final float zOffset;

		public TileEntityStationNameBase(TileEntityType<?> type, boolean verticalChinese, boolean hasShadow, int scale, float yOffset, float zOffset) {
			super(type);
			this.verticalChinese = verticalChinese;
			this.hasShadow = hasShadow;
			this.scale = scale;
			this.yOffset = yOffset;
			this.zOffset = zOffset;
		}

		public abstract boolean shouldRender();

	}

}
