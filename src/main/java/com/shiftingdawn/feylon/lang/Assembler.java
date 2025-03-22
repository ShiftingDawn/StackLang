package com.shiftingdawn.feylon.lang;

import com.shiftingdawn.feylon.OrderedList;
import com.shiftingdawn.feylon.ins.*;

final class Assembler {

	public static AssembledProgram assemble(final LinkerContext linkerContext) {
		final OrderedList<LinkedToken> tokens = new OrderedList<>(linkerContext.result).reverse();
		final AssemblerContext ctx = new AssemblerContext();
		final int skippedPointerOffset = 0;
		while (!tokens.isEmpty()) {
			final LinkedToken token = tokens.pop();
			switch (token.type) {
				case PUSH_INT -> ctx.append(StackInstructions.push((int) token.data), token.pos);
				case PUSH_BOOL -> ctx.append(StackInstructions.push((boolean) token.data), token.pos);
				case PUSH_STRING -> ctx.append(StackInstructions.push((String) token.data), token.pos);
				case PUSH_POINTER -> ctx.append(StackInstructions.pushPtr((int) token.data), token.pos);
				case INTRINSIC -> Assembler.processIntrinsic(ctx, token);

				case FUNCTION -> ctx.append(ControlFlowInstructions.jump((int) token.data - skippedPointerOffset), token.pos);
				case CALL -> ctx.append(ControlFlowInstructions.call(
								linkerContext.functions.get(token.txt).pointer - skippedPointerOffset + 1, (token.selfPointer - skippedPointerOffset) + 1),
						token.pos
				);
				case RETURN -> ctx.append(ControlFlowInstructions::ret, token.pos);

				case JUMP -> ctx.append(ControlFlowInstructions.jump((int) token.data - skippedPointerOffset), token.pos);
				case JUMP_EQ -> ctx.append(ControlFlowInstructions.jumpEq((int) token.data - skippedPointerOffset), token.pos);
				case JUMP_NEQ -> ctx.append(ControlFlowInstructions.jumpNeq((int) token.data - skippedPointerOffset), token.pos);
				case DO -> ctx.append(ControlFlowInstructions.jumpNeq((int) token.data - skippedPointerOffset), token.pos);

				default -> throw new AssertionError("Encountered unhandled token: " + token.type);
			}
		}
		return ctx.finalizeAssembling(linkerContext.memSize);
	}

	private static void processIntrinsic(final AssemblerContext ctx, final LinkedToken token) {
		ctx.append(switch ((Intrinsics) token.data) {
			case ADD -> (Instruction) ArithmeticInstructions::add;
			case SUBTRACT -> (Instruction) ArithmeticInstructions::subtract;
			case MULTIPLY -> (Instruction) ArithmeticInstructions::multiply;
			case DIVIDE -> (Instruction) ArithmeticInstructions::divide;
			case MODULO -> (Instruction) ArithmeticInstructions::mod;
			case SHIFT_LEFT -> (Instruction) ArithmeticInstructions::bitShiftLeft;
			case SHIFT_RIGHT -> (Instruction) ArithmeticInstructions::bitShiftRight;
			case BITWISE_AND -> (Instruction) ArithmeticInstructions::bitwiseAnd;
			case BITWISE_OR -> (Instruction) ArithmeticInstructions::bitwiseOr;
			case BITWISE_XOR -> (Instruction) ArithmeticInstructions::bitwiseXor;

			case EQUALS -> (Instruction) ArithmeticInstructions::equals;
			case NOT_EQUALS -> (Instruction) ArithmeticInstructions::notEquals;
			case LESS -> (Instruction) ArithmeticInstructions::less;
			case GREATER -> (Instruction) ArithmeticInstructions::greater;
			case LESS_OR_EQUAL -> (Instruction) ArithmeticInstructions::lessEqual;
			case GREATER_OR_EQUAL -> (Instruction) ArithmeticInstructions::greaterEqual;

			case DUMP -> (Instruction) StackInstructions::dump;
			case POP -> (Instruction) StackInstructions::pop;
			case DUP -> (Instruction) StackInstructions::dup;
			case SWAP -> (Instruction) StackInstructions::swap;
			case OVER -> (Instruction) StackInstructions::over;
			case ROT -> (Instruction) StackInstructions::rot;

			case STORE -> (Instruction) MemoryInstructions::store8;
			case LOAD -> (Instruction) MemoryInstructions::load8;
			case STORE_16 -> (Instruction) MemoryInstructions::store16;
			case LOAD_16 -> (Instruction) MemoryInstructions::load16;
			case STORE_32 -> (Instruction) MemoryInstructions::store32;
			case LOAD_32 -> (Instruction) MemoryInstructions::load32;

			case SYSCALL_0 -> (Instruction) SystemInstructions::syscall0;
			case SYSCALL_1 -> (Instruction) SystemInstructions::syscall1;
			case SYSCALL_2 -> (Instruction) SystemInstructions::syscall2;
			case SYSCALL_3 -> (Instruction) SystemInstructions::syscall3;
			case SYSCALL_4 -> (Instruction) SystemInstructions::syscall4;
			case SYSCALL_5 -> (Instruction) SystemInstructions::syscall5;
			case SYSCALL_6 -> (Instruction) SystemInstructions::syscall6;
		}, token.pos);
	}
}