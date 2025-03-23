package com.shiftingdawn.feylon.lang;

public enum InstructionType {

	PUSH_INT,
	PUSH_BOOL,
	PUSH_STRING,
	PUSH_POINTER,

	PUSH_VARS,
	POP_VARS,
	APPLY_VAR,

	INTRINSIC,

	FUNCTION,
	CALL,
	RETURN,

	JUMP,
	JUMP_EQ,
	JUMP_NEQ,
	DO,
}