package com.bettercalculator.ui.component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Getter;

public class CheckBox extends JPanel
{
	@Getter
	private final JCheckBox checkBox;
	public CheckBox(String title)
	{
		this(title, false);
	}

	public CheckBox(String title, boolean rightToLeft)
	{
		JLabel label = new JLabel(title);
		checkBox = new JCheckBox();
		if (rightToLeft)
		{
			add(checkBox);
			add(label);
		}
		else
		{
			add(label);
			add(checkBox);
		}
	}

	public boolean isSelected()
	{
		return getCheckBox().isSelected();
	}
}
