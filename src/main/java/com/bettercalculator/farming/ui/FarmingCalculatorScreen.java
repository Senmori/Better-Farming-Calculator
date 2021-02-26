package com.bettercalculator.farming.ui;

import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crops.FruitTree;
import com.bettercalculator.ui.RootPluginPanel;
import com.bettercalculator.ui.panel.CalculatePanel;
import com.bettercalculator.ui.panel.CalculatorScreen;
import com.bettercalculator.ui.component.ProgressBar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import org.apache.commons.lang3.StringUtils;

public class FarmingCalculatorScreen extends CalculatorScreen
{
	private final CalculatePanel calculatePanel;
	private final ProgressBar progress;
	//TODO: Remove below this after testing
	private final Timer progressTimer;

	private final CropSectionUI<FruitTree> treeSectionUI;
	private final JLabel selectedLabel;
	private int needed = 0;
	public FarmingCalculatorScreen(RootPluginPanel rootPanel)
	{
		super(rootPanel);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 0, 0, 0));

		calculatePanel = new CalculatePanel();
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(3, 1,0, 5));
		progress = new ProgressBar(getRootPluginPanel().getInputPanel());
		container.add(progress);


		calculatePanel.add(container, BorderLayout.SOUTH);
		add(calculatePanel, BorderLayout.NORTH);

		treeSectionUI = new CropSectionUI<>(CropType.FRUIT_TREE, FruitTree.class);
		container.add(treeSectionUI);
		selectedLabel = new JLabel("Crop: ");
		container.add(selectedLabel);

		treeSectionUI.addActionListener(e -> {
			String name = StringUtils.capitalize(treeSectionUI.getSelectedItem().name().toLowerCase(Locale.ROOT));
			selectedLabel.setText("Crop: " + name);
		});

		progressTimer = new Timer(5, e -> {
			if (progress.isComplete())
			{
				calculatePanel.getCancelButton().doClick(); // simulate clicking cancel button
			}
			updateSelectedLabel(this.displayNeededXP(getSelectedCropXP()));
		});
		calculatePanel.getCalculateButton().addActionListener(e -> {
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
		});
		calculatePanel.getCancelButton().addActionListener(e -> {
			if (progressTimer.isRunning())
			{
				progressTimer.stop();
			}
		});
		revalidate();
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
	public void update()
	{

	}

	private void updateSelectedLabel(boolean success)
	{
		if (success) needed++;
		StringBuilder text = new StringBuilder();
		text.append(StringUtils.capitalize(treeSectionUI.getSelectedItem().name().toLowerCase(Locale.ROOT)));
		if (progressTimer.isRunning() || progress.isComplete())
		{
			text.append(" -> ").append(getSelectedCropXP());
		}
		if (needed > 0)
		{
			text.append(" (Need: ").append(needed).append(")");
		}
		selectedLabel.setText(text.toString());
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
