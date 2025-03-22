package com.shiftingdawn.feylon;

import com.shiftingdawn.feylon.lang.DataType;

import java.io.Serial;
import java.util.Arrays;

public class DataTypeMismatchException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 7526421299577167300L;

	public DataTypeMismatchException(final DataType actual, final DataType... expected) {
		super(expected.length == 1
				? "Expected type '%s' but got '%s'".formatted(expected[0], actual)
				: "Expected one of types '%s' but got '%s'".formatted(Arrays.toString(expected), actual)
		);
	}
}