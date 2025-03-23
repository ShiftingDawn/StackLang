package com.shiftingdawn.feylon.tests;

import com.shiftingdawn.feylon.Constants;
import com.shiftingdawn.feylon.Stack;
import com.shiftingdawn.feylon.StackElement;
import com.shiftingdawn.feylon.StackUnderflowError;
import com.shiftingdawn.feylon.lang.DataType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class StackTests {

	private Stack stack;
	private Supplier<StackElement[]> array;
	private IntSupplier pointer;

	@Test
	public void testInit() {
		assertArrayEquals(new StackElement[Constants.STACK_SIZE_DATA], this.array.get());
		assertEquals(-1, this.pointer.getAsInt());
	}

	@Test
	public void testPush() {
		this.stack.push(1, DataType.INT);
		assertEquals(new StackElement(1, DataType.INT), this.array.get()[0]);
		assertEquals(0, this.pointer.getAsInt());
	}

	@Test
	public void testPush2() {
		this.stack.push(1, DataType.INT);
		assertEquals(new StackElement(1, DataType.INT), this.array.get()[0]);
		assertEquals(0, this.pointer.getAsInt());
		this.stack.push(2, DataType.INT);
		assertEquals(new StackElement(2, DataType.INT), this.array.get()[1]);
		assertEquals(1, this.pointer.getAsInt());
	}

	@Test
	public void testPushBoolean() {
		this.stack.push(0, DataType.BOOL);
		assertEquals(new StackElement(0, DataType.BOOL), this.array.get()[0]);
		assertEquals(0, this.pointer.getAsInt());
		this.stack.push(1, DataType.BOOL);
		assertEquals(new StackElement(1, DataType.BOOL), this.array.get()[1]);
		assertEquals(1, this.pointer.getAsInt());
	}

	@Test
	public void testPushResize() {
		for (int i = 0; i < Constants.STACK_SIZE_DATA - 1; ++i) {
			this.stack.push(1, DataType.INT);
		}
		assertEquals(Constants.STACK_SIZE_DATA, this.array.get().length);
		this.stack.push(1, DataType.INT);
		assertEquals(Constants.STACK_SIZE_DATA, this.array.get().length);
		this.stack.push(1, DataType.INT);
		assertEquals(Constants.STACK_SIZE_DATA * 2, this.array.get().length);
	}

	@Test
	public void testPop() {
		this.stack.push(1, DataType.INT);
		assertEquals(new StackElement(1, DataType.INT), this.stack.pop());
		assertEquals(-1, this.pointer.getAsInt());
	}

	@Test
	public void testPopEmpty() {
		assertThrows(StackUnderflowError.class, this.stack::pop);
	}

	@BeforeEach
	public void init() throws NoSuchFieldException {
		this.stack = new Stack(Constants.STACK_SIZE_DATA);
		final Field arrayField = Stack.class.getDeclaredField("stack");
		arrayField.setAccessible(true);
		this.array = () -> {
			try {
				return (StackElement[]) arrayField.get(StackTests.this.stack);
			} catch (final IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		};
		final Field pointerField = Stack.class.getDeclaredField("pointer");
		pointerField.setAccessible(true);
		this.pointer = () -> {
			try {
				return pointerField.getInt(this.stack);
			} catch (final IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		};
	}
}