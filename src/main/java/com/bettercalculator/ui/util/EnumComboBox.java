package com.bettercalculator.ui.util;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;

public class EnumComboBox<T extends Enum<T>> extends JComboBox<Enum<T>>
{

	public EnumComboBox(Class<T> enumClass)
	{
		super(enumClass.getEnumConstants());
		setRenderer(new DropdownRenderer());
		setPreferredSize(new Dimension(super.getPreferredSize().width, 25));
		setForeground(Color.WHITE);
		setFocusable(false);
		setEditable(false);
	}


	@Override
	public T getSelectedItem()
	{
		Object obj =  super.getSelectedItem();
		if (obj == null)
		{
			obj = getItemAt(0);
			return (T) obj;
		}
		return (T) obj;
	}
}
