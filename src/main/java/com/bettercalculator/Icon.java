package com.bettercalculator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.UnaryOperator;
import javax.annotation.Nonnull;
import javax.swing.ImageIcon;
import net.runelite.client.util.ImageUtil;

public enum Icon
{
	PANEL_ICON("icon", "panel_icon.png"),
	;

	private final String file;
	private final String dir;
	Icon(String dir, String location)
	{
		this.file = location;
		this.dir = dir;
	}

	/**
	 * Get the raw {@link BufferedImage} of this icon.
	 * @return {@link BufferedImage} of the icon
	 */
	public BufferedImage getImage()
	{
		return ImageUtil.loadImageResource(BetterCalculator.class, "/" + dir + "/" + file);
	}

	/**
	 * @return the {@link ImageIcon} with no modifications. Equivalent to {@code getIcon(UnaryOperator.identity())}
	 */
	public ImageIcon getIcon()
	{
		return getIcon(UnaryOperator.identity());
	}

	/**
	 * Return this icon.
	 * <br>
	 * The {@link UnaryOperator} is applied to the {@link BufferedImage}. The {@link ImageIcon}
	 * is then created using that modified image.
	 *
	 * @param func the {@link UnaryOperator} to apply to the image
	 * @return the modified {@link ImageIcon}
	 */
	public ImageIcon getIcon(@Nonnull UnaryOperator<BufferedImage> func)
	{
		BufferedImage img = func.apply(getImage());
		return new ImageIcon(img);
	}

}
