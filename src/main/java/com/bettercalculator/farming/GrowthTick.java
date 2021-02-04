package com.bettercalculator.farming;

import com.bettercalculator.TimedTick;
import java.util.concurrent.TimeUnit;
import lombok.Getter;

/**
 * Represents a single growth tick for a farming patch.
 */
@Getter
public class GrowthTick implements TimedTick
{
	/** The amount of time that has passed (in minutes). */
	int timePassed;
	/**
	 * The {@link TimeUnit} that can be used to convert the {@link #getTimePassed()} into other units.
	 * By default, this will be {@link TimeUnit#MINUTES}.
	 */
	TimeUnit timeUnit;
}
