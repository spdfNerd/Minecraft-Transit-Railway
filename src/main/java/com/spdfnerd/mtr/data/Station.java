package com.spdfnerd.mtr.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;

public final class Station extends DataBase {

	public Tuple<Integer, Integer> corner1, corner2;

	private static final String KEY_X_MIN = "x_min";
	private static final String KEY_Z_MIN = "z_min";
	private static final String KEY_X_MAX = "x_max";
	private static final String KEY_Z_MAX = "z_max";

	public Station() {
		super();
	}

	public Station(CompoundNBT tag) {
		super(tag);
		corner1 = new Tuple<>(tag.getInt(KEY_X_MIN), tag.getInt(KEY_Z_MIN));
		corner2 = new Tuple<>(tag.getInt(KEY_X_MAX), tag.getInt(KEY_Z_MAX));
	}

	public Station(PacketBuffer packet) {
		super(packet);
		corner1 = new Tuple<>(packet.readInt(), packet.readInt());
		corner2 = new Tuple<>(packet.readInt(), packet.readInt());
	}

	@Override
	public CompoundNBT toCompoundNBT() {
		final CompoundNBT tag = super.toCompoundNBT();
		tag.putInt(KEY_X_MIN, corner1.getA());
		tag.putInt(KEY_Z_MIN, corner1.getB());
		tag.putInt(KEY_X_MAX, corner2.getA());
		tag.putInt(KEY_Z_MAX, corner2.getB());
		return tag;
	}

	@Override
	public void writePacket(PacketBuffer packet) {
		super.writePacket(packet);
		packet.writeInt(corner1.getA());
		packet.writeInt(corner1.getB());
		packet.writeInt(corner2.getA());
		packet.writeInt(corner2.getB());
	}

	public boolean inStation(int x, int z) {
		return RailwayData.isBetween(x, corner1.getA(), corner2.getA()) && RailwayData.isBetween(z, corner1.getB(), corner2.getB());
	}

	public BlockPos getCenter() {
		return new BlockPos((corner1.getA() + corner2.getA()) / 2, 0, (corner1.getB() + corner2.getB()) / 2);
	}

	@Override
	public String toString() {
		return String.format("Station %s: (%d, %d) (%d, %d)", name, corner1.getA(), corner1.getB(), corner2.getA(), corner2.getB());
	}
	
}
