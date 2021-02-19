package com.bettercalculator.farming.ui;

import com.bettercalculator.ui.RootPluginPanel;
import com.bettercalculator.ui.panel.CalculatePanel;
import com.bettercalculator.ui.panel.CalculatorScreen;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FarmingCalculatorScreen extends CalculatorScreen
{
	DecimalFormat formatter = new DecimalFormat("#,###");

	private final CalculatePanel calculatePanel;
	private final JLabel label;
	public FarmingCalculatorScreen(RootPluginPanel rootPanel)
	{
		super(rootPanel);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 0, 0, 0));

		calculatePanel = new CalculatePanel();
		label = new JLabel();


		add(calculatePanel, BorderLayout.NORTH);
		add(label, BorderLayout.CENTER);
	}

	@Override
	public void onHide()
	{
		label.setVisible(false);
	}

	@Override
	public void onDisplay()
	{
		displayNeededXP();
	}

	@Override
	public void update()
	{
		displayNeededXP();
	}

	private void displayNeededXP()
	{
		int currentXP = getRootPluginPanel().getInputPanel().getCurrentExp();
		int targetXP = getRootPluginPanel().getInputPanel().getTargetExp();
		int diff = targetXP - currentXP;
		label.setText("You need " + formatter.format(diff) + " XP.");
		label.setVisible(true);
	}
}
