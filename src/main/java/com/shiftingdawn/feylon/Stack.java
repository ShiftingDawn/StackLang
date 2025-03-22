package com.shiftingdawn.feylon;

import com.shiftingdawn.feylon.lang.DataType;

public class Stack {

	public static final int DEFAULT_STACK_SIZE = 64;
	private StackElement[] stack = new StackElement[Stack.DEFAULT_STACK_SIZE];
	private int pointer = -1;

	private void ensureSize() {
		if (this.pointer + 1 == this.stack.length) {
			final StackElement[] currentStack = this.stack;
			this.stack = new StackElement[this.stack.length * 2];
			System.arraycopy(currentStack, 0, this.stack, 0, currentStack.length);
		}
	}

	public void push(final int stackOrPointer, final DataType type) {
		this.ensureSize();
		this.stack[++this.pointer] = new StackElement(stackOrPointer, type);
	}

	public StackElement pop() {
		if (this.pointer == -1) {
			throw new StackUnderflowError();
		}
		return this.stack[this.pointer--];
	}
}
