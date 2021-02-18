package com.bettercalculator.ui.util;

import java.util.Locale;
import java.util.function.UnaryOperator;
import lombok.Getter;
import net.runelite.api.Experience;
import org.apache.commons.text.WordUtils;

public enum InputType implements UnaryOperator<Integer>, ToolTipProvider
{
	LEVEL(Experience::getLevelForXp, 3, 1, Experience.MAX_VIRT_LEVEL),
	EXPERIENCE(num -> num, 9, 0, Experience.MAX_SKILL_XP),
	;

	private final UnaryOperator<Integer> operator;
	@Getter
	private final int maxDigits;
	@Getter
	private final int maxValue;
	@Getter
	private final int minValue;
	InputType(UnaryOperator<Integer> operator, int maxDigits, int minValue, int maxValue)
	{
		this.operator = operator;
		this.maxDigits = maxDigits;
		this.maxValue = maxValue;
		this.minValue = minValue;
	}

	@Override
	public Integer apply(Integer num)
	{
		return operator.apply(num);
	}

	@Override
	public String getTooltip()
	{
		return WordUtils.capitalize(name().toLowerCase(Locale.ROOT));
	}
}
