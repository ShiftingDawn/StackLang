package com.shiftingdawn.feylon;

public class Constants {

	public static final int SIZEOF_INT = Integer.BYTES;
	public static final int SIZEOF_PTR = Constants.SIZEOF_INT;

	public static final int STACK_SIZE_DATA = 64;
	public static final int STACK_SIZE_CALLS = Constants.STACK_SIZE_DATA;

	public static final int MEM_SIZE_STRINGS = 64 * 1000;
	public static final int MEM_SIZE_VARS = 64 * (Constants.SIZEOF_INT + 1) + 1;
}