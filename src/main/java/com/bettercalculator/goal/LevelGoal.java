package com.bettercalculator.goal;

import lombok.Value;

/**
 * Represents a goal represented in levels.
 */
@Value
public class LevelGoal implements Goal
{
	int currentLevel;
	int targetLevel;

	@Override
	public int getCurrent()
	{
		return currentLevel;
	}

	@Override
	public int getTarget()
	{
		return targetLevel;
	}
}
