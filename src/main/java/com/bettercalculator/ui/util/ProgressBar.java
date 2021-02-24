package com.bettercalculator.ui.util;

import com.bettercalculator.ui.panel.InputPanel;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import lombok.Getter;
import lombok.Setter;

public class ProgressBar extends JProgressBar
{
	private final DecimalFormat formatter = new DecimalFormat("#,###");
	@Setter
	@Getter
	private Color startColor = Color.RED;
	@Setter
	@Getter
	private Color finishColor = Color.GREEN;


	private final InputPanel inputPanel;
	private int gainedExp = 0;

	public ProgressBar(InputPanel inputPanel)
	{
		super();
		setStringPainted(true);
		this.inputPanel = inputPanel;
	}

	public void updateExp(int gainedExp)
	{
		int startExp = inputPanel.getCurrentExp();
		int targetExp = inputPanel.getTargetExp();
		this.gainedExp += gainedExp;
		int currentExp = startExp + this.gainedExp;

		if (targetExp <= 0)
		{
			return;
		}
		if (currentExp >= targetExp)
		{
			SwingUtilities.invokeLater(this::displayCompleteProgressBar);
			return;
		}

		double progressValue = (double) (currentExp * 100 / targetExp);
		SwingUtilities.invokeLater(() -> this.updateProgressBar(currentExp, (int) progressValue));
	}

	private void updateProgressBar(int currentExp, int progress)
	{
		setString(formatter.format(currentExp) + " / " + formatter.format(inputPanel.getTargetExp()));
		setValue(progress);
		updateTooltip();
		adjustForegroundColor();
	}

	private void displayCompleteProgressBar()
	{
		setValue(100);
		setString("Complete!");
		setForeground(getFinishColor());
		updateTooltip();
	}

	private void updateTooltip()
	{
		setToolTipText(((int)(getPercentComplete() * 100)) + "%");
	}

	private void adjustForegroundColor()
	{
		double ratio = getPercentComplete();
		int red = interpolate(ratio, getFinishColor().getRed(), getStartColor().getRed());
		int green = interpolate(ratio, getFinishColor().getGreen(), getStartColor().getGreen());
		int blue = interpolate(ratio, getFinishColor().getBlue(), getStartColor().getBlue());
		setForeground(new Color(red, green, blue).brighter());
	}

	private int interpolate(double ratio, int finish, int start)
	{
		return (int) Math.abs( (ratio * finish) + (1 - ratio) * start);
	}
}
