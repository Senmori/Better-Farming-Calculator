package com.bettercalculator.farming.patch;

import com.bettercalculator.farming.crop.Compost;
import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crop.EmptyCrop;
import javax.annotation.Nonnull;

/**
 * Represents a single farming patch that can support a specific {@link CropType}.
 */
public interface FarmingPatch
{
	/**
	 * @return the {@link CropType} that is supported in this farming patch.
	 */
	@Nonnull
	CropType getSupportedCropType();

	/**
	 * Get the currently planted {@link Crop}, if any.
	 * <br>
	 * If no {@link Crop} is planted, an {@link EmptyCrop} will be returned.
	 *
	 * @return the currently planted crop, or an {@link EmptyCrop}.
	 */
	@Nonnull
	Crop getCurrentCrop();

	/**
	 * Set the currently planted crop if, and only if, this patch has nothing planted.
	 *
	 * @param crop the {@link Crop} to plant.
	 */
	void plantCrop(Crop crop);

	/**
	 * Check if this farming patch supports the supplied compost type.
	 *
	 * @param compost the {@link Compost} to use.
	 * @return true if the supplied {@link Compost} can be used on this farming patch.
	 */
	boolean supportsCompost(Compost compost);

	/**
	 * Add the given {@link Compost} to this farming patch.
	 * @param compost the {@link Compost} to use.
	 */
	void addCompost(Compost compost);

	/**
	 * @return the {@link Compost} used on this farming patch, or {@link Compost#NONE} if none used.
	 */
	@Nonnull
	Compost getCompostUsed();

	/**
	 * @return true if this {@link Crop} is diseased. Crops do not advance growth stages if they are diseased.
	 */
	boolean isCropDiseased();

	/**
	 * Set that this crop is diseased.
	 *
	 * @param diseased true to make this crop be diseased
	 */
	void setDiseased(boolean diseased);

	/**
	 * @return the chance this {@link Crop} will become diseased if no outside influences occur.
	 */
	double getDiseaseChance();
}
