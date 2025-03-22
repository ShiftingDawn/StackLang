package com.shiftingdawn.feylon.ins;

import com.shiftingdawn.feylon.DataTypeMismatchException;
import com.shiftingdawn.feylon.Memory;
import com.shiftingdawn.feylon.Stack;
import com.shiftingdawn.feylon.StackElement;
import com.shiftingdawn.feylon.lang.DataType;

import java.util.function.IntConsumer;

@FunctionalInterface
public interface Instruction {

	void apply(IntConsumer jump, Stack data, Stack returnStack, Memory memory);

	static void assertType(final StackElement element, final DataType expected) {
		if (element.type() != expected) {
			throw new DataTypeMismatchException(element.type(), expected);
		}
	}

	static void assertType(final StackElement element, final DataType... expected) {
		for (final DataType type : expected) {
			if (element.type() == type) {
				return;
			}
		}
		throw new DataTypeMismatchException(element.type(), expected);
	}
}