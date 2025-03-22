package com.shiftingdawn.feylon;

import com.shiftingdawn.feylon.lang.TokenPos;

public interface SourcePosAware {

	void setSourcePos(TokenPos pos);

	TokenPos getSourcePos();
}