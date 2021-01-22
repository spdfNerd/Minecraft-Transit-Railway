package com.spdfnerd.mtr.tileentity;

import com.spdfnerd.mtr.block.BlockStationNameBase;
import com.spdfnerd.mtr.setup.Registration;

public class StationNameTileEntity extends BlockStationNameBase.TileEntityStationNameBase {

	public StationNameTileEntity() {
		super(Registration.STATION_NAME_WALL_TILE_ENTITY.get(), false, true, 40, 0, 0);
	}

	@Override
	public boolean shouldRender() {
		return true;
	}

}
