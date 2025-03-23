package com.shiftingdawn.feylon;

import com.shiftingdawn.feylon.ins.Instruction;
import com.shiftingdawn.feylon.lang.AssembledProgram;

import static com.shiftingdawn.feylon.Constants.STACK_SIZE_CALLS;
import static com.shiftingdawn.feylon.Constants.STACK_SIZE_DATA;

public class Simulator {

	private final Stack dataStack;
	private final Stack returnStack;
	private final Memory memory;
	private final AssembledProgram program;
	private int currentInstruction;

	public Simulator(final Stack dataStack, final Stack returnStack, final Memory memory, final AssembledProgram program) {
		this.dataStack = dataStack;
		this.returnStack = returnStack;
		this.memory = memory;
		this.program = program;
	}

	public Simulator(final AssembledProgram program) {
		this(new Stack(STACK_SIZE_DATA), new Stack(STACK_SIZE_CALLS), new Memory(program.memorySize() << 1), program);
	}

	public void execute() {
		while (this.currentInstruction < this.program.instructions().length) {
			final Instruction instruction = this.program.instructions()[this.currentInstruction++];
			if (instruction == null) {
				continue;
			}
			try {
				instruction.apply(this::jump, this.dataStack, this.returnStack, this.memory);
			} catch (final RuntimeException | Error e) {
				if (e instanceof final SourcePosAware aware) {
					aware.setSourcePos(this.program.sourceLocations().get(instruction));
				}
				throw e;
			}
		}
	}

	public void jump(final int pointer) {
		this.currentInstruction = pointer;
	}
}