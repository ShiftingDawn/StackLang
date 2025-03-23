package com.shiftingdawn.feylon.ins;

import com.shiftingdawn.feylon.Constants;
import com.shiftingdawn.feylon.StackElement;
import com.shiftingdawn.feylon.lang.DataType;

public class VarInstructions {

	public static Instruction pushVars(final int varsToPush) {
		return (jump, data, returnStack, memory) -> {
			final byte offset = memory.get(memory.getPtrVars());
			final int startPointer = memory.getPtrVars() + 1 + (offset * (Constants.SIZEOF_INT + 1));
			for (int i = 0; i < varsToPush; ++i) {
				final StackElement x = data.pop();
				final int pointer = startPointer + (varsToPush - 1 - i) * (Constants.SIZEOF_INT + 1);
				memory.set(pointer, (byte) x.type().ordinal());
				memory.set(pointer + 1, (byte) x.value());
				memory.set(pointer + 2, (byte) (x.value() >> 8));
				memory.set(pointer + 3, (byte) (x.value() >> 16));
				memory.set(pointer + 4, (byte) (x.value() >> 24));
			}
			memory.set(memory.getPtrVars(), (byte) (offset + varsToPush));
		};
	}

	public static Instruction applyVar(final int varIndex) {
		return (jump, data, returnStack, memory) -> {
			final int pointer = (memory.getPtrVars() + 1) + varIndex * (Constants.SIZEOF_INT + 1);
			final DataType type = DataType.values()[memory.get(pointer)];
			int x = memory.get(pointer + 1) & 0xFF;
			x |= (memory.get(pointer + 2) & 0xFF) << 8;
			x |= (memory.get(pointer + 3) & 0xFF) << 16;
			x |= (memory.get(pointer + 4) & 0xFF) << 24;
			data.push(x, type);
		};
	}

	public static Instruction popVars(final int varsToPop) {
		return (jump, data, returnStack, memory) -> {
			final byte offset = memory.get(memory.getPtrVars());
			memory.set(memory.getPtrVars(), (byte) (offset - varsToPop));
		};
	}
}