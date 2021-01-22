package com.spdfnerd.mtr.tileentity;

import com.spdfnerd.mtr.block.BlockStationNameBase;
import com.spdfnerd.mtr.block.BlockStationNameTallBlock;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.block.BlockState;

public class StationNameTallBlockTileEntity extends BlockStationNameBase.TileEntityStationNameBase {

	public StationNameTallBlockTileEntity() {
		super(Registration.STATION_NAME_TALL_BLOCK_TILE_ENTITY.get(), true, false, 80, 0.25F, 0.6875F);
	}

	@Override
	public boolean shouldRender() {
		if (world == null) {
			return false;
		}
		final BlockState state = world.getBlockState(pos);
		return state.getBlock() instanceof BlockStationNameTallBlock && IBlock.getStatePropertySafe(state, IBlock.THIRD) == IBlock.EnumThird.MIDDLE;
	}
}
