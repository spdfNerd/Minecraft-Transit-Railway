package com.spdfnerd.mtr.item;

import com.spdfnerd.mtr.data.RailwayData;
import com.spdfnerd.mtr.packet.PacketTrainDataGuiServer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemDashboard extends Item {

	public ItemDashboard(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (!world.isRemote) {
			RailwayData railwayData = RailwayData.getInstance(world);
			if (railwayData != null) {
				PacketTrainDataGuiServer.openDashboardScreenS2C(player, railwayData.getPlatforms(world), railwayData.getRoutes());
			}
		}
		return super.onItemRightClick(world, player, hand);
	}

}
