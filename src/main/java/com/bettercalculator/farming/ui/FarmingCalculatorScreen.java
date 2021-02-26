package com.bettercalculator.farming.ui;

import com.bettercalculator.BetterCalculator;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crops.FruitTree;
import com.bettercalculator.ui.RootPluginPanel;
import com.bettercalculator.ui.panel.CalculatePanel;
import com.bettercalculator.ui.panel.CalculatorScreen;
import com.bettercalculator.ui.component.ProgressBar;
import javax.swing.BoxLayout;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class FarmingCalculatorScreen extends CalculatorScreen
{
	private final CalculatePanel calculatePanel;
	private final ProgressBar progress;
	//TODO: Remove below this after testing
	private final Timer progressTimer;

	private final CropSectionUI<FruitTree> treeSectionUI;
	private int needed = 0;
	public FarmingCalculatorScreen(RootPluginPanel rootPanel)
	{
		super(rootPanel);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(5, 2, 2, 2));

		calculatePanel = new CalculatePanel();
		progress = new ProgressBar(getRootPluginPanel().getInputPanel());
		treeSectionUI = new CropSectionUI<>(CropType.FRUIT_TREE, FruitTree.class);
		add(calculatePanel);
		add(progress);
		add(treeSectionUI);

		progressTimer = new Timer(5, e -> {
			if (progress.isComplete())
			{
				calculatePanel.getCancelButton().doClick(); // simulate clicking cancel button
				return;
			}
			this.displayNeededXP(getSelectedCropXP());
		});
		calculatePanel.getCalculateButton().addActionListener(e -> this.startTimer());
		calculatePanel.getCancelButton().addActionListener(e -> this.stopTimer());
		revalidate();
	}

	private void startTimer()
	{
		if (!progressTimer.isRunning())
		{
			if (progress.isComplete())
			{
				progress.reset();
				needed = 0;
				progressTimer.restart();
			}
			else
			{
				progressTimer.start();
			}
		}
	}

	private void stopTimer()
	{
		if (progressTimer.isRunning())
		{
			progressTimer.stop();
		}
	}

	@Override
	public void onHide()
	{
		progressTimer.stop();
	}

	@Override
	public void onDisplay()
	{
		if (!progressTimer.isRunning())
		{
			progressTimer.start();
		}
	}

	@Override
	public void update(BetterCalculator plugin)
	{

	}

	private double getSelectedCropXP()
	{
		return treeSectionUI.getExperience();
	}

	private boolean displayNeededXP(double updatedXP)
	{
		return progress.updateExp(updatedXP);
	}
}
