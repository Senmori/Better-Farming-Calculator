package com.bettercalculator.farming.ui;

import com.bettercalculator.ui.RootPluginPanel;
import com.bettercalculator.ui.panel.CalculatePanel;
import com.bettercalculator.ui.panel.CalculatorScreen;
import com.bettercalculator.ui.util.ProgressBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class FarmingCalculatorScreen extends CalculatorScreen
{
	DecimalFormat formatter = new DecimalFormat("#,###");
	private final Color START = Color.RED;
	private final Color FINISH = Color.GREEN;

	private final CalculatePanel calculatePanel;
	private final JLabel neededXP;
	private final ProgressBar progress;
	//TODO: Remove below this after testing
	private final Timer progressTimer;
	private int currentGainedXP = 0;
	public FarmingCalculatorScreen(RootPluginPanel rootPanel)
	{
		super(rootPanel);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 0, 0, 0));

		calculatePanel = new CalculatePanel();
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(3, 1,0, 5));
		neededXP = new JLabel("Test1");
		progress = new ProgressBar(getRootPluginPanel().getInputPanel());
		container.add(neededXP);
		container.add(progress);

		calculatePanel.add(container, BorderLayout.SOUTH);
		add(calculatePanel, BorderLayout.NORTH);

		progressTimer = new Timer(50, e -> {
			this.displayNeededXP(5000);
		});
		calculatePanel.getCalculateButton().addActionListener(e -> {
			if (!progressTimer.isRunning())
			{
				progressTimer.start();
			}
		});
		calculatePanel.getCancelButton().addActionListener(e -> {
			if (progressTimer.isRunning())
			{
				progressTimer.stop();
			}
		});
	}

	@Override
	public void onHide()
	{
		progressTimer.stop();
	}

	@Override
	public void onDisplay()
	{
		displayNeededXP(currentGainedXP);
	}

	@Override
	public void update()
	{
		displayNeededXP(currentGainedXP);
	}


	private void displayNeededXP(int updatedXP)
	{
		progress.updateExp(updatedXP);
	}
}
