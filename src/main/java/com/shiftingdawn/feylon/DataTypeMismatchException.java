package com.shiftingdawn.feylon;

import com.shiftingdawn.feylon.lang.DataType;
import com.shiftingdawn.feylon.lang.TokenPos;

import java.io.Serial;
import java.util.Arrays;

public class DataTypeMismatchException extends RuntimeException implements SourcePosAware {

	@Serial
	private static final long serialVersionUID = 7526421299577167300L;
	private TokenPos pos;

	public DataTypeMismatchException(final DataType actual, final DataType... expected) {
		super(expected.length == 1
				? "Expected type '%s' but got '%s'".formatted(expected[0], actual)
				: "Expected one of types '%s' but got '%s'".formatted(Arrays.toString(expected), actual)
		);
	}

	@Override
	public void setSourcePos(final TokenPos pos) {
		this.pos = pos;
	}

	@Override
	public TokenPos getSourcePos() {
		return this.pos;
	}
}