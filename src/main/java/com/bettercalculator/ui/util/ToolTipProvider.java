package com.bettercalculator.ui.util;

import java.util.function.Supplier;

public interface ToolTipProvider extends Supplier<String>
{
	String getTooltip();

	@Override
	default String get()
	{
		return getTooltip();
	}
}
