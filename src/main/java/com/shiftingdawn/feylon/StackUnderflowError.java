package com.shiftingdawn.feylon;

import com.shiftingdawn.feylon.lang.TokenPos;

import java.io.Serial;

public class StackUnderflowError extends StackOverflowError implements SourcePosAware {
	
	@Serial
	private static final long serialVersionUID = -5799936586674004177L;
	private TokenPos pos;

	@Override
	public void setSourcePos(final TokenPos pos) {
		this.pos = pos;
	}

	@Override
	public TokenPos getSourcePos() {
		return this.pos;
	}
}
