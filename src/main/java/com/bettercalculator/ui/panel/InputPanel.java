package com.bettercalculator.ui.panel;

import com.bettercalculator.ui.RootPluginPanel;
import com.bettercalculator.ui.util.InputType;
import com.bettercalculator.ui.util.IntegerTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import lombok.Getter;
import net.runelite.api.Experience;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

public class InputPanel extends JPanel
{
	private final RootPluginPanel rootPanel;

	private final IntegerTextField currentLevelField;
	private final IntegerTextField currentExpField;
	private final IntegerTextField targetLevelField;
	private final IntegerTextField targetExpField;

	@Getter
	private int currentLevel, currentExp;
	@Getter
	private int targetLevel, targetExp;

	public InputPanel(RootPluginPanel rootPanel)
	{
		setLayout(new GridLayout(2, 2, 7, 7));
		this.rootPanel = rootPanel;
		currentLevelField = createTextField(InputType.LEVEL, "Current Level");
		currentExpField = createTextField(InputType.EXPERIENCE, "Current Experience");
		targetLevelField = createTextField(InputType.LEVEL, "Target Level");
		targetExpField = createTextField(InputType.EXPERIENCE, "Target Experience");

		currentLevelField.addActionListener(e -> {
			onCurrentLevelUpdated();
			targetLevelField.requestFocusInWindow();
		});

		currentExpField.addActionListener(e -> {
			onCurrentXPUpdated();
			targetExpField.requestFocusInWindow();
		});

		targetLevelField.addActionListener(e -> onTargetLevelUpdated());
		targetExpField.addActionListener(e -> onTargetXPUpdated());

		currentLevelField.addFocusListener(e -> onCurrentLevelUpdated());
		currentExpField.addFocusListener(e -> onCurrentXPUpdated());
		targetLevelField.addFocusListener(e -> onTargetLevelUpdated());
		targetExpField.addFocusListener(e -> onTargetXPUpdated());
	}

	private void onCurrentLevelUpdated()
	{
		currentLevel = getValueWithBounds(currentLevelField);
		currentExp = Experience.getXpForLevel(currentLevel);
		updateInputFields();
	}

	private void onCurrentXPUpdated()
	{
		currentExp = getValueWithBounds(currentExpField);
		currentLevel = Experience.getLevelForXp(currentExp);
		updateInputFields();
	}

	private void onTargetLevelUpdated()
	{
		targetLevel = getValueWithBounds(targetLevelField);
		targetExp = Experience.getXpForLevel(targetLevel);
		updateInputFields();
	}

	private void onTargetXPUpdated()
	{
		targetExp = getValueWithBounds(targetExpField);
		targetLevel = Experience.getLevelForXp(targetExp);
		updateInputFields();
	}

	private void updateInputFields()
	{
		if (targetExp < currentExp)
		{
			targetLevel = enforceBounds(InputType.LEVEL, currentLevel + 1);
			targetExp = Experience.getXpForLevel(targetLevel);
			if (targetExp == currentExp)
			{
				targetExp = currentExp + 1;
			}
		}
		currentLevelField.setValue(currentLevel);
		currentExpField.setValue(currentExp);
		targetLevelField.setValue(targetLevel);
		targetExpField.setValue(targetExp);

		rootPanel.update();
	}


	private int getValueWithBounds(IntegerTextField field)
	{
		InputType type = field.getInputType();
		return enforceBounds(type, field.getCurrentValue());
	}

	private int enforceBounds(InputType inputType, int value)
	{
		return Math.min(inputType.getMaxValue(), Math.max(inputType.getMinValue(), value));
	}

	private IntegerTextField createTextField(InputType inputType, String title)
	{
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		JLabel label = new JLabel(title);
		IntegerTextField input = new IntegerTextField(inputType);

		input.setBackground(ColorScheme.DARKER_GRAY_COLOR);
		input.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
		input.setBorder(new EmptyBorder(5, 7, 5, 7));

		label.setFont(FontManager.getRunescapeSmallFont());
		label.setBorder(new EmptyBorder(0, 0, 4, 0));
		label.setForeground(Color.WHITE);

		container.add(label, BorderLayout.NORTH);
		container.add(input, BorderLayout.SOUTH);

		add(container);
		return input;
	}
}
