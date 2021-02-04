package com.bettercalculator.farming.util;

import com.bettercalculator.farming.crop.GrowthCycle;
import lombok.Getter;

@Getter
public enum GrowthTiming
{
	FIVE_MINUTES(5),
	TEN_MINUTES(10),
	_20_MINUTES(20),
	_40_MINUTES(40),
	_80_MINUTES(80),
	_160_MINUTES(160),
	_320_MINUTES(320),
	_640_MINUTES(640),
	;

	private final int length;
	GrowthTiming(int lengthInMinutes)
	{
		this.length = lengthInMinutes;
	}

	public GrowthCycle toGrowthCycle(int numberOfCycles)
	{
		return GrowthCycle.of(this, numberOfCycles);
	}
}
