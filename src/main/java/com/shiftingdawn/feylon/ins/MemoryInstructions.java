package com.shiftingdawn.feylon.ins;

import com.shiftingdawn.feylon.Memory;
import com.shiftingdawn.feylon.MemoryElement;
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
		memory.set(ptr.value(), (byte) x.value(), DataType.INT);
	}

	public static void store16(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		final StackElement x = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		Instruction.assertType(x, DataType.INT);
		memory.set(ptr.value(), (byte) x.value(), DataType.INT);
		memory.set(ptr.value() + 1, (byte) (x.value() >> 8), DataType.INT);
	}

	public static void store32(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		final StackElement x = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		Instruction.assertType(x, DataType.INT);
		memory.set(ptr.value(), (byte) x.value(), DataType.INT);
		memory.set(ptr.value() + 1, (byte) (x.value() >> 8), DataType.INT);
		memory.set(ptr.value() + 2, (byte) (x.value() >> 16), DataType.INT);
		memory.set(ptr.value() + 3, (byte) (x.value() >> 25), DataType.INT);
	}

	public static void load8(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		final MemoryElement x = memory.get(ptr.value());
		data.push(x.value(), x.type());
	}

	public static void load16(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		final MemoryElement elem = memory.get(ptr.value());
		int x = memory.get(ptr.value()).value() & 0xFF;
		x |= (memory.get(ptr.value() + 1).value() & 0xFF) << 8;
		data.push(x, elem.type());
	}

	public static void load32(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement ptr = data.pop();
		Instruction.assertType(ptr, DataType.POINTER);
		final MemoryElement elem = memory.get(ptr.value());
		int x = elem.value() & 0xFF;
		x |= (memory.get(ptr.value() + 1).value() & 0xFF) << 8;
		x |= (memory.get(ptr.value() + 2).value() & 0xFF) << 16;
		x |= (memory.get(ptr.value() + 3).value() & 0xFF) << 24;
		data.push(x, elem.type());
	}
}
