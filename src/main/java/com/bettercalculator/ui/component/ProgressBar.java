package com.bettercalculator.ui.component;

import com.bettercalculator.ui.panel.InputPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import lombok.Getter;
import lombok.Setter;
import net.runelite.client.ui.PluginPanel;

public class ProgressBar extends JPanel
{
	private static final DecimalFormat formatter = new DecimalFormat("#,###");
	@Setter
	@Getter
	private Color startColor = Color.RED;
	@Setter
	@Getter
	private Color finishColor = Color.GREEN;

	private final JProgressBar progressBar;
	private final InputPanel inputPanel;
	private double gainedExp = 0;
	private boolean complete;

	public ProgressBar(InputPanel inputPanel)
	{
		super();
		setBorder(new EmptyBorder(2, 0, 2, 0));
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH - 3, 23));
		this.inputPanel = inputPanel;

		add(progressBar);
	}

	public void reset()
	{
		this.gainedExp = 0.0D;
		updateProgressBar(0, 0);
		complete = false;
	}

	public boolean isComplete()
	{
		return complete;
	}

	public boolean updateExp(double gainedExp)
	{
		if (isComplete())
		{
			return false;
		}
		int startExp = inputPanel.getCurrentExp();
		int targetExp = inputPanel.getTargetExp();
		this.gainedExp += gainedExp;
		double currentExp = startExp + this.gainedExp;

		if (targetExp <= 0)
		{
			return false;
		}
		if (currentExp >= targetExp)
		{
			SwingUtilities.invokeLater(this::displayCompleteProgressBar);
			complete = true;
			return true;
		}

		double progressValue = currentExp * 100 / targetExp;
		SwingUtilities.invokeLater(() -> this.updateProgressBar( (int) currentExp, (int) progressValue));
		return true;
	}

	private void updateProgressBar(int currentExp, int progress)
	{
		progressBar.setString(formatter.format(currentExp) + " / " + formatter.format(inputPanel.getTargetExp()));
		progressBar.setValue(progress);
		updateTooltip();
		adjustForegroundColor();
	}

	private void displayCompleteProgressBar()
	{
		progressBar.setValue(100);
		progressBar.setString("Complete!");
		progressBar.setForeground(getFinishColor());
		updateTooltip();
	}

	private void updateTooltip()
	{
		progressBar.setToolTipText(((int)(progressBar.getPercentComplete() * 100)) + "%");
	}

	private void adjustForegroundColor()
	{
		double ratio = progressBar.getPercentComplete();
		int red = interpolate(ratio, getFinishColor().getRed(), getStartColor().getRed());
		int green = interpolate(ratio, getFinishColor().getGreen(), getStartColor().getGreen());
		int blue = interpolate(ratio, getFinishColor().getBlue(), getStartColor().getBlue());
		progressBar.setForeground(new Color(red, green, blue).brighter());
	}

	private int interpolate(double ratio, int finish, int start)
	{
		return (int) Math.abs( (ratio * finish) + (1 - ratio) * start);
	}
}
