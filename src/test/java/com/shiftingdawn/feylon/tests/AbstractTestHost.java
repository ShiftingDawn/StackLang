package com.shiftingdawn.feylon.tests;

import com.shiftingdawn.feylon.*;
import com.shiftingdawn.feylon.lang.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public abstract class AbstractTestHost {

	protected Stack dataStack;
	protected Stack returnStack;
	protected Memory memory;

	public void run(final String src, final int vars) {
		this.dataStack = new Stack(Constants.STACK_SIZE_DATA);
		this.returnStack = new Stack(Constants.STACK_SIZE_CALLS);
		this.memory = new Memory(64 << 1);
		final AssembledProgram program = Feylon.parse(new ResolvedSources("<generated>", List.of(src)), vars);
		new Simulator(this.dataStack, this.returnStack, this.memory, program).execute();
	}

	public void assertStack(final int value, final DataType type) {
		Assertions.assertEquals(new StackElement(value, type), this.dataStack.pop());
	}

	public void assertReturnTo(final int value) {
		Assertions.assertEquals(value, this.returnStack.pop());
	}

	public void assertMemory(final int pointer, final int value) {
		Assertions.assertEquals(value, this.memory.get(pointer));
	}

	public void assertStackEmpty() {
		Assertions.assertThrows(StackUnderflowError.class, this.dataStack::pop);
	}

	public void assertThrows(final String program) {
		Assertions.assertThrows(FeylonException.class, () -> this.run(program, 0));
	}
}
