package com.spdfnerd.mtr.item;

import com.spdfnerd.mtr.block.BlockPSDAPGBase;
import com.spdfnerd.mtr.block.BlockPSDTop;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemPSDAPGBase extends Item implements IBlock {

	private final EnumPSDAPGItem item;
	private final EnumPSDAPGType type;

	public ItemPSDAPGBase(EnumPSDAPGItem item, EnumPSDAPGType type) {
		super(new Item.Properties().group(ItemGroup.DECORATIONS));
		this.item = item;
		this.type = type;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		final boolean isPSD = type == EnumPSDAPGType.PSD;
		final boolean isDoor = item == EnumPSDAPGItem.PSD_APG_DOOR;

		if (blocksNotReplaceable(context, isDoor ? 2 : 1, isPSD ? 3 : 2, getBlockStateFromItem().getBlock())) {
			return ActionResultType.FAIL;
		}

		final World world = context.getWorld();
		final Direction playerFacing = context.getPlayer().getHorizontalFacing();
		final BlockPos pos = context.getPos().offset(context.getFace());

		for (int x = 0; x < (isDoor ? 2 : 1); x++) {
			final BlockPos newPos = pos.offset(playerFacing.rotateY(), x);

			for (int y = 0; y < 2; y++) {
				final BlockState state = getBlockStateFromItem().with(BlockPSDAPGBase.HORIZONTAL_FACING, playerFacing).with(HALF, y == 1 ?
						SlabType.TOP : SlabType.BOTTOM);
				if (isDoor) {
					final EnumSide side = x == 0 ? EnumSide.LEFT : EnumSide.RIGHT;
					world.setBlockState(newPos.up(y), state.with(SIDE, side));
				} else {
					final EnumSide side = EnumSide.SINGLE;
					world.setBlockState(newPos.up(y), state.with(SIDE_EXTENDED, side));
				}
			}

			if (isPSD) {
				world.setBlockState(newPos.up(2), BlockPSDTop.getActualState(world, newPos.up(2)));
			}
		}

		context.getItem().getStack().shrink(1);
		return ActionResultType.SUCCESS;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.mtr." + item.toString()).setStyle(Style.EMPTY.setColor(Color.fromTextFormatting(TextFormatting.GRAY))));
	}

	private BlockState getBlockStateFromItem() {
		boolean isPSD = type == EnumPSDAPGType.PSD;
		switch (item) {
			case PSD_APG_DOOR:
				return isPSD ? Registration.PSD_DOOR.get().getDefaultState() : Registration.APG_DOOR.get().getDefaultState();
			case PSD_APG_GLASS:
				return isPSD ? Registration.PSD_GLASS.get().getDefaultState() : Registration.APG_GLASS.get().getDefaultState();
			case PSD_APG_GLASS_END:
				return isPSD ? Registration.PSD_GLASS_END.get().getDefaultState() : Registration.APG_GLASS_END.get().getDefaultState();
		}
		return net.minecraft.block.Blocks.AIR.getDefaultState();
	}

	public static boolean blocksNotReplaceable(ItemUseContext context, int width, int height, Block blacklistBlock) {
		final Direction facing = context.getPlayer().getHorizontalFacing();
		final World world = context.getWorld();
		final BlockPos startingPos = context.getPos().offset(context.getFace());

		for (int x = 0; x < width; x++) {
			final BlockPos offsetPos = startingPos.offset(facing.rotateY(), x);

			if (blacklistBlock != null) {
				final boolean isBlacklistedBelow = world.getBlockState(offsetPos.down()).getBlock() == blacklistBlock;
				final boolean isBlacklistedAbove = world.getBlockState(offsetPos.up(height)).getBlock() == blacklistBlock;
				if (isBlacklistedBelow || isBlacklistedAbove) {
					return true;
				}
			}

			for (int y = 0; y < height; y++) {
				if (!world.getBlockState(offsetPos.up(y)).getMaterial().isReplaceable()) {
					return true;
				}
			}
		}

		return false;
	}

	public enum EnumPSDAPGType implements IStringSerializable {

		PSD("psd"), APG("apg");
		private final String name;

		EnumPSDAPGType(String name) {
			this.name = name;
		}

		@Override
		public String getString() {
			return name;
		}

	}

	public enum EnumPSDAPGItem implements IStringSerializable {

		PSD_APG_DOOR("psd_apg_door"), PSD_APG_GLASS("psd_apg_glass"), PSD_APG_GLASS_END("psd_apg_glass_end");
		private final String name;

		EnumPSDAPGItem(String name) {
			this.name = name;
		}

		@Override
		public String getString() {
			return name;
		}

	}

}
