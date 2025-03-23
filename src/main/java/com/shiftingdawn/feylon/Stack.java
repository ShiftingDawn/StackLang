package com.shiftingdawn.feylon;

import com.shiftingdawn.feylon.lang.DataType;

public class Stack {

	private StackElement[] stack;
	private int pointer = -1;

	public Stack(final int size) {
		this.stack = new StackElement[size];
	}

	private void ensureSize() {
		if (this.pointer + 1 == this.stack.length) {
			final StackElement[] currentStack = this.stack;
			this.stack = new StackElement[this.stack.length * 2];
			System.arraycopy(currentStack, 0, this.stack, 0, currentStack.length);
		}
	}

	public void push(final int data, final DataType type) {
		this.ensureSize();
		this.stack[++this.pointer] = new StackElement(data, type);
	}

	public StackElement pop() {
		if (this.pointer == -1) {
			throw new StackUnderflowError();
		}
		return this.stack[this.pointer--];
	}
}
