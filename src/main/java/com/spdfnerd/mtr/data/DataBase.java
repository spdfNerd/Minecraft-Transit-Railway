package com.spdfnerd.mtr.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

import java.util.Random;

public abstract class DataBase implements Comparable<DataBase> {

	public final long id;
	public String name;
	public int color;

	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_COLOR = "color";

	private static final int PACKET_STRING_READ_LENGTH = 32767;

	public DataBase() {
		id = new Random().nextInt(Integer.MAX_VALUE);
		name = "";
	}

	public DataBase(CompoundNBT tag) {
		id = tag.getLong(KEY_ID);
		name = tag.getString(KEY_NAME);
		color = tag.getInt(KEY_COLOR);
	}

	public DataBase(PacketBuffer packet) {
		id = packet.readLong();
		name = packet.readString(PACKET_STRING_READ_LENGTH);
		color = packet.readInt();
	}

	public CompoundNBT toCompoundNBT() {
		final CompoundNBT tag = new CompoundNBT();
		tag.putLong(KEY_ID, id);
		tag.putString(KEY_NAME, name);
		tag.putInt(KEY_COLOR, color);
		return tag;
	}

	public void writePacket(PacketBuffer packet) {
		packet.writeLong(id);
		packet.writeString(name);
		packet.writeInt(color);
	}

	@Override
	public int compareTo(DataBase compare) {
		return (name.toLowerCase() + color).compareTo((compare.name + compare.color).toLowerCase());
	}

}
