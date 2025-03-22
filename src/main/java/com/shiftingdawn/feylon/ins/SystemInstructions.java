package com.shiftingdawn.feylon.ins;

import com.shiftingdawn.feylon.Memory;
import com.shiftingdawn.feylon.Stack;
import com.shiftingdawn.feylon.StackElement;
import com.shiftingdawn.feylon.lang.DataType;
import com.shiftingdawn.feylon.syscall.Register;
import com.shiftingdawn.feylon.syscall.SysCalls;

import java.util.function.IntConsumer;

public class SystemInstructions {

	public static void syscall0(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement call = data.pop();
		Instruction.assertType(call, DataType.INT);
		final SysCalls sysCall = SysCalls.getByIndex(call.value()).orElseThrow(() -> new AssertionError("SysCall '%s' is not implemented".formatted(call)));
		if (sysCall.getArgCount() != 0) {
			throw new AssertionError("SysCall '%s' required 0 arguments".formatted(call));
		}
		Register.RAX.set(call.value());
		sysCall.instantiate().apply(memory);
	}

	public static void syscall1(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement call = data.pop();
		Instruction.assertType(call, DataType.INT);
		final SysCalls sysCall = SysCalls.getByIndex(call.value()).orElseThrow(() -> new AssertionError("SysCall '%s' is not implemented".formatted(call)));
		if (sysCall.getArgCount() != 1) {
			throw new AssertionError("SysCall '%s' required 1 argument".formatted(call));
		}
		Register.RAX.set(call.value());
		final StackElement arg1 = data.pop();
		Instruction.assertType(arg1, DataType.INT, DataType.POINTER);
		Register.RDI.set(arg1.value());
		sysCall.instantiate().apply(memory);
	}

	public static void syscall2(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement call = data.pop();
		Instruction.assertType(call, DataType.INT);
		final SysCalls sysCall = SysCalls.getByIndex(call.value()).orElseThrow(() -> new AssertionError("SysCall '%s' is not implemented".formatted(call)));
		if (sysCall.getArgCount() != 2) {
			throw new AssertionError("SysCall '%s' required 2 arguments".formatted(call));
		}
		Register.RAX.set(call.value());
		final StackElement arg1 = data.pop();
		Instruction.assertType(arg1, DataType.INT, DataType.POINTER);
		Register.RDI.set(arg1.value());
		final StackElement arg2 = data.pop();
		Instruction.assertType(arg2, DataType.INT, DataType.POINTER);
		Register.RSI.set(arg2.value());
		sysCall.instantiate().apply(memory);
	}

	public static void syscall3(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement call = data.pop();
		Instruction.assertType(call, DataType.INT);
		final SysCalls sysCall = SysCalls.getByIndex(call.value()).orElseThrow(() -> new AssertionError("SysCall '%s' is not implemented".formatted(call)));
		if (sysCall.getArgCount() != 3) {
			throw new AssertionError("SysCall '%s' required 3 arguments".formatted(call));
		}
		Register.RAX.set(call.value());
		final StackElement arg1 = data.pop();
		Instruction.assertType(arg1, DataType.INT, DataType.POINTER);
		Register.RDI.set(arg1.value());
		final StackElement arg2 = data.pop();
		Instruction.assertType(arg2, DataType.INT, DataType.POINTER);
		Register.RSI.set(arg2.value());
		final StackElement arg3 = data.pop();
		Instruction.assertType(arg3, DataType.INT, DataType.POINTER);
		Register.RDX.set(arg3.value());
		sysCall.instantiate().apply(memory);
	}

	public static void syscall4(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement call = data.pop();
		Instruction.assertType(call, DataType.INT);
		final SysCalls sysCall = SysCalls.getByIndex(call.value()).orElseThrow(() -> new AssertionError("SysCall '%s' is not implemented".formatted(call)));
		if (sysCall.getArgCount() != 4) {
			throw new AssertionError("SysCall '%s' required 4 arguments".formatted(call));
		}
		Register.RAX.set(call.value());
		final StackElement arg1 = data.pop();
		Instruction.assertType(arg1, DataType.INT, DataType.POINTER);
		Register.RDI.set(arg1.value());
		final StackElement arg2 = data.pop();
		Instruction.assertType(arg2, DataType.INT, DataType.POINTER);
		Register.RSI.set(arg2.value());
		final StackElement arg3 = data.pop();
		Instruction.assertType(arg3, DataType.INT, DataType.POINTER);
		Register.RDX.set(arg3.value());
		final StackElement arg4 = data.pop();
		Instruction.assertType(arg4, DataType.INT, DataType.POINTER);
		Register.R10.set(arg4.value());
		sysCall.instantiate().apply(memory);
	}

	public static void syscall5(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement call = data.pop();
		Instruction.assertType(call, DataType.INT);
		final SysCalls sysCall = SysCalls.getByIndex(call.value()).orElseThrow(() -> new AssertionError("SysCall '%s' is not implemented".formatted(call)));
		if (sysCall.getArgCount() != 5) {
			throw new AssertionError("SysCall '%s' required 5 arguments".formatted(call));
		}
		Register.RAX.set(call.value());
		final StackElement arg1 = data.pop();
		Instruction.assertType(arg1, DataType.INT, DataType.POINTER);
		Register.RDI.set(arg1.value());
		final StackElement arg2 = data.pop();
		Instruction.assertType(arg2, DataType.INT, DataType.POINTER);
		Register.RSI.set(arg2.value());
		final StackElement arg3 = data.pop();
		Instruction.assertType(arg3, DataType.INT, DataType.POINTER);
		Register.RDX.set(arg3.value());
		final StackElement arg4 = data.pop();
		Instruction.assertType(arg4, DataType.INT, DataType.POINTER);
		Register.R10.set(arg4.value());
		final StackElement arg5 = data.pop();
		Instruction.assertType(arg5, DataType.INT, DataType.POINTER);
		Register.R8.set(arg5.value());
		sysCall.instantiate().apply(memory);
	}

	public static void syscall6(final IntConsumer jump, final Stack data, final Stack returnStack, final Memory memory) {
		final StackElement call = data.pop();
		Instruction.assertType(call, DataType.INT);
		final SysCalls sysCall = SysCalls.getByIndex(call.value()).orElseThrow(() -> new AssertionError("SysCall '%s' is not implemented".formatted(call)));
		if (sysCall.getArgCount() != 6) {
			throw new AssertionError("SysCall '%s' required 6 arguments".formatted(call));
		}
		Register.RAX.set(call.value());
		final StackElement arg1 = data.pop();
		Instruction.assertType(arg1, DataType.INT, DataType.POINTER);
		Register.RDI.set(arg1.value());
		final StackElement arg2 = data.pop();
		Instruction.assertType(arg2, DataType.INT, DataType.POINTER);
		Register.RSI.set(arg2.value());
		final StackElement arg3 = data.pop();
		Instruction.assertType(arg3, DataType.INT, DataType.POINTER);
		Register.RDX.set(arg3.value());
		final StackElement arg4 = data.pop();
		Instruction.assertType(arg4, DataType.INT, DataType.POINTER);
		Register.R10.set(arg4.value());
		final StackElement arg5 = data.pop();
		Instruction.assertType(arg5, DataType.INT, DataType.POINTER);
		Register.R8.set(arg5.value());
		final StackElement arg6 = data.pop();
		Instruction.assertType(arg6, DataType.INT, DataType.POINTER);
		Register.R9.set(arg6.value());
		sysCall.instantiate().apply(memory);
	}
}
