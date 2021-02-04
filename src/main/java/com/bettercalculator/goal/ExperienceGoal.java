package com.bettercalculator.goal;

import lombok.Value;

/**
 * Represents a goal represented via experience
 */
@Value
public class ExperienceGoal implements Goal
{
	int currentExperience;
	int targetExperience;

	@Override
	public int getCurrent()
	{
		return currentExperience;
	}

	@Override
	public int getTarget()
	{
		return targetExperience;
	}
}
