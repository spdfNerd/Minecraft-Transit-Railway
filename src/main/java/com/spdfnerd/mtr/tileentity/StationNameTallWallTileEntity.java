package com.spdfnerd.mtr.tileentity;

import com.spdfnerd.mtr.block.BlockStationNameBase;
import com.spdfnerd.mtr.block.BlockStationNameTallWall;
import com.spdfnerd.mtr.block.IBlock;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraft.block.BlockState;

public class StationNameTallWallTileEntity extends BlockStationNameBase.TileEntityStationNameBase {

	public StationNameTallWallTileEntity() {
		super(Registration.STATION_NAME_TALL_WALL_TILE_ENTITY.get(), true, false, 80, 0.25F, 0.03125F);
	}

	@Override
	public boolean shouldRender() {
		if (world == null) {
			return false;
		}
		final BlockState state = world.getBlockState(pos);
		return state.getBlock() instanceof BlockStationNameTallWall && IBlock.getStatePropertySafe(state, IBlock.THIRD) == IBlock.EnumThird.MIDDLE;
	}
}
