package com.spdfnerd.mtr;

import com.spdfnerd.mtr.setup.ClientSetup;
import com.spdfnerd.mtr.setup.ModSetup;
import com.spdfnerd.mtr.setup.Registration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MTR.MOD_ID)
public class MTR {

	public static final String MOD_ID = "mtr";

	public MTR() {
		Registration.init();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
	}

}
