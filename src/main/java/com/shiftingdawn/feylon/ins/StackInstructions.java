package com.shiftingdawn.feylon.ins;

import com.shiftingdawn.feylon.Memory;
import com.shiftingdawn.feylon.Stack;
import com.shiftingdawn.feylon.StackElement;
import com.shiftingdawn.feylon.lang.DataType;

import java.util.function.IntConsumer;

public class StackInstructions {

	public static Instruction push(final int value) {
		return (jump, data, returnStack, memory) -> data.push(value, DataType.INT);
	}

	public static Instruction push(final boolean value) {
		return (jump, data, returnStack, memory) -> data.push(value ? 1 : 0, DataType.BOOL);
	}

	public static Instruction pushPtr(final int value) {
		return (jump, data, returnStack, memory) -> data.push(value, DataType.POINTER);
	}

	public static Instruction push(final String value) {
		return (jump, data, returnStack, memory) -> {
			final int pointer = memory.getNextStringPointer();
			final int writtenSize = memory.setString(pointer, value);
			data.push(writtenSize, DataType.INT);
			data.push(pointer, DataType.POINTER);
		};
	}

	public static void dump(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		System.out.println(data.pop().value());
	}

	public static void pop(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		data.pop();
	}

	public static void dup(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement x = data.pop();
		data.push(x.value(), x.type());
		data.push(x.value(), x.type());
	}

	public static void swap(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push(a.value(), a.type());
		data.push(b.value(), a.type());
	}

	public static void over(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push(b.value(), b.type());
		data.push(a.value(), a.type());
		data.push(b.value(), b.type());
	}

	public static void rot(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement c = data.pop();
		final StackElement b = data.pop();
		final StackElement a = data.pop();
		data.push(b.value(), b.type());
		data.push(c.value(), c.type());
		data.push(a.value(), a.type());
	}
}