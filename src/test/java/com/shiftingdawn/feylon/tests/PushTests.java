package com.shiftingdawn.feylon.tests;

import com.shiftingdawn.feylon.lang.DataType;
import org.junit.jupiter.api.Test;

public class PushTests extends AbstractTestHost {

	@Test
	public void testPushInt() {
		this.run("1", 1);
		this.assertStack(1, DataType.INT);
	}

	@Test
	public void testPushInt3() {
		this.run("1 2 3", 3);
		this.assertStack(3, DataType.INT);
		this.assertStack(2, DataType.INT);
		this.assertStack(1, DataType.INT);
	}

	@Test
	public void testPushString() {
		final String str = "ThisIsAString!";
		this.run('"' + str + '"', 2);
		this.assertStack(0, DataType.POINTER);
		this.assertStack(str.length(), DataType.INT);
	}

	@Test
	public void testPushString2() {
		final String str = "ThisIsAString!";
		this.run('"' + str + "\"\n\"" + str + '"', 4);
		this.assertStack(str.length(), DataType.POINTER);
		this.assertStack(str.length(), DataType.INT);
		this.assertStack(0, DataType.POINTER);
		this.assertStack(str.length(), DataType.INT);
	}
}