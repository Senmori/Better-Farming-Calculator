package com.bettercalculator.farming.crop;

import lombok.Getter;

/**
 * Represents the different kinds of compost that can be applied to a farming patch.
 */
@Getter
public enum Compost
{
	/** Regular compost. */
	COMPOST(0.5, 18),
	/** Supercompost. */
	SUPERCOMPOST(0.85, 26),
	/** Fertile Soil Spell that acts as Supercompost. */
	FERTILE_SOIL_SPELL(SUPERCOMPOST.getDiseaseReductionRate(), 18),
	/** Ultracompost. */
	ULTRACOMPOST(0.9, 36),
	/** Fertile Soil spell that acts as ultracompost. */
	FERTILE_SOIL_ULTRACOMPOST(ULTRACOMPOST.getDiseaseReductionRate(), 18),
	/** No compost. */
	NONE(0.0D, 0),
	;

	/** Get how much this compost reduces a crop's disease rate each growth cycle. */
	private final double diseaseReductionRate;
	/** Get how much experience is earned when using this compost. */
	private final int farmingExperienceGained;
	Compost(double diseaseReductionRate, int farmingExperienceGained)
	{
		this.diseaseReductionRate = diseaseReductionRate;
		this.farmingExperienceGained = farmingExperienceGained;
	}

}
