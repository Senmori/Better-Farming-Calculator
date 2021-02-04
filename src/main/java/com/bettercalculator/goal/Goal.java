package com.bettercalculator.goal;

/**
 * Represents the goal a player wishes to achieve.
 */
public interface Goal
{
	/**
	 * @return the current metric this goal is tracking
	 */
	int getCurrent();

	/**
	 * @return the target metric this goal is tracking
	 */
	int getTarget();
}
