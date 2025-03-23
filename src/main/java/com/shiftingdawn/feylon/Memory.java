package com.shiftingdawn.feylon;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.shiftingdawn.feylon.Constants.MEM_SIZE_STRINGS;
import static com.shiftingdawn.feylon.Constants.MEM_SIZE_VARS;

public class Memory {

	private final int ptrStrings;
	private final int ptrVars;
	private final int totalSize;
	private final ByteBuffer memory;
	private int nextStringPointer = 0;

	public Memory(final int size) {
		this.ptrStrings = size;
		this.ptrVars = this.ptrStrings + MEM_SIZE_STRINGS;
		this.totalSize = this.ptrVars + MEM_SIZE_VARS;
		this.memory = ByteBuffer.allocate(this.totalSize);
	}

	public void set(final int pointer, final byte x) {
		if (pointer < 0 || pointer >= this.totalSize) {
			throw new SegmentationError();
		}
		this.memory.put(pointer, x);
	}

	public int setString(final int pointer, final String str) {
		if (pointer < 0 || pointer >= MEM_SIZE_STRINGS) {
			throw new SegmentationError();
		}
		final byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
		this.memory.put(this.memory.capacity() - MEM_SIZE_STRINGS + pointer, bytes);
		this.nextStringPointer += bytes.length;
		return bytes.length;
	}

	public byte get(final int pointer) {
		if (pointer < 0 || pointer >= this.memory.capacity()) {
			throw new SegmentationError();
		}
		return this.memory.get(pointer);
	}

	public String getString(final int pointer, final int size) {
		if (pointer < 0 || pointer >= MEM_SIZE_STRINGS || size >= MEM_SIZE_STRINGS || pointer + size >= MEM_SIZE_STRINGS) {
			throw new SegmentationError();
		}
		final byte[] bytes = new byte[size];
		this.memory.get(this.memory.capacity() - MEM_SIZE_STRINGS + pointer, bytes);
		return new String(bytes, StandardCharsets.UTF_8);
	}

	public int getPtrStrings() {
		return this.ptrStrings;
	}

	public int getPtrVars() {
		return this.ptrVars;
	}

	public int getNextStringPointer() {
		return this.nextStringPointer;
	}
}
