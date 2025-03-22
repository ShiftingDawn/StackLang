package com.shiftingdawn.feylon.lang;

import com.shiftingdawn.feylon.ins.Instruction;

import java.util.Map;

public record AssembledProgram(Instruction[] instructions, int memorySize, Map<Instruction, TokenPos> sourceLocations) {
}
