package com.bettercalculator.ui.component;

import lombok.Getter;
import net.runelite.api.Experience;
import net.runelite.client.util.Text;

@Getter
public enum InputType implements ToolTipProvider
{
	LEVEL(String.valueOf(Experience.MAX_VIRT_LEVEL).length(), 1, Experience.MAX_VIRT_LEVEL),
	EXPERIENCE(String.valueOf(Experience.MAX_SKILL_XP).length(), 0, Experience.MAX_SKILL_XP),
	;

	private final int maxDigits;
	private final int maxValue;
	private final int minValue;
	InputType(int maxDigits, int minValue, int maxValue)
	{
		this.maxDigits = maxDigits;
		this.maxValue = maxValue;
		this.minValue = minValue;
	}

	@Override
	public String getTooltip()
	{
		return Text.titleCase(this);
	}
}
