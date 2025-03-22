package com.shiftingdawn.feylon;

import com.shiftingdawn.feylon.lang.DataType;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Memory {

	public static final int STRING_MEMORY_SIZE = 64_000;
	private final ByteBuffer memory;
	private int nextStringPointer = 0;

	public Memory(final int size) {
		this.memory = ByteBuffer.allocate((size << 1) + Memory.STRING_MEMORY_SIZE);
	}

	public void set(final int pointer, final byte x, final DataType type) {
		if (pointer < 0 || pointer >= this.memory.capacity()) {
			throw new SegmentationError();
		}
		this.memory.put(pointer << 1, (byte) type.ordinal());
		this.memory.put((pointer << 1) | 1, x);
	}

	public int setString(final int pointer, final String str) {
		if (pointer < 0 || pointer >= Memory.STRING_MEMORY_SIZE) {
			throw new SegmentationError();
		}
		final byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
		this.memory.put(this.memory.capacity() - Memory.STRING_MEMORY_SIZE + pointer, bytes);
		this.nextStringPointer += bytes.length;
		return bytes.length;
	}

	public MemoryElement get(final int pointer) {
		if (pointer < 0 || pointer >= this.memory.capacity()) {
			throw new SegmentationError();
		}
		final byte type = this.memory.get(pointer << 1);
		final byte value = this.memory.get((pointer << 1) | 1);
		return new MemoryElement(value, DataType.values()[type]);
	}

	public String getString(final int pointer, final int size) {
		if (pointer < 0 || pointer >= Memory.STRING_MEMORY_SIZE || size >= Memory.STRING_MEMORY_SIZE || pointer + size >= Memory.STRING_MEMORY_SIZE) {
			throw new SegmentationError();
		}
		final byte[] bytes = new byte[size];
		this.memory.get(this.memory.capacity() - Memory.STRING_MEMORY_SIZE + pointer, bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}

	public int getNextStringPointer() {
		return this.nextStringPointer;
	}
}
