package com.bettercalculator.farming;

/**
 * Represents a game tick that happens after a specified amount of time.
 */
public interface TimedTick
{
	/**
	 * @return the amount of time that has passed. The unit of which is determined by the implementation.
	 */
	int getTimePassed();
}
