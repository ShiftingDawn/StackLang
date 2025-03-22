package com.shiftingdawn.feylon.ins;

import com.shiftingdawn.feylon.Memory;
import com.shiftingdawn.feylon.Stack;
import com.shiftingdawn.feylon.StackElement;
import com.shiftingdawn.feylon.lang.DataType;

import java.util.function.IntConsumer;

public class ArithmeticInstructions {

	public static void add(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push(
				a.value() + b.value(),
				a.type() == DataType.POINTER || b.type() == DataType.POINTER ? DataType.POINTER : DataType.INT
		);
	}

	public static void subtract(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push(
				b.value() - a.value(),
				a.type() == DataType.POINTER || b.type() == DataType.POINTER ? DataType.POINTER : DataType.INT
		);
	}

	public static void multiply(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push(
				a.value() * b.value(),
				a.type() == DataType.POINTER || b.type() == DataType.POINTER ? DataType.POINTER : DataType.INT
		);
	}

	public static void divide(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		Instruction.assertType(a, DataType.INT);
		Instruction.assertType(b, DataType.INT);
		data.push(b.value() / a.value(), DataType.INT);
	}

	public static void mod(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		Instruction.assertType(a, DataType.INT);
		Instruction.assertType(b, DataType.INT);
		data.push(b.value() % a.value(), DataType.INT);
	}

	public static void bitShiftLeft(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		Instruction.assertType(a, DataType.INT);
		Instruction.assertType(b, DataType.INT);
		data.push(b.value() << a.value(), DataType.INT);
	}

	public static void bitShiftRight(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		Instruction.assertType(a, DataType.INT);
		Instruction.assertType(b, DataType.INT);
		data.push(b.value() >> a.value(), DataType.INT);
	}

	public static void bitwiseAnd(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		Instruction.assertType(a, DataType.INT);
		Instruction.assertType(b, DataType.INT);
		data.push(b.value() & a.value(), DataType.INT);
	}

	public static void bitwiseOr(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		Instruction.assertType(a, DataType.INT);
		Instruction.assertType(b, DataType.INT);
		data.push(b.value() | a.value(), DataType.INT);
	}

	public static void bitwiseXor(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		Instruction.assertType(a, DataType.INT);
		Instruction.assertType(b, DataType.INT);
		data.push(b.value() ^ a.value(), DataType.INT);
	}

	public static void equals(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push((a.type() == b.type()) && (a.value() == b.value()) ? 1 : 0, DataType.BOOL);
	}

	public static void notEquals(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push((a.type() == b.type()) && (a.value() != b.value()) ? 1 : 0, DataType.BOOL);
	}

	public static void less(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push((a.type() == b.type()) && (b.value() < a.value()) ? 1 : 0, DataType.BOOL);
	}

	public static void greater(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push((a.type() == b.type()) && (b.value() > a.value()) ? 1 : 0, DataType.BOOL);
	}

	public static void lessEqual(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push((a.type() == b.type()) && (b.value() <= a.value()) ? 1 : 0, DataType.BOOL);
	}

	public static void greaterEqual(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement a = data.pop();
		final StackElement b = data.pop();
		data.push((a.type() == b.type()) && (b.value() >= a.value()) ? 1 : 0, DataType.BOOL);
	}
}