package com.shiftingdawn.feylon.lang;

import com.shiftingdawn.feylon.OrderedList;
import com.shiftingdawn.feylon.ins.Instruction;

import java.util.HashMap;
import java.util.Map;

final class AssemblerContext {

	private final OrderedList<Instruction> result = new OrderedList<>();
	private final Map<Instruction, TokenPos> sourceLocations = new HashMap<>();

	public void append(final Instruction instruction, final TokenPos sourcePos) {
		this.result.append(instruction);
		this.sourceLocations.put(instruction, sourcePos);
	}

	public AssembledProgram finalizeAssembling(final int memSize) {
		return new AssembledProgram(this.result.toArray(Instruction[]::new), memSize, this.sourceLocations);
	}
}