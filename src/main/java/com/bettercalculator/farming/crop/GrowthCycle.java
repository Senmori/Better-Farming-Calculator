package com.bettercalculator.farming.crop;

import com.bettercalculator.farming.GrowthTick;
import lombok.Value;

/**
 * Represents how long a {@link Crop} will take to grow.
 * This is separated into two parts.<br>
 * The growth cycle, which is how long until the crop "advances" a stage in it's growth
 * and
 * The number of cycles needed for the {@link Crop} to be fully grown.
 */
@Value(
	staticConstructor = "of"
)
public class GrowthCycle
{
	/** How long each growth cycle is. */
	GrowthTiming growthTiming;
	/** The number of growth cycles until this crop is fully grown. */
	int numberOfGrowthCycles;

	/**
	 * Get the total amount of time required for this crop to be fully grown.
	 * <br>
	 * This is calculated via <br>{@code getGrowthCycleLength() * getNumberOfGrowthCycles()}
	 *
	 * @return the total amount of time required for this crop to be fully grown.
	 */
	public int getGrowthTime()
	{
		return getGrowthTiming().getLength() * getNumberOfGrowthCycles();
	}

	/**
	 * Check if the crop is fully grown according to its {@link GrowthCycle}
	 *
	 * @param tick the {@link GrowthTick}
	 * @return true if the crop is fully grown (more time has passed than it's total growth cycle time)
	 */
	public boolean isFullyGrown(GrowthTick tick)
	{
		int timePassed = (int) tick.getTimeUnit().toMinutes(tick.getTimePassed());
		return timePassed >= getGrowthTime();
	}

	/**
	 * Get the current growth stage given the {@link GrowthTick}.
	 *
	 * @param tick the {@link GrowthTick} the crop is currently on
	 * @return the current growth stage for the given {@link GrowthTick} since the crop was planted
	 */
	public int getGrowthStage(GrowthTick tick)
	{
		int timePassed = (int) tick.getTimeUnit().toMinutes(tick.getTimePassed());
		return Math.min(1, timePassed / getGrowthTiming().getLength());
	}
}
