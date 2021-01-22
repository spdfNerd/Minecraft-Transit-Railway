package com.spdfnerd.mtr.data;

import com.spdfnerd.mtr.path.RoutePathFinder;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Route extends DataBase {

	public final List<Long> platformIds;

	private static final String KEY_PLATFORM_IDS = "platform_ids";

	public Route() {
		super();
		platformIds = new ArrayList<>();
	}

	public Route(CompoundNBT tag) {
		super(tag);
		platformIds = new ArrayList<>();
		final long[] platformIdsArray = tag.getLongArray(KEY_PLATFORM_IDS);
		for (final long platformId : platformIdsArray) {
			platformIds.add(platformId);
		}
	}

	public Route(PacketBuffer packet) {
		super(packet);
		platformIds = new ArrayList<>();
		final int platformCount = packet.readInt();
		for (int i = 0; i < platformCount; i++) {
			platformIds.add(packet.readLong());
		}
	}

	@Override
	public CompoundNBT toCompoundNBT() {
		final CompoundNBT tag = super.toCompoundNBT();
		tag.putLongArray(KEY_PLATFORM_IDS, platformIds);
		return tag;
	}

	@Override
	public void writePacket(PacketBuffer packet) {
		super.writePacket(packet);
		packet.writeInt(platformIds.size());
		for (final long platformId : platformIds) {
			packet.writeLong(platformId);
		}
	}

	public List<List<Pos3f>> getPath(IWorld world, Set<Platform> platforms, Platform firstPlatform) {
		final List<List<Pos3f>> paths = new ArrayList<>();
		for (int i = -1; i < platformIds.size() - 1; i++) {
			final Platform platformStart = i < 0 ? firstPlatform : RailwayData.getDataById(platforms, platformIds.get(i));
			final Platform platformEnd = RailwayData.getDataById(platforms, platformIds.get(i + 1));
			if (platformStart == null || platformEnd == null) {
				return paths;
			} else {
				final List<Pos3f> path = new RoutePathFinder(world, platformStart, platformEnd).findPath();
				paths.add(path);
			}
		}
		return paths;
	}

}
