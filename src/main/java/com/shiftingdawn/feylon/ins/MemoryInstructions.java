package com.shiftingdawn.feylon.ins;

import com.shiftingdawn.feylon.Memory;
import com.shiftingdawn.feylon.Stack;
import com.shiftingdawn.feylon.StackElement;
import com.shiftingdawn.feylon.lang.DataType;

import java.util.function.IntConsumer;

public class MemoryInstructions {

	public static void store8(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		final StackElement x = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		Instruction.assertType(x, DataType.INT);
		memory.set(ptr.value() << 1, (byte) DataType.INT.ordinal());
		memory.set((ptr.value() << 1) + 1, (byte) x.value());
	}

	public static void store16(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		final StackElement x = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		Instruction.assertType(x, DataType.INT);
		memory.set(ptr.value() << 1, (byte) DataType.INT.ordinal());
		memory.set((ptr.value() << 1) + 1, (byte) x.value());
		memory.set((ptr.value() << 1) + 2, (byte) (x.value() >> 8));
	}

	public static void store32(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		final StackElement x = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		Instruction.assertType(x, DataType.INT);
		memory.set(ptr.value() << 1, (byte) DataType.INT.ordinal());
		memory.set((ptr.value() << 1) + 1, (byte) x.value());
		memory.set((ptr.value() << 1) + 2, (byte) (x.value() >> 8));
		memory.set((ptr.value() << 1) + 3, (byte) (x.value() >> 16));
		memory.set((ptr.value() << 1) + 4, (byte) (x.value() >> 24));
	}

	public static void load8(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		final DataType type = DataType.values()[memory.get(ptr.value()) << 1];
		final int x = memory.get((ptr.value() << 1) + 1) & 0xFF;
		data.push(x, type);
	}

	public static void load16(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		final int p = (ptr.value() << 1);
		final DataType type = DataType.values()[memory.get(p)];
		final int x = memory.get(p + 1) & 0xFF;
		final int y = (memory.get(p + 2) & 0xFF) << 8;
		final int z = x | y;
		data.push(z, type);
	}

	public static void load32(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		final DataType type = DataType.values()[memory.get(ptr.value()) << 1];
		int x = memory.get((ptr.value() << 1) + 1) & 0xFF;
		x |= (memory.get((ptr.value() << 1) + 2) & 0xFF) << 8;
		x |= (memory.get((ptr.value() << 1) + 3) & 0xFF) << 16;
		x |= (memory.get((ptr.value() << 1) + 4) & 0xFF) << 24;
		data.push(x, type);
	}
}
